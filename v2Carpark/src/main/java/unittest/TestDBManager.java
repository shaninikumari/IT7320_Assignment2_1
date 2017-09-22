package unittest;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import util.CarParkBookingException;
import util.DBManager;
@RunWith(MockitoJUnitRunner.class)
public class TestDBManager {
	
	//Use of assertTrue. If user is register and its combination of user and password is correct
	//dbmanager authenticateUser method must return true.
	@Test
	public void testExistingUser() {
		
		DBManager dbmanager	= new DBManager();
		
		assertTrue(dbmanager.authenticateUser("shanini", "shanini"));
	}
	
	//Use of assertFalse. If user is register and its combination of user and password is wrong
	//dbmanager authenticateUser method must return false.
	@Test
	public void testFalseUser() {
		
		DBManager dbmanager	= new DBManager();
		
		assertFalse(dbmanager.authenticateUser("shanini", "shanini1"));
	}
	
	//Use of assertNotNull. If registered user have booked the car park slot, 
	//dbmanager getBookedParkingSlot must return not null object.
	@Test
	public void testGetBookedParkingSlot() {
		
		DBManager dbmanager	= new DBManager();
		
		assertNotNull(dbmanager.getBookedParkingSlot("shanini"));
	}
	
	
	
}
