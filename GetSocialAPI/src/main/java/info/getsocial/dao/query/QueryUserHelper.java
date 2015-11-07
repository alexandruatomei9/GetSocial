package info.getsocial.dao.query;

import org.mongodb.morphia.query.Query;

import info.getsocial.domain.User;

public class QueryUserHelper {
	//TODO: get from datastore
	private Query<User> userQuery;

	public Query<User> hasName(String name) {
		return userQuery.field("name").equal(name);
	}

	public Query<User> ageIsGreaterThan(Integer age) {
		return userQuery.field("age").greaterThan(age);
	}

	public Query<User> ageIsLessThan(Integer age) {
		return userQuery.field("age").greaterThan(age);
	}

	public Query<User> hasHobby(String hobby, boolean ignoreCaseSensitive) {
		if (ignoreCaseSensitive) {
			String string = "/^" + hobby + "$/i";
			return userQuery.field("hobbies").hasThisElement(string);
		} else {
			return userQuery.field("hobbies").hasThisElement(hobby);
		}
	}
}
