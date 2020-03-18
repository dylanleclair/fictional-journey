package application;

public class Appointment extends Booking {

	
	private Doctor doctor; // maybe make this a list of staff involved
	private Patient patient; // the patient

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
