package ua.nure.cs.savenkov.usermanagement.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.management.RuntimeErrorException;

public class ConnectionFactoryImpl implements ConnectionFactory {
	private String url = "jdbc:hsqldb:file:db/usermanagement";
	private String user = "sa";
	private String password ="";
	private String driver = "org.hsqldb.jdbcDriver";

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

}
