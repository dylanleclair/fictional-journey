package application;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {

	protected String title;
	protected String name;
	protected String emailAddress;
	protected String phoneNumber;
	protected String password;
	protected ArrayList<Integer> bookingIDs = new ArrayList<Integer>();

	public ArrayList<Integer> getBookingIDs() {
		return bookingIDs;
	}

	public void setBookingIDs(ArrayList<Integer> bookingIDs) {
		this.bookingIDs = bookingIDs;
	}

	// constructor
	public User(String name, String emailAddress, String phoneNumber, String password) {
		this.name = name;
		this.emailAddress = emailAddress;
		this.phoneNumber = phoneNumber;
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	
	
	
}
