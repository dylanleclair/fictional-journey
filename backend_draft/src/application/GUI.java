package application;

import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
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
	
	
	static Database datab = new Database();
	
	String user = "Moe";
    String pw = "password";
    String checkUser, checkPW;
    
    
    MainSceneController mcontroller;
    
    User signedIn;
    
    boolean credentialsMatch = false;


    static Roles selectedRole;
    
    
	@Override
	public void start(Stage primaryStage) throws Exception {        // the GUI class
	
		
		
		// we need to add initialization code for the database so users can actually login
		
		datab.loadData("data/system.dat");
		
		Stage window = primaryStage;
		window.setResizable(false);
		
		BorderPane userSelection = new BorderPane();
		//Image image = new Image("logo.jpg", 400, 100, false, false);
		//userSelection.add(new ImageView(image), 1, 0);
		primaryStage.setTitle("Sukrum Hospital Management System");
		userSelection.setStyle("-fx-background-color: #373EBA");
		//userSelection.setAlignment(Pos.CENTER);
		//userSelection.setHgap(40);
		//userSelection.setVgap(5);
		//userSelection.setGridLinesVisible(false);
		Scene scene = new Scene(userSelection,500,250);         
		scene.getStylesheets().add(GUI.class.getResource("StyleSheet.css").toExternalForm());
		
		
		GridPane loginPane = new GridPane();
		loginPane.setStyle("-fx-background-color: #373EBA");
		loginPane.setAlignment(Pos.CENTER);
		loginPane.setHgap(5);
		loginPane.setVgap(5);
		loginPane.setGridLinesVisible(false);
		Scene scene1 = new Scene(loginPane, 500, 250);
		scene1.getStylesheets().add(GUI.class.getResource("StyleSheet.css").toExternalForm());
		
		GridPane loggedInPane = new GridPane();
		loggedInPane.setStyle("-fx-background-color: #373EBA");
		loggedInPane.setAlignment(Pos.CENTER);
		loggedInPane.setHgap(5);
		loggedInPane.setVgap(5);
		loggedInPane.setGridLinesVisible(false);
		Scene scene2 = new Scene(loggedInPane, 700, 700);
		scene2.getStylesheets().add(GUI.class.getResource("StyleSheet.css").toExternalForm());
               
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
				String user, pass;
				checkUser = userName.getText().toString();
				checkPW = passwordField.getText().toString();
				
				switch(selectedRole) {
				
				case DOCTOR :
					
			    	for (Doctor item : datab.doctors) {
			    		if (item.emailAddress.contentEquals(checkUser)) {
			    			if (checkPW .contentEquals(item.getPassword())) {
			    				credentialsMatch = true;
			    				signedIn = item;
			    			}
			    			
			    		}
			    	}
					
					break;
				case ADMIN : 
					
			    	for (Admin item : datab.administrators) {
			    		if (item.emailAddress.contentEquals(checkUser)) {
			    			if (checkPW .contentEquals(item.getPassword())) {
			    				credentialsMatch = true;
			    				signedIn = item;
			    			}
			    			
			    		}
			    	}
			    	
					break;
				
				case PATIENT : 
					
			    	for (Patient item : datab.patients) {
			    		if (item.emailAddress.contentEquals(checkUser)) {
			    			if (checkPW .contentEquals(item.getPassword())) {
			    				credentialsMatch = true;
			    				signedIn = item;
			    			}
			    			
			    		}
			    	}
					
					break;
					
				}
				

				if (credentialsMatch) {
					
					//signedIn = fakePatient;
					// Logic for determining which role was selected & building the proper GUI for them to see
					
					GridPane mainpane = new GridPane();
					intializeBasicPane(mainpane);
					Scene mainScene = new Scene(mainpane, 700,700);
					
					if (selectedRole == Roles.ADMIN) {

					
						//addAdminElements(mainpane);

						mainScene = generateScene();
						
					} else if (selectedRole == Roles.DOCTOR) {
						
						mainScene = generateScene();
					} else if (selectedRole == Roles.PATIENT) {
						
						mainScene = generateScene();

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

		
		Button registerButton = new Button("New patient? Register here.");
		
		registerButton.setPadding(new Insets(5,5,5,5));
		
		registerButton.setOnAction(e -> {
			
			
			window.setScene(new Scene(generateRegistrationPanel(scene, window),765,360));
		});
		
		
		
		
		HBox roleSelect = new HBox(30);
		
		roleSelect.setPadding(new Insets(80,0,0,70));
		
		
		roleSelect.getChildren().add(newButton0);
		roleSelect.getChildren().add(newButton1);
		roleSelect.getChildren().add(newButton2);
		

		userSelection.setCenter(roleSelect);
		userSelection.setBottom(registerButton);
		
		
		BorderPane.setMargin(registerButton, new Insets(0,0,20,0) );
		BorderPane.setAlignment(roleSelect, Pos.CENTER);
		BorderPane.setAlignment(registerButton, Pos.CENTER);
		
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
	 * Deprecated
	 * @param fileName
	 * @return
	 */
	public Scene generateScene () {
		try {
			
			
			FXMLLoader loader = new FXMLLoader();
			
			
			Parent root = loader.load(getClass().getResourceAsStream("MainScene.FXML"));
			
			MainSceneController lol = (MainSceneController) loader.getController();
			root.getStylesheets().add(GUI.class.getResource("StyleSheet.css").toExternalForm());
			
			mcontroller = lol;
			
			lol.setSignedIn(signedIn);
			lol.init();
		
			Scene scene = new Scene(root);
			return scene;
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	
	}
	
	/**
	 * Generates a the home scene for a doctor
	 * @return
	 */
	public Scene generateDoctorHome() {
		
		try {
			
			FXMLLoader loader = new FXMLLoader();
			
			
			Parent root = loader.load(getClass().getResourceAsStream("HomeGUIDoctor.fxml"));
			

			Scene scene = new Scene(root);
			return scene;
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	
		
	}
	
	/**
	 * Generates home scene for a patient
	 * @return
	 */
	public Scene generatePatientHome() {
		
		try {
			
			FXMLLoader loader = new FXMLLoader();
			
			
			Parent root = loader.load(getClass().getResourceAsStream("HomeGUIPatient.fxml"));
			

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

	
	/**
	 * TODO: Integrate a register button in the main screen that will lead you to this!
	 * @return a Pane (BorderPane, actually) that asks for registration data & ties to the database
	 */
public Pane generateRegistrationPanel (Scene initialScene, Stage window) {

		
		BorderPane canvas = new BorderPane();
		
		VBox internal = new VBox();
		
		internal.setMaxWidth(760);
		internal.setMinWidth(760);
		
		internal.setMinHeight(300);
		
		Label title = new Label("Submit a Registration Request");
		title.setFont(new Font(19));
		

		
		// Build the labels and textfields for their corresponding thing
	
		Label prompt = new Label("Please enter your information below:");
		prompt.setFont(new Font(16));
		
		
		Label nameTitle = new Label("Enter your name:");
		Label emailTitle = new Label("Enter your email:");
		Label phoneTitle = new Label("Enter your phone number:");
		Label passTitle = new Label("Choose a password:");
		
		TextField name = new TextField();
		TextField email = new TextField();
		TextField phone = new TextField();
		PasswordField password = new PasswordField();

		Button submit = new Button("Submit");
		
		
		// Add these all to the VBox
		
		internal.getChildren().addAll(prompt,nameTitle,name,emailTitle,email,phoneTitle,phone,passTitle,password,submit);
		
		
		// Set prompt text
		
		name.setPromptText("Name");
		email.setPromptText("Email");
		phone.setPromptText("Phone Number");
		password.setPromptText("Password");
		
		
		
		submit.setOnAction(e -> {
			// read entered data
			
			String inputName,inputEmail,inputPhone,inputPass;
			inputName = name.getText();
			inputEmail = name.getText();
			inputPhone = phone.getText();
			inputPass = password.getText();
			
			// create a registration request
			
			PendingUser newReg = null;
			
			// Make sure things are entered.
			if (inputName.length() > 0 && inputEmail.length() > 0 && inputPhone.length() > 0 && inputPass.length() > 0) {
				newReg = new PendingUser(inputName,inputEmail,inputPhone,inputPass);
			} else {
				prompt.setText("Looks like you left something blank! Please fix it up!");
			}
		
			
			// add to database
			
			if (newReg != null) {
				try {
					GUI.datab.register(newReg);
					
					// clear the ui
					internal.getChildren().clear();
					title.setText("Thanks for signing up! Please try to sign in after a few days!");
					
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			

		});
	
		
		Button back = new Button("Back");
		internal.getChildren().add(back);
	
		back.setOnAction(e-> {
			
			window.setScene(initialScene);
			
		});
		
		// Styling
		
		title.setPadding(new Insets(15,0,5,15));
		
		canvas.setTop(title);
		canvas.setCenter(internal);
		
		
		internal.setPadding(new Insets(0,15,0,15));
		internal.setSpacing(5);
		
		VBox.setMargin(submit, new Insets(8,0,0,0));
		canvas.getStylesheets().add(GUI.class.getResource("StyleSheet.css").toExternalForm());
		
		title.setStyle("-fx-text-fill: #ffffff");
		prompt.setStyle("-fx-text-fill: #ffffff");
		nameTitle.setStyle("-fx-text-fill: #ffffff");
		emailTitle.setStyle("-fx-text-fill: #ffffff");
		phoneTitle.setStyle("-fx-text-fill: #ffffff");
		passTitle.setStyle("-fx-text-fill: #ffffff");
		
		return canvas;
	}
	
	
}