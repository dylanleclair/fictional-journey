package backend_proj;

import java.util.ArrayList;

public class Doctor extends Staff {

	// this should take in a list of arrays w/ length 2 (basically a tuple) from 0 to 23 for every hour in a day. 
	ArrayList<int[]> appointmentHours;
	Location currentLocation;
	
	
	public ArrayList<int[]> getAppointmentHours() {
		return appointmentHours;
	}

	/**
	 * Set the appointment hours of a doctor. The GUI implementation should find a way to parse data into this format. 
	 * Format is a set of tuples where each tuple is a range from 1 hour to the next, where the hours are a number from 0 to 23.
	 * @param appointmentHours
	 */
	public void setAppointmentHours(ArrayList<int[]> appointmentHours) {
		this.appointmentHours = appointmentHours;
	}
	
	
	
	
	
	
	
	
}
