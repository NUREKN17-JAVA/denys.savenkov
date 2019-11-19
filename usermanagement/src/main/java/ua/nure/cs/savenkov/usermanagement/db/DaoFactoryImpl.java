package ua.nure.cs.savenkov.usermanagement.db;

import java.sql.Connection;

import ua.nure.cs.savenkov.usermanagement.User;

public class DaoFactoryImpl extends DaoFactory {
	
	
	@Override
	protected ConnectionFactory getConnectionFactory() {
		return new ConnectionFactoryImpl(properties);
	}

	@Override
	public Dao<User> getUserDao() {
		Dao<User> result = null;
	    try {
	        Class clazz = Class.forName(properties.getProperty(USER_DAO));
	        result = (Dao<User>) clazz.newInstance();
	        result.setConnectionFactory(getConnectionFactory());
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	    return result;
	}

}
