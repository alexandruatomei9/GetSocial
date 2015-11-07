package info.getsocial.dao;

import org.bson.types.ObjectId;

import info.getsocial.domain.User;

public interface IDaoUser extends IDaoMorphia<User, ObjectId> {

}
