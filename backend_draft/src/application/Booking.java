package application;

import java.io.Serializable;
import java.time.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Booking implements Serializable {

	
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
	
	public Booking(String lol) {
		this.available = false;
		
		LocalDateTime test = LocalDateTime.of(LocalDate.now(), LocalTime.of(0, 0));
		this.startTime = test;
		this.endTime = test.plusMinutes(25);
		this.location = Department.TBA;
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
	
	

	@Override
	public String toString() {
		
		LocalDate dateOf = startTime.toLocalDate();
		String monthDay = dateOf.getMonth().toString().substring(0, 1) + dateOf.getMonth().toString().substring(1).toLowerCase() + " " + dateOf.getDayOfMonth();
		
		String type = this.getClass().getName().substring(12);
		
		return type + " on " + monthDay + " at " + startTime.toLocalTime().toString();
	}
	
}
