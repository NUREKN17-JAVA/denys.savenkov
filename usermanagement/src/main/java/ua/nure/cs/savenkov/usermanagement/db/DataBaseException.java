package ua.nure.cs.savenkov.usermanagement.db;

import java.sql.SQLException;

public class DataBaseException extends Exception {

  public DataBaseException(Exception e) {
    super(e);
  }
  
  public DataBaseException(String string) {
	  // TODO: Auto-generated constructor stub
  }

}