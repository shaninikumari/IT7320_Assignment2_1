package util;

import java.util.Date;

public class Utility {
	public int getCurrentTime(){
		Date currentTime = new Date();
		return currentTime.getHours()*60+ currentTime.getMinutes();
	}
}
