package info.getsocial.dao;

import java.io.Serializable;

/**
 * Base interface for CRUD operations and common queries
 */
public interface IDaoBase<T> {
          
    public void save(T domain);
         
    public void update(T domain);
         
    public void delete(T domain);
     
    public T get(Serializable id);
    
    public int deleteById(String id);

}