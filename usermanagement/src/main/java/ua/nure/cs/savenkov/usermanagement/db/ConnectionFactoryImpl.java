package ua.nure.cs.savenkov.usermanagement.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.management.RuntimeErrorException;

public class ConnectionFactoryImpl implements ConnectionFactory {
	private String url;
	private String user;
	private String password;
	private String driver;

	public ConnectionFactoryImpl(String driver, String url, String user, String password) {
		this.driver = driver;
		this.url = url;
		this.user = user;
		this.password = password; 
	}

	@Override
	public Connection createConnection() throws DataBaseException {
	    try {
	      Class.forName(driver);
	    } catch (ClassNotFoundException e) {
	      throw new RuntimeException(e);
	    }
	    try {
	      return DriverManager.getConnection(url, user, password);
	    } catch (SQLException e) {
	      throw new DataBaseException(e);
	    }
	}
	
	private ConnectionFactory getConnection() {
		//String user = proper
		return null;
	}

}
