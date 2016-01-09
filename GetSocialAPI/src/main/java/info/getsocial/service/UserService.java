package info.getsocial.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.connect.ConnectionKey;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import info.getsocial.dao.IDaoUser;
import info.getsocial.dao.UserRepository;
import info.getsocial.domain.UserAccount;
import info.getsocial.domain.exception.GetSocialException;
import info.getsocial.security.SocialUserService;

@Component
public class UserService implements SocialUserService {

	@Autowired
	IDaoUser userDao;

	@Autowired
	private UserRepository userRepo;

	private final AccountStatusUserDetailsChecker detailsChecker = new AccountStatusUserDetailsChecker();

	public UserAccount getUser(String id) throws GetSocialException {
		try {
			UserAccount user = userDao.get(id);
			return user;
		} catch (Exception ex) {
			throw new GetSocialException(404);
		}
	}

	public void saveUser(String id, String name, String email, String password) throws GetSocialException {
		try {
			// User user = new User(id, name, email, password);
			// userDao.save(user);
		} catch (Exception ex) {
			throw new GetSocialException(500);
		}
	}

	public void deleteUser(UserAccount user) throws GetSocialException {
		try {
			userDao.delete(user);
		} catch (Exception ex) {
			throw new GetSocialException(500);
		}
	}

	public void updateUser(UserAccount user) throws GetSocialException {
		try {
			userDao.update(user);
		} catch (Exception ex) {
			throw new GetSocialException(500);
		}
	}

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
