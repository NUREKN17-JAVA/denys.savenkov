package ua.nure.cs.savenkov.usermanagement.db;

import java.util.Collection;

import ua.nure.cs.savenkov.usermanagement.User;

public interface UserDao {
	User create(User user) throws DataBaseException;
	
	void update(User user) throws DataBaseException;
	
	void delete(User user) throws DataBaseException;
	
	User find(Long id) throws DataBaseException;
	
	Collection<User> findAll() throws DataBaseException;
}
