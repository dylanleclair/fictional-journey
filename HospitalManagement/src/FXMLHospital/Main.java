package FXMLHospital;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception{
		try {
			 FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("LoginGUIPatient.fxml"));
		        Parent rootBoard = (Parent) fxmlLoader.load();
		        Stage stage = new Stage();
		        stage.setTitle("Login");
		        stage.setScene(new Scene(rootBoard));
		        stage.setResizable(false);
		        stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
