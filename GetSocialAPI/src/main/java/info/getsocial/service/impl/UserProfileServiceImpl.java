package info.getsocial.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import info.getsocial.dao.UserProfileRepository;
import info.getsocial.dao.UserRepository;
import info.getsocial.domain.UserAccount;
import info.getsocial.domain.UserProfile;
import info.getsocial.service.UserProfileService;

@Component
public class UserProfileServiceImpl implements UserProfileService{
	@Autowired 
	UserProfileRepository userProfileRepo;
	
	@Autowired
	UserRepository userRepo;
	
	@Override
	public void saveUserProfile(UserProfile userProfile) {
		userProfileRepo.save(userProfile);	
	}

	@Override
	public UserProfile getUserProfileById(String id) {
		return userProfileRepo.findById(id);
	}

	@Override
	public UserProfile getAuthenticatedUserProfile() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserAccount principal = (UserAccount) authentication.getPrincipal();
		UserAccount loggedInUser = userRepo.findById(principal.getId());
		return userProfileRepo.findById(loggedInUser.getProviderUserId());
	}

	@Override
	public String getAuthenticatedUserProfileId() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserAccount principal = (UserAccount) authentication.getPrincipal();
		UserAccount loggedInUser = userRepo.findById(principal.getId());
		return loggedInUser.getProviderUserId();
	}
	
	

}
