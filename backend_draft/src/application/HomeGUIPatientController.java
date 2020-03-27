package application;

import java.io.IOException;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class HomeGUIPatientController {

	@FXML
	private Pane calendarPane = new Pane();
	
	public Pane getCalendarPane() {
		return calendarPane;
	}

	public void setCalendarPane(Pane calendarPane) {
		this.calendarPane = calendarPane;
	}

	@FXML 
	private ListView<Booking> appointments;
	

	public ListView<Booking> getAppointments() {
		return appointments;
	}

	public void setAppointments(ObservableList<Booking> appointments) {
		this.appointments.setItems(appointments);
	}

	 @FXML
	    private Label name;

	    public Label getName() {
			return name;
		}

		public void setName(String name) {
			this.name.setText(name);
		}

		@FXML
	    private Label role;
		
		public void setRole(String role) {
			this.role.setText(role);
		}

	
    @FXML
    private Button addAppointment;

    @FXML
    private Button addMeeting;

    @FXML
    private ListView<Booking> tests;


    public ListView<Booking> getTests() {
		return tests;
	}

	public void setTests(ObservableList<Booking> meetings) {
		this.tests.setItems(meetings);
	}

	@FXML
    private TableColumn<?, ?> tableViewTimeDoctor;

    public void changeAppointmentButton(ActionEvent event) throws IOException 
    {
    	Node changeApp = (Node) event.getSource();
    	Parent appointmentTab = FXMLLoader.load(getClass().getResource("AppointmentGUIDoctor.fxml"));
		Scene appointmentViewScene = new Scene(appointmentTab);
    	
    	Stage appointmentWindow = (Stage)((changeApp)).getScene().getWindow();
    	appointmentWindow.setScene(appointmentViewScene);
    	appointmentWindow.show();
	}
    
    public void changeTestButton(ActionEvent event) throws IOException 
    {
    	Node changeTest = (Node) event.getSource();
    	Parent testTab = FXMLLoader.load(getClass().getResource("TestGUIDoctor.fxml"));
		Scene testViewScene = new Scene(testTab);
    	
    	Stage testWindow = (Stage)((changeTest)).getScene().getWindow();
    	testWindow.setScene(testViewScene);
    	testWindow.show();
	}

    
    public void bookMeeting (ActionEvent event) throws IOException {
    	
    }
    
    public void addAppointment (ActionEvent event) throws IOException {
    	
    }
    
}
