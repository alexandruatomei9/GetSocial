package info.getsocial.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;

import info.getsocial.security.UserAuthenticationUserIdSource;

@Configuration
@EnableSocial
public class FacebookConfig extends SocialConfigurerAdapter {

	private static String ABOUT_ME_PERMISSION = "user_about_me";
	private static String BOOKS_PERMISSION = "user_actions.books";
	private static String EDUCATION_PERMISSION = "user_education_history";
	private static String MUSIC_PERMISSION = "user_actions.music";
	private static String LOCATION_PERMISSION = "user_location";
	private static String READ_CUSTOM_FRIENDLIST = "read_custom_friendlists";
	private static String READ_FRIENDLIST = "user_friends";
	@Autowired
	private ConnectionSignUp autoSignUpHandler;

	@Autowired
	private DataSource datasource;

	public void addConnectionFactories(ConnectionFactoryConfigurer connectionFactoryConfigurer,
			Environment environment) {
		FacebookConnectionFactory fbConnFactory = new FacebookConnectionFactory(
				environment.getProperty("facebook.appKey"), environment.getProperty("facebook.appSecret"),
				environment.getProperty("facebook.appNameSpace"));
		fbConnFactory.setScope(ABOUT_ME_PERMISSION + "," + BOOKS_PERMISSION + "," + EDUCATION_PERMISSION + ","
				+ MUSIC_PERMISSION + "," + LOCATION_PERMISSION + "," + READ_CUSTOM_FRIENDLIST + "," + READ_FRIENDLIST);
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

		JdbcUsersConnectionRepository repo = new JdbcUsersConnectionRepository(datasource, connectionFactoryLocator,
				Encryptors.noOpText());
		repo.setConnectionSignUp(autoSignUpHandler);

		return repo;
	}
}
