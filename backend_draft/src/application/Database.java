package application;

import java.util.ArrayList;
import java.util.HashMap;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;


public class Database implements Serializable {
    /**
	 * 
	 */
	//private static final long serialVersionUID = -4264496296267755018L;
	ArrayList<Admin> administrators;
    ArrayList<Doctor> doctors;
    ArrayList<Patient> patients;
    HashMap<Integer,Booking> bookings;
    int systemBookingCount;

    // default constructor
    public Database() { 
        this.administrators = new ArrayList<Admin>();
        this.doctors = new ArrayList<Doctor>();
        this.patients = new ArrayList<Patient>();
        this.systemBookingCount = 0;
        this.bookings = new HashMap<Integer,Booking>();
    }

    // save to file
    public void saveData(String filepath) throws IOException {

        try {
            ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(filepath));
            output.writeObject(this);
            output.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    // load from file
    public void loadData(String filepath) throws IOException {
        try {
            ObjectInputStream input = new ObjectInputStream(new FileInputStream(filepath));
            Database in = (Database)input.readObject();
            this.administrators = in.administrators;
            this.doctors = in.doctors;
            this.patients = in.patients;
            this.systemBookingCount = in.systemBookingCount;
            this.bookings = in.bookings;
            input.close();
            
        } catch (Exception ex) {
            ex.printStackTrace();

        }
        
        
        
    }


    /**
     * Fills the database with some sample data. 
     * @author Dylan Leclair
     */
    public void initializeDatabase() {
    	
        int[][] lol = {{1,2}, {3,4}};
        
        Doctor fakeDoc = new Doctor(lol, Department.CARDIOLOGY, "Dylan Leclair", "fake@lol.com","4035127333", "pog");
        Patient fakePatient = new Patient("John Doe", "fake@sukrum.com", "403 512 7333" , "lmao");
        Admin dylan = new Admin("Dylan", "dylan", "fake", "1");
        
        
        this.administrators.add(dylan);
        this.doctors.add(fakeDoc);
        this.patients.add(fakePatient);
        addAppointment(fakePatient, fakeDoc, new TimeSlot(LocalDateTime.now()));
    	addMeeting(dylan);
        
    }
    
    
    /**
     * Pushes the updated initialize data to the live version of the database.
     * @param args
     */
    public static void main(String[] args) {
    	
    	
    	Database datab = new Database();
    	
    	datab.initializeDatabase();
    	
    	try {
    		datab.saveData("data/system.dat");
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	
    	
    	Database test = new Database();
    	try {
			test.loadData("data/system.dat");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
    }
    
    /** 
     * Retrieves a list of all of a users specified type of Bookings. (Bookings can be appointments, meetings, or tests.)
     * @param the user you want to retrieve the bookings from, for example a Patient could be provided here.
     * @param the class name of the type of Booking you want to collect -- ie: Appointment for a patient's appointments.
     * @return
     */
	public ObservableList<Booking> getBookings (User user) {
	
		ObservableList<Booking> list = FXCollections.observableArrayList();
		
		for(Integer id : user.getBookingIDs()) {
		

			list.add(bookings.get(id));

		
		}	
		
		System.out.println(list);
		return list;
	}
    
	
    /** 
     * Retrieves a list of all of a users specified type of Bookings. (Bookings can be appointments, meetings, or tests.)
     * @param the user you want to retrieve the bookings from, for example a Patient could be provided here.
     * @param the class name of the type of Booking you want to collect -- ie: Appointment for a patient's appointments.
     * @return
     */
	public ArrayList<Booking> getBookings (User user, LocalDate date) {
	
		ArrayList<Booking> list = new ArrayList<Booking>();
		
		for(Integer id : user.getBookingIDs()) {
			
			
			
			Booking booking = bookings.get(id);
			LocalDateTime start = booking.startTime;
			LocalDate dayOf = start.toLocalDate();
			
			if (dayOf.equals(date)) {
				list.add(booking);
			}
			
			

		
		}	

		return list;
	}
    
	
    /**
     * Adds an appointment to the system, adding the appointment id to the patients and doctors lists.
     * @param patient
     * @param doctor
     */
	public void addAppointment (Patient patient, Doctor doctor, TimeSlot slot) {
	
	
		bookings.put(systemBookingCount, new Appointment(doctor, slot));
		
		doctor.bookingIDs.add(systemBookingCount);
		patient.bookingIDs.add(systemBookingCount);
		
		systemBookingCount++;
		
		// after updating the system count and bookings, we should update the state of the database
		
		try {
			this.saveData("data/system.dat");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	public void addMeeting (User user) {
		
		bookings.put(systemBookingCount, new Booking("lol"));
		user.bookingIDs.add(systemBookingCount);
		
		systemBookingCount++;
		
	}
	
}

