package application;

import java.time.LocalDateTime;


public class Appointment extends Booking {

	
	private Doctor doctor; // maybe make this a list of staff involved
	private Patient patient; // the patient
	
	public Appointment(Doctor doctor, Patient patient, LocalDateTime startTime, Location location) {
		super(startTime, location);
		this.doctor = doctor;
		this.patient = patient;
		
	}
	

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}


	public void EditBooking() {
		
	}
	
}
