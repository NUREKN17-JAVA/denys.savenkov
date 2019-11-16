package ua.nure.cs.savenkov.usermanagement.db;

import java.util.Collection;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ua.nure.cs.savenkov.usermanagement.User;



public class HsqldbUserDao implements UserDao<User> {
  
  private ConnectionFactory connectionFactory;
  private static final String CALL_IDENTITY = "call IDENTITY()";
  private static final String INSERT_QUERY = "INSERT INTO users (firstname, lastname, dateofbirth) VALUES (?,?,?)";
  
  public HsqldbUserDao(ConnectionFactory connectionFactory) {
    this.connectionFactory = connectionFactory;
  }

  @Override
  public User create(User entity) throws DataBaseException {
    try {
        Connection connection = connectionFactory.createConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY);
		preparedStatement.setString(1, entity.getFirstName());
		preparedStatement.setString(2, entity.getLastName());
		preparedStatement.setDate(3, new Date(entity.getDateOfBirth().getTime()));
		int numberOfRows = preparedStatement.executeUpdate();
		if(numberOfRows != 1) {
			throw new DataBaseException("Number of rows: " + numberOfRows);
		}
		CallableStatement callableStatement = connection.prepareCall(CALL_IDENTITY);
		ResultSet keys = callableStatement.executeQuery();
		if (keys.next()) {
			entity.setId(new Long(keys.getLong(1)));
		}
		keys.close();
		callableStatement.close();
		preparedStatement.close();
		connection.close();
		return entity;
	} catch(DataBaseException e) {
		throw e;
	} catch (SQLException e) {
		throw new DataBaseException(e);
	}
  }

  @Override
  public void update(User entity) throws DataBaseException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void delete(User entity) throws DataBaseException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public User find(Long id) throws DataBaseException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Collection<User> findAll() throws DataBaseException {
    // TODO Auto-generated method stub
    return null;
  }

}