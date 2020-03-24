package application;

import java.io.IOException;

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
import javafx.stage.Stage;

public class HomeGUIPatientController {

    @FXML
    private Button appointmentPatientButton;
    
    @FXML
    private ListView<?> appPatientListView;
    
    @FXML
    private ListView<?> testPatientListView;

    @FXML
    private Button testPatientButton;

    @FXML
    private Label patientDateLabel;

    @FXML
    private Label patientTimeLabel;

    @FXML
    private TableColumn<?, ?> tableViewTimePatient;
    
    public void changeAppointmentButton(ActionEvent event) throws IOException 
    {
    	Node changeApp = (Node) event.getSource();
    	Parent appointmentTab = FXMLLoader.load(getClass().getResource("AppointmentGUIPatient.fxml"));
		Scene appointmentViewScene = new Scene(appointmentTab);
    	
    	Stage appointmentWindow = (Stage)((changeApp)).getScene().getWindow();
    	appointmentWindow.setScene(appointmentViewScene);
    	appointmentWindow.show();
	}
    
    public void changeTestButton(ActionEvent event) throws IOException 
    {
    	Node changeTest = (Node) event.getSource();
    	Parent testTab = FXMLLoader.load(getClass().getResource("TestGUIPatient.fxml"));
		Scene testViewScene = new Scene(testTab);
    	
    	Stage testWindow = (Stage)((changeTest)).getScene().getWindow();
    	testWindow.setScene(testViewScene);
    	testWindow.show();
	}

}
