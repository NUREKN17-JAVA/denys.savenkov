package ua.nure.cs.savenkov.usermanagement.gui;

import java.awt.Component;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.mockobjects.dynamic.Mock;

import javafx.scene.input.DataFormat;
import junit.extensions.jfcunit.JFCTestCase;
import junit.extensions.jfcunit.JFCTestHelper;
import junit.extensions.jfcunit.TestHelper;
import junit.extensions.jfcunit.eventdata.MouseEventData;
import junit.extensions.jfcunit.eventdata.StringEventData;
import junit.extensions.jfcunit.finder.NamedComponentFinder;
import junit.framework.TestCase;
import ua.nure.cs.savenkov.usermanagement.User;
import ua.nure.cs.savenkov.usermanagement.db.DaoFactory;
import ua.nure.cs.savenkov.usermanagement.db.DaoFactoryImpl;
import ua.nure.cs.savenkov.usermanagement.db.MockDaoFactory;
import ua.nure.cs.savenkov.usermanagement.db.MockUserDao;

public class MainFrameTest extends JFCTestCase {

	private static final String FIND_ALL_COMMAND = "findAll";
	private static final String DATE_OF_BIRTH_FIELD_COMPONENT_NAME = "dateOfBirthField";
	private static final String LAST_NAME_FIELD_COMPONENT_NAME = "lastNameField";
	private static final String FIRST_NAME_FIELD_COMPONENT_NAME = "firstNameField";
	private static final String ADD_PANEL_COMPONENT_NAME = "addPanel";
	private static final String DETAILS_BUTTON_COMPONENT_NAME = "detailsButton";
	private static final String EDIT_BUTTON_COMPONENT_NAME = "editButton";
	private static final String ADD_BUTTON_COMPONENT_NAME = "addButton";
	private static final String DELETE_BUTTON_COMPONENT_NAME = "deleteButton";
	private static final String USER_TABLE_COMPONENT_NAME = "userTable";
	private static final String BROWSE_PANEL_COMPONENT_NAME = "browsePanel";
	private static final String OK_BUTTON_COMPONENT_NAME = "okButton";
	private static final String CANCEL_BUTTON_COMPONENT_NAME = "cancelButton";
	private static final String FIRST_NAME = "John";
	private static final String LAST_NAME = "Doe";
	private static final Date DATE_OF_BIRTH = new Date();
	private static final String DEFAULT_NAME = "Sam";
	
	private static final String DEFAULT_SURNAME = "Willson";
	private MainFrame mainFrame;
	private Mock mockUserDao;
	private List<User> users;
	
	protected void setUp() throws Exception {
		super.setUp();
		
		try {
			Properties properties = new Properties();
			properties.setProperty("dao.factory", MockDaoFactory.class.getName());
			DaoFactory.getInstance().init(properties);
			mockUserDao = ((MockDaoFactory) DaoFactory.getInstance()).getMockUserDao();
			mockUserDao.expectAndReturn(FIND_ALL_COMMAND, new ArrayList());
			setHelper(new JFCTestHelper());
			mainFrame = new MainFrame();
		} catch (Exception e) {
			e.printStackTrace();
		}
		mainFrame.setVisible(true);
	}

	protected void tearDown() throws Exception {
		try {
			mockUserDao.verify();
			mainFrame.setVisible(false);
			getHelper().cleanUp(this);
			super.tearDown();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected Component find(Class<?> componentClass, String componentName) {
		NamedComponentFinder finder = new NamedComponentFinder(componentClass, componentName);
		finder.setWait(0);
		Component component = finder.find(mainFrame, 0);
		assertNotNull("Could not find component '" + componentName + "'", component);
		return component;
	}
	
	public void testBrowseControls() {
		find(JPanel.class, BROWSE_PANEL_COMPONENT_NAME);
		JTable table = (JTable)find(JTable.class, USER_TABLE_COMPONENT_NAME);
		assertEquals(3, table.getColumnCount());
		assertEquals("ID", table.getColumnName(0));
		assertEquals("Имя", table.getColumnName(1));
		assertEquals("Фамилия", table.getColumnName(2));
		
		find(JButton.class, ADD_BUTTON_COMPONENT_NAME);
		find(JButton.class, EDIT_BUTTON_COMPONENT_NAME);
		find(JButton.class, DETAILS_BUTTON_COMPONENT_NAME);
		find(JButton.class, DELETE_BUTTON_COMPONENT_NAME);
	}
	
	public void testAddUserOk() {
		find(JPanel.class, BROWSE_PANEL_COMPONENT_NAME);
		JTable table = (JTable)find(JTable.class, USER_TABLE_COMPONENT_NAME);
		assertEquals(0, table.getRowCount());
		
		JButton addButton = (JButton) find(JButton.class, ADD_BUTTON_COMPONENT_NAME);
		getHelper().enterClickAndLeave(new MouseEventData(this, addButton));
		find(JPanel.class, ADD_PANEL_COMPONENT_NAME);
		
		fillFields(FIRST_NAME, LAST_NAME, DATE_OF_BIRTH);
		JButton okButton = (JButton) find(JButton.class, OK_BUTTON_COMPONENT_NAME);
		find(JButton.class, CANCEL_BUTTON_COMPONENT_NAME);
		
		
		
		getHelper().enterClickAndLeave(new MouseEventData(this, okButton));
		
		find(JPanel.class, BROWSE_PANEL_COMPONENT_NAME);
		table = (JTable)find(JTable.class, USER_TABLE_COMPONENT_NAME);
		assertEquals(1, table.getRowCount());
	}
	
	public void testAddUserCancel() {
		find(JPanel.class, BROWSE_PANEL_COMPONENT_NAME);
		JTable table = (JTable)find(JTable.class, USER_TABLE_COMPONENT_NAME);
		assertEquals(0, table.getRowCount());
		
		JButton addButton = (JButton) find(JButton.class, ADD_BUTTON_COMPONENT_NAME);
		getHelper().enterClickAndLeave(new MouseEventData(this, addButton));
		find(JPanel.class, ADD_PANEL_COMPONENT_NAME);
		
		fillFields(FIRST_NAME, LAST_NAME, DATE_OF_BIRTH);
		JButton cancelButton = (JButton) find(JButton.class, CANCEL_BUTTON_COMPONENT_NAME);
		find(JButton.class, CANCEL_BUTTON_COMPONENT_NAME);
		
		
		
		getHelper().enterClickAndLeave(new MouseEventData(this, cancelButton));
		
		find(JPanel.class, BROWSE_PANEL_COMPONENT_NAME);
		table = (JTable)find(JTable.class, USER_TABLE_COMPONENT_NAME);
		assertEquals(0, table.getRowCount());
	}


	private void fillFields(String firstName, String lastName, Date dateOfBirth) {
		JTextField firstNameField = (JTextField) find(JTextField.class, FIRST_NAME_FIELD_COMPONENT_NAME);
		JTextField lastNameField = (JTextField) find(JTextField.class, LAST_NAME_FIELD_COMPONENT_NAME);
		JTextField dateOfBirthField = (JTextField) find(JTextField.class, DATE_OF_BIRTH_FIELD_COMPONENT_NAME);
		
		getHelper().sendString(new StringEventData(this, firstNameField, firstName));
		getHelper().sendString(new StringEventData(this, lastNameField, lastName));
		String dateString = DateFormat.getInstance().format(dateOfBirth);
		getHelper().sendString(new StringEventData(this, dateOfBirthField, dateString));
	}
	

}
