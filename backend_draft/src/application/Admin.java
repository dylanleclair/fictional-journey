package application;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Admin extends User {

    // default constructor
    public Admin(String name, String email, String phone, String password) {
        super(name, email, phone, password);
    }

    // modify database
    public void addDoctor(Database data, Doctor doctor) {
        data.doctors.add(doctor);
    }

    public void addPatient(Database data, Patient patient) {
        data.patients.add(patient);
    }

    public void addAdmin(Database data, Admin admin) {
        data.administrators.add(admin);
    }

}