package application;

import java.time.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Booking {

	
	// All appointments are 25 min long, start at the top of an hour
	
	
	protected LocalDateTime timeOfBooking; // the date and time of the appointment
	protected Location location; 
	protected boolean Available;
	
	
	public Booking() {
		
	}
	
	/**
	 * Still yet to be fully implemented
	 * TODO: make it functional lmao
	 * @param doc
	 * @return
	 */
	public ObservableList<Booking> generateTimeSlots(Doctor doc) {
		
		ObservableList<Booking> bookings= FXCollections.observableArrayList();
		
		for (int[] range : doc.getAppointmentHours()) {
			
			
			LocalTime start = LocalTime.of(range[0], 0);
			LocalTime end = LocalTime.of(range[1], 0);
			while(start.isBefore(end)) {
				// iterate, creating 2, 25 min bookings for every hour until we go out of range for each interval
				// 
				
				
				// access the second element of a time tuple and 
			}
			
		}
		
		return bookings;
		
	}
	

	
	
	
}
