package ua.nure.cs.savenkov.usermanagement.db;

import java.util.Collection;

import ua.nure.cs.savenkov.usermanagement.User;

public class HsqldbUserDao implements UserDao {
	
	private ConnectionFactory connectionFactory;
	
	@Override
	public User create(User user) throws DataBaseException {
		// TODO Auto-generated method stub
		return null;
	}
	
	public HsqldbUserDao(ConnectionFactory connectionFactory) {
		this.connectionFactory = connectionFactory;
	}

	@Override
	public void update(User user) throws DataBaseException {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(User user) throws DataBaseException {
		// TODO Auto-generated method stub

	}

	@Override
	public User find(Long id) throws DataBaseException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<User> findAll() throws DataBaseException {
		// TODO Auto-generated method stub
		return null;
	}

}
