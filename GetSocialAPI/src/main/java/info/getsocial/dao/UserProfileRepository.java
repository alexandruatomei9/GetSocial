package info.getsocial.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import info.getsocial.domain.UserProfile;

public interface UserProfileRepository extends JpaRepository<UserProfile, String>{
	
	UserProfile findById(String id);
}
