package application;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;



/**
 * A timeslot is simply an appointment that is yet to be booked
 * @author Dylan Leclair
 *
 */
public class TimeSlot {

	
	LocalDateTime startTime;
	LocalDateTime endTime; 
	
	
	public TimeSlot (LocalDateTime startTime) {
		
		this.startTime = startTime;
		this.endTime = startTime.plusMinutes(25);
		
	}
	

	
	
}
