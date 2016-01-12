package info.getsocial.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import info.getsocial.domain.UserTeam;

public interface UserTeamRepository extends JpaRepository<UserTeam, String> {
	
	UserTeam findById(String id);
}
