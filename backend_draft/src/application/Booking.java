package application;

import java.io.Serializable;
import java.time.*;
import java.time.format.DateTimeFormatter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Booking implements Serializable {

	
	// All appointments are 25 min long, start at the top of an hour
  
	protected LocalDateTime startTime; // the date and time of the appointment
	protected LocalDateTime endTime;
	protected Department location; 

	/**
	 * Constructor for meetings that are 25 min long.
	 * @param startTime
	 * @param location
	 */
	public Booking(LocalDateTime startTime, Department location) {
		
		this.startTime = startTime;
		this.endTime = startTime.plusMinutes(25);
		this.location = location;

		
	}
	
	public Booking() {
		
	}
	
	public Booking(String lol) {
		
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
		this.startTime = slot.getStartTime();
		this.endTime = slot.getEndTime();
		this.location = doctor.department;
	}
	
	public Booking( TimeSlot slot) {
		this.startTime = slot.getStartTime();
		this.endTime = slot.getEndTime();
	}
	
	

	@Override
	public String toString() {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
		
		LocalDate dateOf = startTime.toLocalDate();
		String monthDay = dateOf.getMonth().toString().substring(0, 1) + dateOf.getMonth().toString().substring(1).toLowerCase() + " " + dateOf.getDayOfMonth();
		
		String type = this.getClass().getName().substring(12);
		
		
		String localTime = startTime.toLocalTime().toString();
		
		if (localTime == "00:00") {
			localTime = "Midnight";
			
		} else if (localTime == "12:00") {
			localTime = "Noon";
		} // add logic to account for 13-24 to actually be regular times instead of military lol
		
		
		return type + " on " + monthDay + " at " + startTime.toLocalTime().format(formatter);
	}
	
}
