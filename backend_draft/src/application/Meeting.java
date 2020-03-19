package application;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Meeting extends Booking {

	
	public Meeting(LocalDateTime startTime, Location location) {
		super(startTime, location);
		// TODO Auto-generated constructor stub
	}

	protected ArrayList<User> staff;
	
}
