package application;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


  public class Doctor extends User {

	// this should take in a list of arrays w/ length 2 (basically a tuple) from 0 to 23 for every hour in a day. 
	int[][] appointmentHours;
	ArrayList<TimeSlot> openAppointments; // generated from appointmentHours and daysWorkWeek
	Department department;
    List<Boolean> workingDays = new ArrayList<Boolean>(Arrays.asList(new Boolean[7]));
    
    
  // constructors - update these

  public int[][] getAppointmentHours() {
		return appointmentHours;
	}




	public void setAppointmentHours(int[][] appointmentHours) {
		this.appointmentHours = appointmentHours;
	}




	public ArrayList<TimeSlot> getOpenAppointments() {
		return openAppointments;
	}




	public void setOpenAppointments(ArrayList<TimeSlot> openAppointments) {
		this.openAppointments = openAppointments;
	}




	public Department getDepartment() {
		return department;
	}




	public void setDepartment(Department department) {
		this.department = department;
	}




public Doctor(int[][] apptHours, Department dept, String name, String email, String phone, String password) {
		super(name,email,phone,password);
		appointmentHours = apptHours;
		department = dept;
		//openAppointments = generateTimeSlots(this);
		Collections.fill(workingDays, Boolean.TRUE);
	}
	


	
	/**
	 * Currently creates timeslots on the day it was executed using the appointment hours specified
	 * This would refresh every month i guess idk?
	 * TODO: make it properly function / refresh
	 * @param doc
	 * @return
	 */
	public ObservableList<TimeSlot> generateTimeSlots(Doctor doc, LocalDate day) {
		
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
	
	
	
	public ArrayList<TimeSlot> genTimeSlots(LocalDate day) {
		
		ArrayList<TimeSlot> openSlots = new ArrayList<TimeSlot>();
		

		int lol = day.getDayOfWeek().getValue();
		boolean condition = false;
		if (lol == 7) {
			condition = workingDays.get(0);
		} else {
			condition = workingDays.get(lol);
		}
		
		if (condition) {
			
			for (int[] range : this.appointmentHours) {
				
				
				LocalTime start = LocalTime.of(range[0], 0);
				LocalTime end = LocalTime.of(range[1], 0);
				
				while(start.isBefore(end)) {
					
					LocalDateTime slotTime = LocalDateTime.of(day, start);
					
					openSlots.add(new TimeSlot(slotTime));
					slotTime = slotTime.plusMinutes(30);
					
					openSlots.add(new TimeSlot(slotTime));
					slotTime = slotTime.plusMinutes(30);
					
					start = start.plusHours(1);
					
					
				}
		}
		
			
		}
		
		return openSlots;
		
	}
	
	
	
	public void setWorkingDays (List<Boolean> list) {

		this.workingDays = list;
	}
	
	public String toString() {
		return name;
		
	}
	
	
	
}
