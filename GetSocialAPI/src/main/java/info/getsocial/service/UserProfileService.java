package info.getsocial.service;

import info.getsocial.domain.UserProfile;

public interface UserProfileService {

	void saveUserProfile(UserProfile userProfile);
	
	UserProfile getUserProfileById(String id);
	
	String getAuthenticatedUserProfileId();
	
	UserProfile getAuthenticatedUserProfile();
}
