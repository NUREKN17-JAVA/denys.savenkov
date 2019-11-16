package ua.nure.cs.savenkov.usermanagement.db;

import java.io.IOException;
import java.util.Properties;


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
}
