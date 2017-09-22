package util;

import java.text.SimpleDateFormat;
import java.util.Date;

import bean.ParkingSlotBean;

public class CostCalculator {
	private Utility utility;
	public void setUtility(Utility utility){
		this.utility = utility;
	}
	public double calculateParkingCharge(String bookingId){
		double totalCost=0.0;
		setUtility(new Utility());
		try{
		DBManager dbm= new DBManager();
		ParkingSlotBean parkingSlotBean = dbm.getBookedParkingSlot(bookingId.trim());
		totalCost = calculateCharge(parkingSlotBean);
		}catch(Exception e){
			
		}
		return totalCost;
	}
	
	public double calculateCharge(ParkingSlotBean parkingSlotBean)throws CarParkBookingException{
		double totalCost=0.0;
		try{
			System.out.println("calculateCharge called");
				SimpleDateFormat parser = new SimpleDateFormat("HH:mm");
				
				Date startTime = parser.parse(parkingSlotBean.getStarttime());
				
				int startTimeInt=startTime.getHours()*60+ startTime.getMinutes();
				
				Date endTime = parser.parse(parkingSlotBean.getEndtime());
				int endTimeInt=endTime.getHours()*60+ endTime.getMinutes();
				
				
				int currentTimeInt=utility.getCurrentTime();
				System.out.println(currentTimeInt);
				int effectiveExitTime;
				if(currentTimeInt>endTimeInt){
					effectiveExitTime=currentTimeInt;
				}else{
					effectiveExitTime=endTimeInt;
				}
				int totalParkingTime=effectiveExitTime-startTimeInt;
				double totalParkingTimeinHour=totalParkingTime/60;
				
				String[] parkingDateArray = parkingSlotBean.getDate().trim().split("/");
				
				Date parkingDate = new Date(Integer.parseInt(parkingDateArray[2])-1900, Integer.parseInt(parkingDateArray[1])-1, Integer.parseInt(parkingDateArray[0]));
				System.out.println(parkingDate.getDay());
				double rate;
				if(parkingDate.getDay()>1 && parkingDate.getDay()<5){
					rate=1.5;
				}else{
					rate=0.0;
				}
				System.out.println(rate);
				 totalCost=totalParkingTimeinHour * rate;
				
		
		
		}catch(Exception e){
			throw new CarParkBookingException();
		}
		
		return totalCost;
		
	}
	
	

}
