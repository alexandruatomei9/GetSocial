package info.getsocial.security;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.social.connect.ConnectionKey;
import org.springframework.social.security.SocialUserDetailsService;

import info.getsocial.domain.UserAccount;

public interface SocialUserService extends SocialUserDetailsService, UserDetailsService {

    UserAccount loadUserByConnectionKey(ConnectionKey connectionKey);
    UserAccount loadUserByUserId(String userId);
    UserAccount loadUserByUsername(String username);
    void updateUserDetails(UserAccount user);

}