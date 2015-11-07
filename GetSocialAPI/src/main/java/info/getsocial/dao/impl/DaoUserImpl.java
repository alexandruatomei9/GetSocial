package info.getsocial.dao.impl;

import java.io.Serializable;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.mongodb.WriteResult;

import info.getsocial.dao.IDaoUser;
import info.getsocial.domain.User;

public class DaoUserImpl implements IDaoUser {

	private MongoOperations mongoOps;
	private static final String PERSON_COLLECTION = "Person";

	@Override
	public void save(User domain) {
		this.mongoOps.insert(domain, PERSON_COLLECTION);
	}

	@Override
	public void update(User domain) {
		this.mongoOps.save(domain, PERSON_COLLECTION);
	}
	
	@Override
	public void delete(User domain) {
		this.mongoOps.remove(domain);
	}

	@Override
	public User get(Serializable id) {
		Query query = new Query(Criteria.where("_id").is(id));
		return this.mongoOps.findOne(query, User.class, PERSON_COLLECTION);
	}

	@Override
	public int deleteById(String id) {
		Query query = new Query(Criteria.where("_id").is(id));
		WriteResult result = this.mongoOps.remove(query, User.class, PERSON_COLLECTION);
		return result.getN();
	}
}
