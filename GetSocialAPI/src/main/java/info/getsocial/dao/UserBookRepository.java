package info.getsocial.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import info.getsocial.domain.UserBook;

public interface UserBookRepository extends JpaRepository<UserBook, String>{
	
	UserBook findById(String id);
}
