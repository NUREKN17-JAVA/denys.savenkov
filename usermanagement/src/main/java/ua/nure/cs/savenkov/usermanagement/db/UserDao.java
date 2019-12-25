package ua.nure.cs.savenkov.usermanagement.db;

import java.util.Collection;

import ua.nure.cs.savenkov.usermanagement.User;


public interface UserDao {
//    T create(T entity) throws DataBaseException;
//
//    void update(T entity) throws DataBaseException;
//
//    void delete(T entity) throws DataBaseException;
//
//    T find(Long id) throws DataBaseException;
//
//    Collection<T> findAll() throws DataBaseException;
//    
//    void setConnectionFactory(ConnectionFactory connectionFactory);
	   User create(User entity) throws DataBaseException;

	   void update(User entity) throws DataBaseException;

	   void delete(User entity) throws DataBaseException;

	   User find(Long id) throws DataBaseException;

	   Collection<User> findAll() throws DataBaseException;
	   
	   Collection find(String firstName, String lastName) throws DataBaseException;
	    
	   void setConnectionFactory(ConnectionFactory connectionFactory);
}