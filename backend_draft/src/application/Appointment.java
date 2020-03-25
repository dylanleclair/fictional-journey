package application;

import java.io.Serializable;
import java.time.LocalDateTime;


public class Appointment extends Booking implements Serializable {

	
	private Doctor doctor; // maybe make this a list of staff involved
	private Patient patient; // the patient
	// maybe add a field for the reason
	
	public Appointment() {
		
	}
	
	public Appointment(Doctor doctor, Patient patient, LocalDateTime startTime, Department location) {
		super(startTime, location);
		this.doctor = doctor;
		this.patient = patient;
		
	}
	
	public Appointment(Doctor doctor, TimeSlot slot) {
		super(doctor, slot);
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
