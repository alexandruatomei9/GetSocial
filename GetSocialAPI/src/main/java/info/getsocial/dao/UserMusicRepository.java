package info.getsocial.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import info.getsocial.domain.UserMusic;
import info.getsocial.domain.UserProfile;

public interface UserMusicRepository extends JpaRepository<UserMusic, String>{

	UserMusic findById(String id);
	
	//UserMusic findByUserProfile(UserProfile userProfile);
}
