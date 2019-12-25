package ua.nure.cs.savenkov.usermanagement.db;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import ua.nure.cs.savenkov.usermanagement.User;

public class MockUserDao implements UserDao {
	private Long id = (long) 0;
	private Map users = new HashMap();
	
	@ Override
	public User create(User user) throws DataBaseException {
		Long currentId = new Long(++id);
		user.setId(currentId);
		users.put(currentId, user);
		return user;
	}

	@Override
	public void update(User user) throws DataBaseException {
		Long currentId = user.getId();
		users.remove(currentId);
		users.put(currentId, user);
	}

	@Override
	public void delete(User user) throws DataBaseException {
		Long currentId = user.getId();
		users.remove(currentId);		
	}

	@Override
	public User find(Long id) throws DataBaseException {
		return (User) users.get(id);
	}

	@Override
	public Collection findAll() throws DataBaseException {
		return users.values();
	}

	@Override
	public void setConnectionFactory(ConnectionFactory connectionFactory) {
		
	}

	@Override
	public Collection find(String firstName, String lastName) throws DataBaseException {
		throw new UnsupportedOperationException();
	}

}
