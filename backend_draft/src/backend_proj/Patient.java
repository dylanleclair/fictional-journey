package backend_proj;

import java.io.File;
import java.util.ArrayList;

public class Patient extends User {

	ArrayList<Booking> bookings;
	ArrayList<TestRecommendation> tests;
	File patientRecord;
	
	
	// I think we can save the patients records in a file / files that can be a subdirectory.
	// bookings will be loaded and then differentiated
	
}
