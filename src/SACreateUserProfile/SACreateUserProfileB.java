package SACreateUserProfile;

import javafx.geometry.HPos;
// JavaFX imports
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

// Class imports
import main.CafeApp;

public class SACreateUserProfileB {

	public Scene getSACreateUserProfileScene() {
		GridPane saCreateUserProfilePane = new GridPane();
		saCreateUserProfilePane.setAlignment(Pos.CENTER);
		saCreateUserProfilePane.setHgap(6);
		saCreateUserProfilePane.setVgap(6);
		saCreateUserProfilePane.setStyle("-fx-background-color: #DBDBDB;");

		Text welcomeText = new Text("Create user profile");
		welcomeText.setFont(Font.font("Arial", FontWeight.BOLD, 24));

		HBox welcomeBox = new HBox(welcomeText);
		welcomeBox.setAlignment(Pos.CENTER_LEFT);

		saCreateUserProfilePane.add(welcomeBox, 1, 0);

		Label roleLabel = new Label("Role: ");
		saCreateUserProfilePane.add(roleLabel, 0, 4);

		TextField roleTextField= new TextField();
		saCreateUserProfilePane.add(roleTextField, 1, 4);

		Label roleDescLabel = new Label("Description: ");
		saCreateUserProfilePane.add(roleDescLabel, 0, 6);

		TextField roleDescTextField = new TextField();
		saCreateUserProfilePane.add(roleDescTextField, 1, 6);

		Button backButton = new Button("Back");
		backButton.setStyle("-fx-background-color: #E8E8E8; -fx-effect: dropshadow(three-pass-box, #B9B9B9, 5, 0.5, 0, 0);");
        backButton.setPrefWidth(80); 
        backButton.setPrefHeight(25);
        saCreateUserProfilePane.add(backButton, 0, 8);

		Button createButton = new Button("Create");
		createButton.setStyle("-fx-background-color: #E8E8E8; -fx-effect: dropshadow(three-pass-box, #B9B9B9, 5, 0.5, 0, 0);");
        createButton.setPrefWidth(80); 
        createButton.setPrefHeight(25);
        HBox hbox = new HBox(10);
        GridPane.setFillWidth(hbox, true);
        GridPane.setHalignment(hbox, HPos.RIGHT);
        saCreateUserProfilePane.add(hbox, 1, 8);
        hbox.setAlignment(Pos.CENTER_RIGHT);
		hbox.getChildren().addAll(backButton, createButton);

		createButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
      		public void handle(ActionEvent e) {
      			String role = roleTextField.getText();
      			String description = roleDescTextField.getText();
      			String isSuspended = "0";

      			if (role.equals("") || description.equals("")) {
      				System.out.println("Invalidity detected");
					Alert alert = new Alert(AlertType.WARNING, "Invalid Information Entered", ButtonType.CLOSE);
					alert.show();
					return;
				}

      			// Instantiate a create profile controller
      			SACreateUserProfileC saCreateUserProfileC = new SACreateUserProfileC();

      			// Call the only method in the controller, which will return a boolean value
      			boolean result = saCreateUserProfileC.createProfile(role, description, isSuspended);

      			if (result == false) {
      				System.out.println("Failure");
					Alert alert = new Alert(AlertType.ERROR, "Profile already exists", ButtonType.CLOSE);
					alert.show();
      			} else {
      				System.out.println("Success");
					Alert alert = new Alert(AlertType.INFORMATION, "Profile successfully created", ButtonType.CLOSE);
					alert.show();
      			}
      		}
    	});

		backButton.setOnAction(new EventHandler<ActionEvent>() {
    		@Override
      		public void handle(ActionEvent e) {
    			CafeApp.changeScene(CafeApp.getLandingScene());
    		}
    	});

		Scene saCreateUserProfileScene = new Scene(saCreateUserProfilePane, 500, 500);

		return saCreateUserProfileScene;
	}
}
