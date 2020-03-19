package application;

import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.IOException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Popup;
import javafx.stage.Stage;


/**
 * The main class for the GUI and it's associated functions
 * 
 * 
 * TODO: - add a section for users to request an account for the hospital, which the admin must then verify
 * 
 * @author Dylan Leclair, Mohammed Chama
 *
 */
public class GUI extends Application {
	
	String user = "Moe";
    String pw = "password";
    String checkUser, checkPW;
    
    Roles selectedRole;
    
    
	@Override
	public void start(Stage primaryStage) throws Exception {        // the GUI class
	
		
		
		// we need to add initialization code for the database so users can actually login
		
		
		Stage window = primaryStage;
		
		GridPane userSelection = new GridPane();
		//Image image = new Image("logo.jpg", 400, 100, false, false);
		//userSelection.add(new ImageView(image), 1, 0);
		primaryStage.setTitle("Sukrum Technologies");
		userSelection.setStyle("-fx-background-color: #373EBA");
		userSelection.setAlignment(Pos.CENTER);
		userSelection.setHgap(40);
		userSelection.setVgap(5);
		userSelection.setGridLinesVisible(false);
		Scene scene = new Scene(userSelection,500,250);         
		
		
		
		GridPane loginPane = new GridPane();
		loginPane.setStyle("-fx-background-color: #373EBA");
		loginPane.setAlignment(Pos.CENTER);
		loginPane.setHgap(5);
		loginPane.setVgap(5);
		loginPane.setGridLinesVisible(false);
		Scene scene1 = new Scene(loginPane, 500, 250);
		
		
		GridPane loggedInPane = new GridPane();
		loggedInPane.setStyle("-fx-background-color: #373EBA");
		loggedInPane.setAlignment(Pos.CENTER);
		loggedInPane.setHgap(5);
		loggedInPane.setVgap(5);
		loggedInPane.setGridLinesVisible(false);
		Scene scene2 = new Scene(loggedInPane, 700, 700);
		
               
		TextField userName = new TextField();
		userName.setPromptText("Username");
		loginPane.add(userName, 0, 0);


		PasswordField passwordField = new PasswordField();
		passwordField.setPromptText("Password");
		loginPane.add(passwordField, 0,1);
		
		Button loginButton  = new Button();
		loginButton.setText ("Login");
		loginButton.setPadding(new Insets(5,5,5,5));
		loginButton.setPrefSize(100, 20);
		loginPane.add(loginButton, 0,3);
		//loginButton.setOnAction(e -> window.setScene(scene2));
		loginButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				checkUser = userName.getText().toString();
				checkPW = passwordField.getText().toString();
				if (checkUser.equals(user) && checkPW.equals(pw)) {
					
					
					// Logic for determining which role was selected & building the proper GUI for them to see
					
					GridPane mainpane = new GridPane();
					intializeBasicPane(mainpane);
					Scene mainScene = new Scene(mainpane, 700,700);
					
					if (selectedRole == Roles.ADMIN) {
						
						
						
						
						mainScene = generateScene("lol");
						
					} else if (selectedRole == Roles.DOCTOR) {
						
						addDoctorElements(mainpane);
						
					} else if (selectedRole == Roles.PATIENT) {
						
						addPatientElements(mainpane);
						
					}
					
					window.setScene(mainScene);
				}
				
				// error trapping cases
				else {
					if (checkUser.contentEquals("")) {
						AlertBox.display("String", "Please enter a username");
					}
					else if (checkPW.contentEquals("")) {
						AlertBox.display("String", "Please enter a password");
					}
					else {
						AlertBox.display("String", "Incorrect username or password");
					}
				}
			}

		});
		
		
		Button backButton  = new Button();
		backButton.setText ("Back");
		backButton.setPadding(new Insets(5,5,5,5));
		backButton.setPrefSize(100, 20);
		backButton.setOnAction(e -> window.setScene(scene));

		loginPane.add(backButton, 0,4);

		
		
		// Code for the logic of the intial startup screen
		
		Button newButton0  = new Button();
		newButton0.setText ("Admin");
		newButton0.setPadding(new Insets(5,5,5,5));
		newButton0.setPrefSize(100, 20);
		newButton0.setOnAction(e -> {
			window.setScene(scene1);
			selectedRole = Roles.ADMIN;
		});
		
		Button newButton1  = new Button();
		newButton1.setText ("Doctor");
		newButton1.setPadding(new Insets(5,5,5,5));
		newButton1.setPrefSize(100, 20);
		newButton1.setOnAction(e -> {
			window.setScene(scene1);
			selectedRole = Roles.DOCTOR;
		});
		
		Button newButton2  = new Button();
		newButton2.setText ("Patient");
		newButton2.setPadding(new Insets(5,5,5,5));
		newButton2.setPrefSize(100, 20);
		newButton2.setOnAction(e -> {
			window.setScene(scene1);
			selectedRole = Roles.PATIENT;
		});

		userSelection.add(newButton0, 0,1);
		userSelection.add(newButton1, 1,1);
		userSelection.add(newButton2, 2,1);
		
		window.setScene(scene);
		window.show();
		
		
		
		
		// Scenes for different users
		
		
	
		
	}
	
	
	
	

	/**
	 * The function responsible for generating a basic scene for the UI that has elements shared by all users. 
	 * 
	 * @param gpane
	 * @return
	 */
	public void intializeBasicPane(GridPane gpane) {
		
		// UI elements that are added for all scenes
	
		gpane.setStyle("-fx-background-color: #373EBA");
		gpane.setAlignment(Pos.CENTER);
		gpane.setHgap(5);
		gpane.setVgap(5);
		gpane.setGridLinesVisible(false);
	
		
	}
	
	/**
	 * Adds patient specific functionality to the shared UI.
	 * @param gpane - the shared UI to add additional functionality to
	 */
	public void addPatientElements (GridPane gpane) {
		
		
		// add elements to make the scene specific to a patient
		
	}
	
	
	/**
	 * Adds admin specific functionality to the shared UI.
	 * @param gpane
	 */
	public void addAdminElements (GridPane gpane) {
		
		// add elements to make scene specific to an admin
		
		Button sample = new Button("lol");
		sample.setAlignment(Pos.CENTER);
		
		gpane.add(sample,1,0);
	}

	
	public Scene generateScene (String fileName) {
		try {
			
			
			FXMLLoader loader = new FXMLLoader();
			
			
			Parent root = loader.load(getClass().getResourceAsStream("BasicScene.FXML"));
			
			BasicController lol = (BasicController) loader.getController();
			
			lol.setName("hello");
		
			Scene scene = new Scene(root);
			return scene;
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	
	}
	
	
	/**
	 * Adds doctor specific functionality to the shared UI.
	 * @param pane
	 */
	public void addDoctorElements (GridPane pane) {
		
	}
	
	public static void main (String[] args) {
		
		
		launch(args);
		
	}

	
}