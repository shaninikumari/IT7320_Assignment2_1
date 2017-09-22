package unittest;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import util.CostCalculator;
import util.Utility;
import util.CarParkBookingException;
import bean.ParkingSlotBean;
import static org.mockito.Mockito.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
@RunWith(MockitoJUnitRunner.class)
public class TestCostCalculator {
	
	//This test case tests calculated value for car parking charges.
	//It uses equality assertion for comparing calculated outcome with expected value.
	//The costCalculator class uses current time while calculating the parking cost.
	//As current time is dynamic so to mock it Mockito injection is used to inject a
	//Constant value when call for current time occurs.
	
	 @InjectMocks
	 CostCalculator costCalculator = new CostCalculator();
	 @Mock
	 Utility utility;
	@Test
	public void testActiveHour() throws CarParkBookingException{
		
		//CostCalculator costCalculator1 = new CostCalculator();
	    when(utility.getCurrentTime()).thenReturn(660);
		ParkingSlotBean parkingSlotBean = new ParkingSlotBean();
		parkingSlotBean.setDate("20/09/2017");
		parkingSlotBean.setStarttime("10:00");
		parkingSlotBean.setEndtime("11:00");
		//CostCalculator costCalculator = new CostCalculator();
		
		assertEquals(1.5, costCalculator.calculateCharge(parkingSlotBean), 0.0);
	}
	
	//This test case is used for testing calculated fair for the week end.
	//It uses equality assertion for comparing calculated outcome with expected value. 
	@Test
	public void testWeekEnd() throws CarParkBookingException{
		
		//CostCalculator costCalculator1 = new CostCalculator();
	    when(utility.getCurrentTime()).thenReturn(660);
		ParkingSlotBean parkingSlotBean = new ParkingSlotBean();
		parkingSlotBean.setDate("17/09/2017");
		parkingSlotBean.setStarttime("10:00");
		parkingSlotBean.setEndtime("11:00");
		//CostCalculator costCalculator = new CostCalculator();
		
		assertEquals(0.0, costCalculator.calculateCharge(parkingSlotBean), 0.0);
	}

	//This test case is for testing expected exception.
	//Cost calculator throws an exception when invalid date or time is sent for calculating parking charge.
	
	@Test(expected = CarParkBookingException.class)
	public void testException() throws CarParkBookingException{
		
		//CostCalculator costCalculator1 = new CostCalculator();
	    when(utility.getCurrentTime()).thenReturn(660);
		ParkingSlotBean parkingSlotBean = new ParkingSlotBean();
		parkingSlotBean.setDate("17/09/2017");
		parkingSlotBean.setStarttime("xx");
		parkingSlotBean.setEndtime("11:00");
		costCalculator.calculateCharge(parkingSlotBean);
		
		//assertEquals(0.0, costCalculator.calculateCharge(parkingSlotBean), 0.0);
	}
}
