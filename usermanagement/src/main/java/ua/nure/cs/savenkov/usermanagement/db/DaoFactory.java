package ua.nure.cs.savenkov.usermanagement.db;

import java.io.IOException;
import java.util.Properties;

import sun.security.jca.GetInstance.Instance;
import ua.nure.cs.savenkov.usermanagement.User;


public class DaoFactory {
	private static final String USER_DAO = "dao.usermanagement.db.UserDao";
	private final Properties properties;
	
	private final static DaoFactory INSTANCE = new DaoFactory();
	
	public static DaoFactory getInstance() {
		return INSTANCE;
	}
	
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
	
	public Dao<User> getUserDao() {
		Dao<User> result = null;
        try {
            Class clazz = Class.forName(properties.getProperty(USER_DAO));
            Dao<User> dao = (Dao<User>) clazz.newInstance();
            dao.setConnectionFactory(getConnectionFactory());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
	}
}
