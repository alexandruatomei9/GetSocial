package info.getsocial.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.core.env.Environment;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.mem.InMemoryUsersConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;

import info.getsocial.security.SocialUserService;
import info.getsocial.security.UserAuthenticationUserIdSource;
import info.getsocial.security.UsersConnectionRepositoryImpl;

@Configuration
@EnableSocial
public class FacebookConfig extends SocialConfigurerAdapter {

	@Autowired
	private ConnectionSignUp autoSignUpHandler;

	@Autowired
	private SocialUserService userService;

	public void addConnectionFactories(ConnectionFactoryConfigurer connectionFactoryConfigurer,
			Environment environment) {
		FacebookConnectionFactory fbConnFactory = new FacebookConnectionFactory(
				environment.getProperty("facebook.appKey"), environment.getProperty("facebook.appSecret"),
				environment.getProperty("facebook.appNameSpace"));
		connectionFactoryConfigurer.addConnectionFactory(fbConnFactory);
	}

	@Override
	public UserIdSource getUserIdSource() {
		// retrieve the UserId from the UserAuthentication in the security
		// context
		return new UserAuthenticationUserIdSource();
	}

	@Bean
	@Scope(value = "request", proxyMode = ScopedProxyMode.INTERFACES)
	public Facebook facebook(ConnectionRepository repository) {
		Connection<Facebook> connection = repository.findPrimaryConnection(Facebook.class);
		return connection != null ? connection.getApi() : null;
	}

	@Override
	public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
		UsersConnectionRepositoryImpl usersConnectionRepository = new UsersConnectionRepositoryImpl(userService,
				connectionFactoryLocator);
		
		// if no local user record exists yet for a facebook's user id
		// automatically create a User and add it to the database
		usersConnectionRepository.setConnectionSignUp(autoSignUpHandler);

		return usersConnectionRepository;
	}
}
