package application;

import java.time.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Booking {

	
	// All appointments are 25 min long, start at the top of an hour
	
	
	protected LocalDateTime startTime; // the date and time of the appointment
	protected LocalDateTime endTime;
	protected Location location; 
	protected boolean available;
	
	
	public Booking(LocalDateTime startTime, Location location) {
		
		this.available = true;
		this.startTime = startTime;
		this.endTime = startTime.plusMinutes(25);
		this.location = location;
		
		
	}
	

	

	
	
	
}
