package info.getsocial.dao;

import java.io.Serializable;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.QueryResults;
import org.mongodb.morphia.query.UpdateOperations;

public interface IDaoMorphia<T, K extends Serializable> extends IDaoBase<T, K> {
	
	Query<T> createQuery();
    
    UpdateOperations<T> createUpdateOperations();
    
    java.lang.Class<T> getEntityClass();
        
    void updateFirst(Query<T> q, UpdateOperations<T> ops);
    
    void update(Query<T> q, UpdateOperations<T> ops);
    
    void deleteById(K id);
    
    void deleteByQuery(Query<T> q);
        
    java.util.List<K> findIds(java.lang.String key, java.lang.Object value);
    
    java.util.List<K> findIds();
    
    java.util.List<K> findIds(Query<T> q);
    
    boolean exists(java.lang.String key, Object value);
    
    boolean exists(Query<T> q);
    
    long count();
    
    long count(String key, Object value);
    
    long count(Query<T> q);
    
    T findOne(String key, java.lang.Object value);
    
    T findOne(Query<T> q);
    
    QueryResults<T> find();
    
    QueryResults<T> find(Query<T> q);
    
    void dropCollection();
    
	Datastore getDatastore();
}
