package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class BasicController {

	

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





    

