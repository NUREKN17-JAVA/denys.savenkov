package ua.nure.cs.savenkov.usermanagement;

import java.util.Calendar;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import junit.framework.TestCase;

class UserTest extends TestCase {

	private User user;
		
	// for testGetAge()
	private static final int DAY_OF_BIRTH = 9;
	private static final int MONTH_OF_BIRTH = Calendar.JANUARY; // or 1
	private static final int YEAR_OF_BIRTH = 2001;
	private static final int ETHALON_AGE = 18;
	
	// for testGetAgeToday()
	private static final int DAY_OF_BIRTH_TODAY = 27;
	private static final int MONTH_OF_BIRTH_TODAY = Calendar.OCTOBER;
	private static final int YEAR_OF_BIRTH_TODAY = 1994;
	private static final int ETHALON_AGE_TODAY = 25;
	
	// for testGetAgeTomorrow()
	private static final int DAY_OF_BIRTH_TOMORROW = 28;
	private static final int MONTH_OF_BIRTH_TOMORROW = Calendar.OCTOBER;
	private static final int YEAR_OF_BIRTH_TOMORROW = 1994;
	private static final int ETHALON_AGE_TOMORROW = 24;

	// for testGetAgeNextMonth()
	private static final int DAY_OF_BIRTH_NEXT_MONTH = 27;
	private static final int MONTH_OF_BIRTH_NEXT_MONTH = Calendar.NOVEMBER;
	private static final int YEAR_OF_BIRTH_NEXT_MONTH = 1994;
	private static final int ETHALON_AGE_NEXT_MONTH = 24;
	
	// for testGetAgeNotBorn()
	private static final int DAY_OF_BIRTH_NOT_BORN = 1;
	private static final int MONTH_OF_BIRTH_NOT_BORN = Calendar.JANUARY;
	private static final int YEAR_OF_BIRTH_NOT_BORN = 2020;
	
	// tested on 27 Oct. 2019
	
	@BeforeEach
	protected void setUp() throws Exception {
		super.setUp();
		user = new User();
	}
	
	@Test
	// Test to get user's full name
	public void testGetFullName() {
		user.setFirstName("John");
		user.setLastName("Doe");
		assertEquals("Doe, John", user.getFullName());
	}
	
	@Test
	// A test to get user's age
	public void testGetAge() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(YEAR_OF_BIRTH, MONTH_OF_BIRTH, DAY_OF_BIRTH);
		user.setDateOfBirth(calendar.getTime());
		assertEquals(ETHALON_AGE, user.getAge());
	}
	
	@Test
	// A test to get user's age if his birthday is today
	public void testGetAgeToday() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(YEAR_OF_BIRTH_TODAY, MONTH_OF_BIRTH_TODAY, DAY_OF_BIRTH_TODAY);
		user.setDateOfBirth(calendar.getTime());
		assertEquals(ETHALON_AGE_TODAY, user.getAge());
	}
	
	@Test
	// A test to get user's age if his birhday will be tomorrow
	// (or it will be this year after the current day)
	public void testGetAgeTomorrow() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(YEAR_OF_BIRTH_TOMORROW, MONTH_OF_BIRTH_TOMORROW, DAY_OF_BIRTH_TOMORROW);
		user.setDateOfBirth(calendar.getTime());
		assertEquals(ETHALON_AGE_TOMORROW, user.getAge());
	}
	
	@Test
	// A test to get user's age if his birhday will be this day next month
	public void testGetAgeNextMonth() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(YEAR_OF_BIRTH_NEXT_MONTH, MONTH_OF_BIRTH_NEXT_MONTH, DAY_OF_BIRTH_NEXT_MONTH);
		user.setDateOfBirth(calendar.getTime());
		assertEquals(ETHALON_AGE_NEXT_MONTH, user.getAge());
	}

	@Test
	// A test to get user's age who has not been born yet
	public void testGetAgeNotBorn() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(YEAR_OF_BIRTH_NOT_BORN, MONTH_OF_BIRTH_NOT_BORN, DAY_OF_BIRTH_NOT_BORN);
		user.setDateOfBirth(calendar.getTime());
		assertTrue("Wrong user`s year of birth",user.getAge()<0);
	}
}
