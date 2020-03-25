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
import javafx.scene.control.TableColumn;
import javafx.stage.Stage;

public class TestGUIPatientController {

    @FXML
    private ComboBox<?> departmentTestComboBox;

    @FXML
    private ComboBox<?> testComboBox;

    @FXML
    private Button testHomeButton;

    @FXML
    private TableColumn<?, ?> bookedApp;

    @FXML
    private Button testCancelButton;

    @FXML
    private TableColumn<?, ?> newAppointment;

    @FXML
    private Button testBookButton;

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