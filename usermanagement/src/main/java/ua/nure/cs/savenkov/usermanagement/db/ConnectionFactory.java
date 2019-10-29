package ua.nure.cs.savenkov.usermanagement.db;

import java.sql.Connection;

public interface ConnectionFactory {
	Connection createConnection() throws DataBaseException;
	
}
