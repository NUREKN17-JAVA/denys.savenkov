package ua.nure.cs.savenkov.usermanagement.db;

import java.util.Date;

import org.dbunit.DatabaseTestCase;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;

import ua.nure.cs.savenkov.usermanagement.User;

public class HsqldbUserDaoTest extends DatabaseTestCase {

	  private static final String LASTNAME = "Titov";
	  private static final String FIRSTNAME = "John";
	  private HsqldbUserDao dao;
	  private ConnectionFactory connectionFactory;
	  
	  public void testCreate() throws DataBaseException {
	    User user = new User();
	    user.setFirstName(FIRSTNAME);
	    user.setLastName(LASTNAME);
	    user.setDateOfBirth(new Date());
	    assertNull(user.getId());
	    User userTOCheck = (User) dao.create(user);
	    assertNotNull(userTOCheck);
	    assertNotNull(userTOCheck.getId());
	  }

	  protected void setUp() throws Exception {
	    super.setUp();
	    dao = new HsqldbUserDao(connectionFactory);
	  }

	  @Override
	  protected IDatabaseConnection getConnection() throws Exception {
	    connectionFactory = new ConnectionFactoryImpl();
	    return new DatabaseConnection(connectionFactory.createConnection());
	  }

	  @Override
	  protected IDataSet getDataSet() throws Exception {
	    // TODO Auto-generated method stub
	    return null;
	  }

	}