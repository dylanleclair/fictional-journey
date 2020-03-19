package application;

import java.io.Serializable;

public class User implements Serializable {
	
	protected String name;
	protected String emailAddress;
	protected String phoneNumber;
	

	// constructor
	public User(String name, String emailAddress, String phoneNumber) {
		this.name = name;
		this.emailAddress = emailAddress;
		this.phoneNumber = phoneNumber;
	}
	
}
