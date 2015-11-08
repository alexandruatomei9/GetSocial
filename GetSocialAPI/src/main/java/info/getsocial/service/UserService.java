package info.getsocial.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import info.getsocial.dao.IDaoUser;
import info.getsocial.domain.User;
import info.getsocial.domain.exception.GetSocialException;

@Service
public class UserService {

	@Autowired
	IDaoUser userDao;
	
	public User getUser(String id) throws GetSocialException{
		try{
			User user = userDao.get(id);
			return user;
		} catch (Exception ex) {
			throw new GetSocialException(404);
		} 
	}
	
	public void saveUser(String id, String name, String email, String password) throws GetSocialException{
		try {
			User user = new User(id, name, email, password);
			userDao.save(user);
		} catch (Exception ex) {
			throw new GetSocialException(500);
		}	
	}
	
	public void deleteUser( User user ) throws GetSocialException {
		try {
			userDao.delete(user);
		} catch(Exception ex) {
			throw new GetSocialException(500);
		}
	}
	
	public void updateUser( User user ) throws GetSocialException {
		try {
			userDao.update(user);
		} catch(Exception ex) {
			throw new GetSocialException(500);
		}
	}
}
