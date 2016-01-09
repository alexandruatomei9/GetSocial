package info.getsocial.dao.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.mongodb.WriteResult;

import info.getsocial.dao.IDaoUser;
import info.getsocial.domain.UserAccount;

@Repository
public class DaoUserImpl implements IDaoUser {

	@Autowired
	private MongoOperations mongoOps;

	private static final String PERSON_COLLECTION = "Person";

	@Override
	public void save(UserAccount domain) {
		this.mongoOps.insert(domain, PERSON_COLLECTION);
	}

	@Override
	public void update(UserAccount domain) {
		this.mongoOps.save(domain, PERSON_COLLECTION);
	}

	@Override
	public void delete(UserAccount domain) {
		this.mongoOps.remove(domain);
	}

	@Override
	public UserAccount get(Serializable id) {
		Query query = new Query(Criteria.where("_id").is(id));
		return this.mongoOps.findOne(query, UserAccount.class, PERSON_COLLECTION);
	}

	@Override
	public int deleteById(String id) {
		Query query = new Query(Criteria.where("_id").is(id));
		WriteResult result = this.mongoOps.remove(query, UserAccount.class, PERSON_COLLECTION);
		return result.getN();
	}
}
