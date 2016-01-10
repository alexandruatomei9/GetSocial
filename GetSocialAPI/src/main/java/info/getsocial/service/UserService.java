package info.getsocial.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.connect.ConnectionKey;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import info.getsocial.dao.UserRepository;
import info.getsocial.domain.UserAccount;
import info.getsocial.security.SocialUserService;

@Component
public class UserService implements SocialUserService {

	@Autowired
	private UserRepository userRepo;

	private final AccountStatusUserDetailsChecker detailsChecker = new AccountStatusUserDetailsChecker();

	@Override
	@Transactional(readOnly = true)
	public UserAccount loadUserByUserId(String userId) {
		final UserAccount user = userRepo.findById(Long.valueOf(userId));
		return checkUser(user);
	}

	@Override
	@Transactional(readOnly = true)
	public UserAccount loadUserByUsername(String username) {
		final UserAccount user = userRepo.findByUsername(username);
		return checkUser(user);
	}

	@Override
	@Transactional(readOnly = true)
	public UserAccount loadUserByConnectionKey(ConnectionKey connectionKey) {
		final UserAccount user = userRepo.findByProviderIdAndProviderUserId(connectionKey.getProviderId(),
				connectionKey.getProviderUserId());
		return checkUser(user);
	}

	@Override
	public void updateUserDetails(UserAccount user) {
		userRepo.save(user);
	}

	private UserAccount checkUser(UserAccount user) {
		if (user == null) {
			throw new UsernameNotFoundException("user not found");
		}
		detailsChecker.check(user);
		return user;
	}
}
