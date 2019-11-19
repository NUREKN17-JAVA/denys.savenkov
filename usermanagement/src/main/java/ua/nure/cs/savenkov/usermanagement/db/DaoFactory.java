package ua.nure.cs.savenkov.usermanagement.db;

import java.io.IOException;
import java.util.Properties;

import sun.security.jca.GetInstance.Instance;
import ua.nure.cs.savenkov.usermanagement.User;


public abstract class DaoFactory {
	private static final String SETTING_PROPERTIES = "settings.properties";
	private static final String CONNECTION_DRIVER = "connection.driver";
	private static final String CONNECTION_URL = "connection.url";
	private static final String CONNECTION_PASSWORD = "connection.password";
	private static final String CONNECTION_USER = "connection.user";
	protected static final String USER_DAO = "dao.UserDao";
	private static final String DAO_FACTORY = "dao.factory";
	
	protected static Properties properties;
	protected static DaoFactory instance;
	
	protected DaoFactory() {}
	
	static {
		properties = new Properties();
		try {
			properties.load(DaoFactory.class.getClass().getClassLoader().getResourceAsStream(SETTING_PROPERTIES));
		}
		catch (IOException e){
			throw new RuntimeException(e);
		}
	}
	
	public static synchronized DaoFactory getInstance() {
		if (instance == null){
	    		try {
				Class factoryClass = Class.forName(properties
						.getProperty(DAO_FACTORY));
				instance = (DaoFactory) factoryClass.newInstance();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
	   	}
	       return instance;
	}
	
	protected abstract ConnectionFactory getConnectionFactory();
//	String user = properties.getProperty(CONNECTION_USER);
//	String password = properties.getProperty(CONNECTION_PASSWORD);
//	String url  = properties.getProperty(CONNECTION_URL);
//	String driver = properties.getProperty(CONNECTION_DRIVER);
//	return new ConnectionFactoryImpl(driver, url, user, password);
	
	public abstract Dao<User> getUserDao();
	
	
	public void init(Properties properties) {
		this.properties = properties;
		instance = null;
	}
}
