package ua.nure.cs.savenkov.usermanagement.db;

import java.io.IOException;
import java.util.Properties;

import ua.nure.cs.savenkov.usermanagement.User;


public class DaoFactory {
	private final Properties properties;
	
	public DaoFactory() {
		properties = new Properties();
		try {
			properties.load(getClass().getClassLoader().getResourceAsStream("setting.properties"));
		}
		catch (IOException e){
			throw new RuntimeException(e);
		}
	}
	
	private ConnectionFactory getConnectionFactory() {
		String user = properties.getProperty("connection.user");
		String password = properties.getProperty("connection.password");
		String url  = properties.getProperty("connection.url");
		String driver = properties.getProperty("connection.driver");
		return new ConnectionFactoryImpl(driver, url, user, password);
	}
	
//	public UserDao<User> getUserDao() throws ReflectiveOperationException {
//		UserDao<User> userDao = null;
//        try {
//            Class UserDaoClass = Class.forName(properties.getProperty("dao.UserDao"));
//            userDao = (UserDao<User>) UserDaoClass.newInstance();
//            userDao.setConnectionFactory(getConnectionFactory());
//        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
//            throw new ReflectiveOperationException(e);
//        }
//
//        return userDao;
//	}
}
