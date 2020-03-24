package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class AppointmentGUIPatientController {

    @FXML
    private ComboBox<?> departmentComboBox;

    @FXML
    private ComboBox<?> doctorComboBox;

    @FXML
    private Button appHomeButton;

    @FXML
    private ListView<?> appListView;

    @FXML
    private Button appBookButton;

    @FXML
 
    public void changeHomeButton(ActionEvent event) throws IOException 
    {
    	Node changeHome = (Node) event.getSource();
    	Parent homeTab = FXMLLoader.load(getClass().getResource("HomeGUIPatient.fxml"));
		Scene homeViewScene = new Scene(homeTab);
    	
    	Stage homeWindow = (Stage)((changeHome)).getScene().getWindow();
    	homeWindow.setScene(homeViewScene);
    	homeWindow.show();
	}

}