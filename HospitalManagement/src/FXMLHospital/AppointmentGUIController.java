package FXMLHospital;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;

public class AppointmentGUIController {

    @FXML
    private ComboBox<?> departmentComboBox;

    @FXML
    private ComboBox<?> doctorComboBox;

    @FXML
    private Button appHomeButton;

    @FXML
    private TableColumn<?, ?> bookedApp;

    @FXML
    private Button appCancelButton;

    @FXML
    private TableColumn<?, ?> newAppointment;

    @FXML
    private Button appBookButton;

}
