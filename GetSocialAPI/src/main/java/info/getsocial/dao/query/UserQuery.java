package info.getsocial.dao.query;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.QueryBuilder;

public class UserQuery {
	
	public QueryBuilder queryBuilder;
	
	public QueryBuilder userWithName(String name) {
		return queryBuilder.put("name").is(name);
	}
	
	public QueryBuilder hasAgeGreaterThan(Integer age) {
		return queryBuilder.put("age").greaterThan(age);
	}
	
	public QueryBuilder hasHobby(String hobby) {
		return queryBuilder.put("hobbies").elemMatch(new BasicDBObject());
	}
	
	public DBObject build() {
		return queryBuilder.get();
	}
}
