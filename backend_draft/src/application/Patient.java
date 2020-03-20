package application;

import java.io.File;
import java.util.ArrayList;

public class Patient extends User {

	ArrayList<Booking> bookings;
	ArrayList<TestRecommendation> tests;
	healthInfo patientRecord;

	public Patient(String name, String email, String phone) {
		super(name, email, phone);
	}


	
	
	// if we want to be super extra we could hash appointments to an ID so that we can modify them 
	
	// I think we can save the patients records in a file / files that can be a subdirectory.
	// bookings will be loaded and then differentiated
	
}
