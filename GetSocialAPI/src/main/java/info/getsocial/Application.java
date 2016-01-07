package info.getsocial;


import org.glassfish.jersey.servlet.ServletContainer;
import org.glassfish.jersey.servlet.ServletProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import org.springframework.social.UserIdSource;
import org.springframework.social.security.SocialAuthenticationFilter;
import org.springframework.social.security.SpringSocialConfigurer;

import info.getsocial.config.JerseyConfig;
import info.getsocial.security.AuthenticationFilter;
import info.getsocial.security.AuthenticationSuccessHandler;
import info.getsocial.security.RESTAuthenticationEntryPoint;
import info.getsocial.security.SocialUserService;

@Configuration
@EnableWebSecurity
@EnableAutoConfiguration
@EnableJpaRepositories(basePackages = {"info.getsocial.dao"})
@ComponentScan(basePackages = { "info.getsocial" })
@EntityScan(basePackages = {"info.getsocial.domain"})
public class Application extends WebSecurityConfigurerAdapter {

	public static void main(String[] args) throws Exception {
		new SpringApplicationBuilder(Application.class).showBanner(false).run(args);
	}

	public Application() {
		super(true);
	}

	@Autowired
	private AuthenticationSuccessHandler authenticationSuccessHandler;
	
	@Autowired
	private RESTAuthenticationEntryPoint authenticationEntryPoint;
	
	@Autowired
	private AuthenticationFilter authenticationFilter;

	@Autowired
	private UserIdSource userIdSource;

	@Autowired
	private SocialUserService userService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// Set a custom successHandler on the SocialAuthenticationFilter
		final SpringSocialConfigurer socialConfigurer = new SpringSocialConfigurer();
		socialConfigurer.addObjectPostProcessor(new ObjectPostProcessor<SocialAuthenticationFilter>() {
			@Override
			public <O extends SocialAuthenticationFilter> O postProcess(O socialAuthenticationFilter) {
				 socialAuthenticationFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler);
				return socialAuthenticationFilter;
			}
		});

		http.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint)
				.and().anonymous()
				.and().servletApi()
				.and().headers().cacheControl()
				.and().authorizeRequests()

				// allow anonymous font and template requests
				.antMatchers("/").permitAll()
				.antMatchers("/favicon.ico").permitAll()
				.antMatchers("/resources/**").permitAll()

				// allow anonymous calls to social login
				.antMatchers("/auth/**").permitAll()

				// all other request need to be authenticated
				.antMatchers(HttpMethod.GET, "/rest/**").hasRole("USER")
				
				// add custom authentication filter for complete stateless JWT
				// based authentication
				.and().addFilterBefore(authenticationFilter, AbstractPreAuthenticatedProcessingFilter.class)

				// apply the configuration from the socialConfigurer (adds the
				// SocialAuthenticationFilter)
				.apply(socialConfigurer.userIdSource(userIdSource));
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService);
	}

	@Override
	protected SocialUserService userDetailsService() {
		return userService;
	}

	@Bean
	public ServletRegistrationBean jerseyServlet() {
		ServletRegistrationBean registration = new ServletRegistrationBean(new ServletContainer(), "/rest/*");
		registration.addInitParameter(ServletProperties.JAXRS_APPLICATION_CLASS, JerseyConfig.class.getName());
		return registration;
	}
}
