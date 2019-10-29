package ua.nure.cs.savenkov.usermanagement.db;

import java.util.Collection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import ua.nure.cs.savenkov.usermanagement.User;



public class HsqldbUserDao implements UserDao<User> {
  
  private ConnectionFactory connectionFactory;
  private static final String INSERT_QUERY = "insert into users (firstname, lastname, dateofbirth) values (?,?,?)";
  
  public HsqldbUserDao(ConnectionFactory connectionFactory) {
    this.connectionFactory = connectionFactory;
  }

  @Override
  public User create(User entity) throws DataBaseException {
    Connection connection = connectionFactory.createConnection();
    try {
		PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY);
		preparedStatement.setString(1, entity.getFirstName());
		preparedStatement.setString(2, entity.getLastName());
		preparedStatement.setDate(3, new Date(entity.getDateOfBirth().getTime()));
		int numberOfRows = preparedStatement.executeUpdate();
		if(numberOfRows != 1) {
			throw new DataBaseException("Number of rows: " + numberOfRows)
		}
	} catch (SQLException e) {
		throw new DataBaseException(e)
	} catch(DataBaseException e) {
		throw e;
	}
	return null;
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