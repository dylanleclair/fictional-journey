package application;

import java.io.Serializable;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Doctor extends User {

	// this should take in a list of arrays w/ length 2 (basically a tuple) from 0 to 23 for every hour in a day. 
	ArrayList<int[]> appointmentHours;
	Location currentLocation;

	
	// constructor
	public Doctor(String name, String email, String phone) {
		super(name, email, phone);
		this.appointmentHours = new ArrayList<int[]>(); 
	}
	
	
	// can split this into different parts
	public ArrayList<Booking> todo; // will contain a doctors scheduled appointments / meetings for a day????? Weeks? IDK? 
	
	public ArrayList<int[]> getAppointmentHours() {
		return appointmentHours;
	}

	/**
	 * Set the appointment hours of a doctor. The GUI implementation should find a way to parse data into this format. 
	 * Format is a set of tuples where each tuple is a range from 1 hour to the next, where the hours are a number from 0 to 23.
	 * @param appointmentHours
	 */
	public void setAppointmentHours(ArrayList<int[]> appointmentHours) {
		
		todo.add(new Appointment());
		
		this.appointmentHours = appointmentHours;
	}
	
	
	/**
	 * Get the appointments a doctor has for the day
	 * TODO: Add logic for different days or make a separate function for the calendar view
	 * @return an ObservableList of the doctor's appointments.
	 */
	public ObservableList<Appointment> getViewableAppointments () {
		
		
		ObservableList<Appointment> appointments= FXCollections.observableArrayList();
		
		// add logic for displaying different days? 
		
		for (Booking b : todo) {
			if (b.getClass().getName() == "Appointment") {
				
				
				appointments.add((Appointment) b);
				
				
			}
		}
	
		
		return appointments;
		
	}
	
	
	
	/**
	 * Get the meetings that the doctor has for a day
	 * TODO: Add logic for different days or make a separate function for the calendar view
	 * @return an ObservableList of the doctor's appointments.
	 */
	public ObservableList<Meeting> getViewableMeetings () {
		
		ObservableList<Meeting> meetings = FXCollections.observableArrayList();
		
		
		// add logic for different days 
		
		for (Booking b : todo) {
			if (b.getClass().getName() == "Meeting") {
				
				
				meetings.add((Meeting) b);
				
			}
		}
	
		
		return meetings;
		
	}
	
	
	
	
	
}
