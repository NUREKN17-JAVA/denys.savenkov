package ua.nure.cs.savenkov.usermanagement.db;

import java.util.Date;

import org.dbunit.DatabaseTestCase;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.XmlDataSet;

import ua.nure.cs.savenkov.usermanagement.User;

public class HsqldbUserDaoTest extends DatabaseTestCase {

	  private static final String LASTNAME = "Doe";
	  private static final String FIRSTNAME = "John";
	  private HsqldbUserDao dao;
	  private ConnectionFactory connectionFactory;
	  
	  public void testCreate() throws DataBaseException {
	    try {
			User user = new User();
			user.setFirstName(FIRSTNAME);
			user.setLastName(LASTNAME);
			user.setDateOfBirth(new Date());
			assertNull(user.getId());
			user = dao.create(user);
			assertNotNull(user);
			assertNotNull(user.getId());
		} catch (DataBaseException e) {
			e.printStackTrace();
			fail(e.toString());
		}
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
		IDataSet dataSet = new XmlDataSet(
				getClass().getClassLoader().getResourceAsStream("usersDataSet.xml"));
	    return null;
	  }

	}