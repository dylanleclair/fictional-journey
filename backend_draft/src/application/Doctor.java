package application;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Doctor extends Staff {

	// this should take in a list of arrays w/ length 2 (basically a tuple) from 0 to 23 for every hour in a day. 
	int[][] appointmentHours;
	ObservableList<TimeSlot> openAppointments;
	Department department;
	
	
	
;	public Doctor(int[][] apptHours, Department dept) {
		
		appointmentHours = apptHours;
		department = dept;
		openAppointments = generateTimeSlots(this);
	}
	
	
	// can split this into different parts
	public ArrayList<Booking> todo; // will contain a doctors scheduled appointments / meetings for a day????? Weeks? IDK? 
	

	
	
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
	
	
	
	/**
	 * Currently creates timeslots on the day it was executed using the appointment hours specified
	 * This would refresh every month i guess idk?
	 * TODO: make it properly function / refresh
	 * @param doc
	 * @return
	 */
	public ObservableList<TimeSlot> generateTimeSlots(Doctor doc) {
		
		ObservableList<TimeSlot> openSlots = FXCollections.observableArrayList();
		
		for (int[] range : doc.appointmentHours) {
			
			
			LocalDate now = LocalDate.now();
			
			LocalTime start = LocalTime.of(range[0], 0);
			LocalTime end = LocalTime.of(range[1], 0);
			
			while(start.isBefore(end)) {
				
				LocalDateTime slotTime = LocalDateTime.of(now, start);
				
				openSlots.add(new TimeSlot(slotTime));
				slotTime = slotTime.plusMinutes(30);
				
				openSlots.add(new TimeSlot(slotTime));
				slotTime = slotTime.plusMinutes(30);
				
				start = start.plusHours(1);
				
				
				
				// Keep this in here as a reference for when you need to parse date/time data into the GUI 
				
				ZonedDateTime wow = ZonedDateTime.of(slotTime, ZoneId.systemDefault());
				
				DateTimeFormatter pretty = DateTimeFormatter.RFC_1123_DATE_TIME;
				String output = pretty.format(wow);
				System.out.println(output);
			}
			
		}
		
		return openSlots;
		
	}
	
	
	
	public static void main (String[] args) {
		

		int[][] sample = new int[2][2];
		sample[0][0] = 3;
		sample[0][1] = 5;
		sample[1][0] = 1;
		sample[1][1] = 2;
		
		
		int[][] lol = {{1,2}, {3,4}};
		
		@SuppressWarnings("unused")
		Doctor one = new Doctor(lol, Department.CARDIOLOGY);
		
	
		
		
	}
	
	
}
