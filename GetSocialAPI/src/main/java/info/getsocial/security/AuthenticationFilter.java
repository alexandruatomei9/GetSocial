package info.getsocial.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import info.getsocial.dao.UserRepository;
import info.getsocial.domain.UserAccount;

@Component
public class AuthenticationFilter extends GenericFilterBean {

	@Autowired
	private TokenAuthenticationService tokenAuthenticationService;

	@Autowired
	private UserRepository userRepo;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		this.addAuthenticationFromHeader((HttpServletRequest) request);
		chain.doFilter(request, response);
	}

	private void addAuthenticationFromHeader(HttpServletRequest request) {
		final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof UserAuthentication)) {
			final UserAuthentication userAuthentication = tokenAuthenticationService.getAuthentication(request);
			if (userAuthentication != null) {
				UserAccount account = (UserAccount) userAuthentication.getPrincipal();
				if (account != null && userExistsInDB(account.getId())) {
					SecurityContextHolder.getContext().setAuthentication(userAuthentication);
				}
			}
		}
	}

	private boolean userExistsInDB(Long id) {
		UserAccount account = userRepo.findById(id);
		return (account != null);
	}
}
