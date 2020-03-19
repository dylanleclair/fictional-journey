package application;

import java.util.ArrayList;
import java.io.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class Database implements Serializable {
    ArrayList<Admin> administrators;
    ArrayList<Doctor> doctors;
    ArrayList<Patient> patients;

    // default constructor
    public Database() { 
        this.administrators = new ArrayList<Admin>();
        this.doctors = new ArrayList<Doctor>();
        this.patients = new ArrayList<Patient>();
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
            input.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


}