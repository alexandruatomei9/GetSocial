package info.getsocial.converter;

import org.springframework.social.facebook.api.User;
import org.springframework.stereotype.Component;

import info.getsocial.domain.UserProfile;

@Component
public class UserToUserProfileTransformer extends Transformer<org.springframework.social.facebook.api.User, UserProfile> {
	
	public UserToUserProfileTransformer() {}
	
	@Override
	public	UserProfile transform(User e) {
		if(e == null) {
			return null;
		}
		UserProfile profile =  new UserProfile();
		profile.setId(e.getId());
		profile.setAge_range(e.getAgeRange());
		profile.setEmail(e.getEmail());
		profile.setFirstName(e.getFirstName());
		profile.setLastName(e.getLastName());
		profile.setInterestedIn(e.getInterestedIn());
		profile.setGender(e.getGender());
		profile.setHometown(e.getHometown());
		profile.setLocale(e.getLocale());
		profile.setWork(e.getWork());
		profile.setInterestedIn(e.getInterestedIn());
		return profile;
	}

	
	
}
