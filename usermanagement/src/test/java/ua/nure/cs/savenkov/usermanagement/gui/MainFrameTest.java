package ua.nure.cs.savenkov.usermanagement.gui;

import java.awt.Component;

import javax.swing.JPanel;
import javax.swing.JTable;

import junit.extensions.jfcunit.JFCTestCase;
import junit.extensions.jfcunit.JFCTestHelper;
import junit.extensions.jfcunit.TestHelper;
import junit.extensions.jfcunit.finder.NamedComponentFinder;

public class MainFrameTest extends JFCTestCase {

	private static final String DETAILS_BUTTON_COMPONENT_NAME = "detailsButton";
	private static final String EDIT_BUTTON_COMPONENT_NAME = "editButton";
	private static final String ADD_BUTTON_COMPONENT_NAME = "addButton";
	private static final String USER_TABLE_COMPONENT_NAME = "userTable";
	private static final String BROWSE_PANEL_COMPONENT_NAME = "browsePanel";
	private MainFrame mainFrame;

	protected void setUp() throws Exception {
		super.setUp();
		setHelper(new JFCTestHelper());
		mainFrame = new MainFrame();
		mainFrame.setVisible(true);
	}

	protected void tearDown() throws Exception {
		mainFrame.setVisible(false);
		getHelper();
		TestHelper.cleanUp(this);
		super.tearDown();
	}
	
	protected Component find(Class<?> componentClass, String componentName) {
		NamedComponentFinder finder = new NamedComponentFinder(componentClass, componentName);
		finder.setWait(0);
		Component component = finder.find(mainFrame, 0);
		assertNotNull("Could not find component '" + componentName + "'");
		return component;
	}
	
	public void testBrowseControls() {
		find(JPanel.class, BROWSE_PANEL_COMPONENT_NAME);
		find(JTable.class, USER_TABLE_COMPONENT_NAME);
		find(JPanel.class, ADD_BUTTON_COMPONENT_NAME);
		find(JPanel.class, EDIT_BUTTON_COMPONENT_NAME);
		find(JPanel.class, DETAILS_BUTTON_COMPONENT_NAME);
	}

}