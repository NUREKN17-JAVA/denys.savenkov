package ua.nure.cs.savenkov.usermanagement.db;

import java.io.IOException;
import java.util.Properties;

import sun.security.jca.GetInstance.Instance;
import ua.nure.cs.savenkov.usermanagement.User;


public class DaoFactory {
	private static final String SETTING_PROPERTIES = "settings.properties";
	private static final String CONNECTION_DRIVER = "connection.driver";
	private static final String CONNECTION_URL = "connection.url";
	private static final String CONNECTION_PASSWORD = "connection.password";
	private static final String CONNECTION_USER = "connection.user";
	private static final String USER_DAO = "dao.usermanagement.db.UserDao";
	private final Properties properties;
	
	private static final DaoFactory INSTANCE = new DaoFactory();
	
	public DaoFactory() {
		properties = new Properties();
		try {
			properties.load(getClass().getClassLoader().getResourceAsStream(SETTING_PROPERTIES));
		}
		catch (IOException e){
			throw new RuntimeException(e);
		}
	}
	
	public static DaoFactory getInstance() {
        return INSTANCE;
    }
	
	private ConnectionFactory getConnectionFactory() {
		String user = properties.getProperty(CONNECTION_USER);
		String password = properties.getProperty(CONNECTION_PASSWORD);
		String url  = properties.getProperty(CONNECTION_URL);
		String driver = properties.getProperty(CONNECTION_DRIVER);
		return new ConnectionFactoryImpl(driver, url, user, password);
	}
	
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
