package application;

import java.time.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Booking {

	
	// All appointments are 25 min long, start at the top of an hour
  
	protected LocalDateTime startTime; // the date and time of the appointment
	protected LocalDateTime endTime;
	protected Department location; 
	protected boolean available;
	
	/**
	 * Constructor for meetings that are 25 min long.
	 * @param startTime
	 * @param location
	 */
	public Booking(LocalDateTime startTime, Department location) {
		
		this.available = true;
		this.startTime = startTime;
		this.endTime = startTime.plusMinutes(25);
		this.location = location;

		
	}
	
	public Booking() {
		
	}
	
	/**
	 * This is used for booking an appointment with a doctor
	 * @param doctor - the Doctor who's appointment it is
	 * @param slot - a TimeSlot to retreive the start and end times from. 
	 */
	public Booking(Doctor doctor, TimeSlot slot) {
		this.available = false;
		this.startTime = slot.getStartTime();
		this.endTime = slot.getEndTime();
		this.location = doctor.department;
	}
	
	

	
	
}
