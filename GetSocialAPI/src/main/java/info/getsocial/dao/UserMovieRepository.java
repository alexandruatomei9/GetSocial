package info.getsocial.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import info.getsocial.domain.UserMovie;

public interface UserMovieRepository extends JpaRepository<UserMovie, String> {
	
	UserMovie findById(String id);
}
