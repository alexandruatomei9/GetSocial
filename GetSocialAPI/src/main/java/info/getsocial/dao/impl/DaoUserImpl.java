package info.getsocial.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.QueryResults;
import org.mongodb.morphia.query.UpdateOperations;

import info.getsocial.dao.IDaoUser;
import info.getsocial.domain.User;

public class DaoUserImpl implements IDaoUser {

	@Override
	public Query<User> createQuery() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UpdateOperations<User> createUpdateOperations() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Class<User> getEntityClass() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateFirst(Query<User> q, UpdateOperations<User> ops) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Query<User> q, UpdateOperations<User> ops) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(ObjectId id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteByQuery(Query<User> q) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ObjectId> findIds(String key, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ObjectId> findIds() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ObjectId> findIds(Query<User> q) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean exists(String key, Object value) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean exists(Query<User> q) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long count(String key, Object value) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long count(Query<User> q) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public User findOne(String key, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findOne(Query<User> q) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public QueryResults<User> find() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public QueryResults<User> find(Query<User> q) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void dropCollection() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Datastore getDatastore() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(User domain) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(User domain) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(User domain) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public User get(Serializable id) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
