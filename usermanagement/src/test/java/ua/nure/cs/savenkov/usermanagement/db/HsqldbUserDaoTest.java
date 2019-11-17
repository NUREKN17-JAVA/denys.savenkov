package ua.nure.cs.savenkov.usermanagement.db;


import java.util.Collection;
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
	  private static final Long TEST_ID = 666L;
	  private HsqldbUserDao dao;
	  private ConnectionFactory connectionFactory;
	  
	  private static final String CONNECTION_USER = "sa";
	  private static final String CONNECTION_PASSWORD = "";
	  private static final String CONNECTION_URL = "jdbc:hsqldb:file:db/usermanagement";
	  private static final String CONNECTION_DRIVER = "org.hsqldb.jdbcDriver";
	  
	  private User user;
	  
	  public void testCreate() throws DataBaseException {
	    try {
			assertNotNull(user.getId());
			
			
			User check = dao.create(user);

            assertNotNull(check);
			
            assertEquals(user.getFirstName(), check.getFirstName());
            assertEquals(user.getLastName(), check.getLastName());
            assertEquals(user.getDateOfBirth(), check.getDateOfBirth());
		} catch (DataBaseException e) {
			e.printStackTrace();
			fail(e.toString());
		}
	  }
	  
	  public void testFindAll() throws DataBaseException {
	        Collection<User> items = dao.findAll();
	        assertNotNull(items);
	        assertEquals("Collection size doesn't match ethalon.", 2, items.size());
	    }
	  
	  protected void setUp() throws Exception {
	    super.setUp();

        this.user = new User();
        this.user.setId(TEST_ID);
        this.user.setFirstName(FIRSTNAME);
        this.user.setLastName(LASTNAME);
        this.user.setDateOfBirth(new Date());

        this.dao = new HsqldbUserDao(connectionFactory);
	  }

	  @Override
	  protected IDatabaseConnection getConnection() throws Exception {
		connectionFactory = new ConnectionFactoryImpl(CONNECTION_DRIVER, CONNECTION_URL, CONNECTION_USER, CONNECTION_PASSWORD);
	    return new DatabaseConnection(connectionFactory.createConnection());
	  }
//
//	  public ConnectionFactory getConnectionFactory() {
//		return connectionFactory;
//	}

//	public void setConnectionFactory(ConnectionFactory connectionFactory) {
//		this.connectionFactory = connectionFactory;
//	}

	@Override
	  protected IDataSet getDataSet() throws Exception {
		IDataSet dataSet = new XmlDataSet(
				getClass().getClassLoader().getResourceAsStream("usersDataSet.xml"));
	    return dataSet;
	  }

	}