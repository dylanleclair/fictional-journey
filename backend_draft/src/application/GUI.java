package application;

import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Popup;
import javafx.stage.Stage;



public class GUI extends Application {
	
	String user = "Moe";
    String pw = "password";
    String checkUser, checkPW;
	@Override
	public void start(Stage primaryStage) throws Exception {        // the GUI class
		
		
		
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
					window.setScene(scene2);
				}
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

		
		Button newButton0  = new Button();
		newButton0.setText ("Admin");
		newButton0.setPadding(new Insets(5,5,5,5));
		newButton0.setPrefSize(100, 20);
		newButton0.setOnAction(e -> window.setScene(scene1));
		
		Button newButton1  = new Button();
		newButton1.setText ("Staff");
		newButton1.setPadding(new Insets(5,5,5,5));
		newButton1.setPrefSize(100, 20);
		newButton1.setOnAction(e -> window.setScene(scene1));
		
		Button newButton2  = new Button();
		newButton2.setText ("Patient");
		newButton2.setPadding(new Insets(5,5,5,5));
		newButton2.setPrefSize(100, 20);
		newButton2.setOnAction(e -> window.setScene(scene1));

		userSelection.add(newButton0, 0,1);
		userSelection.add(newButton1, 1,1);
		userSelection.add(newButton2, 2,1);
		
		window.setScene(scene);
		window.show();
		
	}
	

	public static void main (String[] args) {
		
		
		launch(args);
		
	}

	
}