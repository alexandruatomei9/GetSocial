package info.getsocial.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import info.getsocial.domain.UserMusic;

public interface UserMusicRepository extends JpaRepository<UserMusic, String>{

	UserMusic findById(String id);
}
