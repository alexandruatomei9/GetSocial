package info.getsocial.security;

import info.getsocial.domain.User;

public enum UserRole {
	USER;

	public UserAuthority asAuthorityFor(final User user) {
		final UserAuthority authority = new UserAuthority();
		authority.setAuthority("ROLE_" + toString());
		authority.setUser(user);
		return authority;
	}

	public static UserRole valueOf(final UserAuthority authority) {
		switch (authority.getAuthority()) {
		case "ROLE_USER":
			return USER;
		}
		throw new IllegalArgumentException("No role defined for authority: " + authority.getAuthority());
	}
}