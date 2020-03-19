package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AlertBox {
	
	public static void display(String title, String message) {
		Stage window = new Stage();

		window.initModality(Modality.APPLICATION_MODAL);
		window.setMinWidth(220);
		window.setMinHeight(150);
		
		Label label = new Label();
		label.setText(message);
		
		label.setPadding(new Insets(20, 0, 20, 0));
		label.setAlignment(Pos.TOP_CENTER);
		Button closeButton = new Button("Close");
		
		closeButton.setAlignment(Pos.BOTTOM_CENTER);
		closeButton.setPadding(new Insets(5, 30, 5, 30)); 
		closeButton.setOnAction(e -> window.close());
		
		VBox layout = new VBox();
		layout.getChildren().addAll(label, closeButton);
		layout.setAlignment(Pos.CENTER);
		
		
		Scene scene = new Scene(layout);
		//window.initStyle(StageStyle.UTILITY);
		window.setScene(scene);
		window.showAndWait();
		
	}
}
