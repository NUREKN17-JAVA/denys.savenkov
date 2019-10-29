package ua.nure.cs.savenkov.usermanagement.db;

import java.util.Collection;

import ua.nure.cs.savenkov.usermanagement.User;

public class HsqldbUserDao implements UserDao<User> {
  
  private ConnectionFactory connectionFactory;
  
  public HsqldbUserDao(ConnectionFactory connectionFactory) {
    this.connectionFactory = connectionFactory;
  }

  @Override
  public User create(User entity) throws DataBaseException {
    // TODO Auto-generated method stub
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