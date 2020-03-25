package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class BasicController {

	

	@FXML 
	private ListView<Booking> appointments;
	

	public ListView<Booking> getAppointments() {
		return appointments;
	}

	public void setAppointments(ObservableList<Booking> appointments) {
		this.appointments.setItems(appointments);
	}

	@FXML 
	private TableView tableview;
	
	@FXML
	private TableColumn sunday;
	
	@FXML private TableColumn monday;
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

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
    void handleSettings(ActionEvent event) {
    	name.setText("Hello!");
    }

    @FXML
    void initialize() {
        assert name != null : "fx:id=\"name\" was not injected: check your FXML file 'Untitled'.";
        assert role != null : "fx:id=\"role\" was not injected: check your FXML file 'Untitled'.";

    }
}





    

