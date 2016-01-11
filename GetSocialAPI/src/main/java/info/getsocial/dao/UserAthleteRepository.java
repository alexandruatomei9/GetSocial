package info.getsocial.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import info.getsocial.domain.UserAthlete;

public interface UserAthleteRepository extends JpaRepository<UserAthlete, String>{

	UserAthlete findById(String id);
}
