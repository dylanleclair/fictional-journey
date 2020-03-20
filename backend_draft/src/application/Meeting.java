package application;

import java.time.LocalDateTime;

import java.util.ArrayList;

public class Meeting extends Booking {

	protected ArrayList<User> staff; // the list of staff to be present at the meeting
  
	public Meeting(LocalDateTime startTime, Location location) {
		super(startTime, location);
		// TODO Auto-generated constructor stub
	}


	
	
}
