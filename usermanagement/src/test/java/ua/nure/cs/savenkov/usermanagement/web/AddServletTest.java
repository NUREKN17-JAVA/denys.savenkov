package ua.nure.cs.savenkov.usermanagement.web;

import java.text.DateFormat;
import java.util.Date;

import ua.nure.cs.savenkov.usermanagement.User;

public class AddServletTest extends MockServletTestCase {
    private static final String ERROR_ATTRIBUTE = "error";
    private static final String OK_BUTTON = "Ok";
    private static final String OK_BUTTON_PARAMETER = "okButton";
    private static final String DATE_PARAMETER = "date";
    private static final String LAST_NAME_PARAMETER = "lastName";
    private static final String FIRST_NAME_PARAMETER = "firstName";
    private static final String ID_VALUE = "1000";
    private static final String ID_PARAMETER = "id";
    private static final String CREATE_USER = "create";
    private static final String LAST_NAME = "Doe";
    private static final String FIRST_NAME = "John";

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        createServlet(AddServlet.class);
    }

    public void testAdd() {
        Date date = new Date();
        User newUser = new User(FIRST_NAME, LAST_NAME, new Date());
        User user = new User(1000L, FIRST_NAME, LAST_NAME, new Date());
        getMockUserDao().expect(CREATE_USER, user);

        addRequestParameter(ID_PARAMETER, ID_VALUE);
        addRequestParameter(FIRST_NAME_PARAMETER, FIRST_NAME);
        addRequestParameter(LAST_NAME_PARAMETER, LAST_NAME);
        addRequestParameter(DATE_PARAMETER, DateFormat.getInstance().format(date));
        addRequestParameter(OK_BUTTON_PARAMETER, OK_BUTTON);
        doPost();
    }

    public void testAddEmptyFirstName() {
        Date date = new Date();
        addRequestParameter(LAST_NAME_PARAMETER, LAST_NAME);
        addRequestParameter(DATE_PARAMETER, DateFormat.getInstance().format(date));
        addRequestParameter(OK_BUTTON_PARAMETER, OK_BUTTON);
        doPost();
        String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute(ERROR_ATTRIBUTE);
        assertNotNull("Could not find error message in session scope", errorMessage);
    }

    public void testAddEmptyLastName() {
        Date date = new Date();
        addRequestParameter(FIRST_NAME_PARAMETER, FIRST_NAME);
        addRequestParameter(DATE_PARAMETER, DateFormat.getInstance().format(date));
        addRequestParameter(OK_BUTTON_PARAMETER, OK_BUTTON);
        doPost();
        String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute(ERROR_ATTRIBUTE);
        assertNotNull("Could not find error message in session scope", errorMessage);
    }

    public void testAddEmptyDate() {
        Date date = new Date();
        addRequestParameter(FIRST_NAME_PARAMETER, FIRST_NAME);
        addRequestParameter(LAST_NAME_PARAMETER, LAST_NAME);
        addRequestParameter(OK_BUTTON_PARAMETER, OK_BUTTON);
        doPost();
        String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute(ERROR_ATTRIBUTE);
        assertNotNull("Could not find error message in session scope", errorMessage);
    }

    public void testAddEmptyDateIncorrect() {
        Date date = new Date();
        addRequestParameter(FIRST_NAME_PARAMETER, FIRST_NAME);
        addRequestParameter(LAST_NAME_PARAMETER, LAST_NAME);
        addRequestParameter(DATE_PARAMETER, "aaaaaaaaaaaaaaa");
        addRequestParameter(OK_BUTTON_PARAMETER, OK_BUTTON);
        doPost();
        String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute(ERROR_ATTRIBUTE);
        assertNotNull("Could not find error message in session scope", errorMessage);
    }

}
