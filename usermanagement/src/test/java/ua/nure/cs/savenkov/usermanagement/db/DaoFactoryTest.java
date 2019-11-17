package ua.nure.cs.savenkov.usermanagement.db;

import junit.framework.TestCase;
import ua.nure.cs.savenkov.usermanagement.User;

public class DaoFactoryTest extends TestCase {

	public void testGetUserDao() {
			try {
				DaoFactory daoFactory = DaoFactory.getInstance();
				assertNotNull("DaoFactory instance is null", daoFactory);
				Dao<User> userDao = daoFactory.getUserDao();
				assertNotNull("Dao<User> is null", userDao);
			} catch (RuntimeException e) {
				e.printStackTrace();
				fail(e.toString());
			}
	}

}
