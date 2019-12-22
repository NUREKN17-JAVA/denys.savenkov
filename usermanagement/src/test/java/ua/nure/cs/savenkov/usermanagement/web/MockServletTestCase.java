package ua.nure.cs.savenkov.usermanagement.web;

import java.util.Properties;

import com.mockobjects.dynamic.Mock;
import com.mockrunner.servlet.BasicServletTestCaseAdapter;

import ua.nure.cs.savenkov.usermanagement.db.DaoFactory;
import ua.nure.cs.savenkov.usermanagement.db.MockDaoFactory;

public class MockServletTestCase extends BasicServletTestCaseAdapter {
	
	
	private static final String DAO_FACTORY = "dao.factory";
	private Mock mockUserDao;
	
	public Mock getMockUserDao() {
		return mockUserDao;
	}

	public void setMockUserDao(Mock mockUserDao) {
		this.mockUserDao = mockUserDao;
	}

	protected void setUp() throws Exception {
		super.setUp();
		Properties properties = new Properties();
		properties.setProperty(DAO_FACTORY, MockDaoFactory.class.getName());
		DaoFactory.init(properties);
		setMockUserDao(((MockDaoFactory)DaoFactory.getInstance()).getMockUserDao());
	}

	protected void tearDown() throws Exception {
		getMockUserDao().verify();
		super.tearDown();
	}

}
