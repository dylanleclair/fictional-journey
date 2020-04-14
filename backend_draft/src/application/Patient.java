package application;

import java.io.File;
import java.util.ArrayList;

import javafx.collections.ObservableList;

public class Patient extends User {

	ArrayList<TestRecommendation> tests;
	healthInfo patientRecord;

	public Patient(String name, String email, String phone, String password) {
		super(name, email, phone, password);
		this.tests = new ArrayList<TestRecommendation>();
	}


	public void recommendTest (TestRecommendation tr) {
		tests.add(tr);
	}


	public ArrayList<TestRecommendation> getTests() {
		return tests;
	}


	public void setTests(ArrayList<TestRecommendation> tests) {
		this.tests = tests;
	}

	

}
