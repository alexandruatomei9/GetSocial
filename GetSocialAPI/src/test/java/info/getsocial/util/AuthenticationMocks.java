package info.getsocial.util;

import info.getsocial.domain.UserAccount;
import info.getsocial.security.UserAuthentication;

public class AuthenticationMocks {

	private AuthenticationMocks() {
	}

	public static UserAuthentication mockedUser() {
		UserAccount account = new UserAccount();
		account.setUsername("Mock User");
		account.setProviderId("facebook");
		return new UserAuthentication(account);
	}
}
