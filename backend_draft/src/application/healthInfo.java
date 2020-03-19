package application;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class healthInfo {
    int age;
    double weight;
    double height;
    ArrayList<String> conditions;
    ArrayList<String> medications;
    String doctorNotes;

    // constructor
    public healthInfo(int age, double weight, double height) {
        this.conditions = new ArrayList<String>();
        this.medications = new ArrayList<String>();
        this.age = age;
        this.weight = weight;
        this.height = height;
    }

    

}