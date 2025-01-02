package SACreateUserAccount;

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
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;

// Java imports
import java.util.Vector;

// Class imports
import main.CafeApp;

public class SACreateUserAccountB {

	public Scene getSACreateUserAccountScene() {
		GridPane saCreateUserAccountPane = new GridPane();
		saCreateUserAccountPane.setAlignment(Pos.CENTER);
		//pane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
		saCreateUserAccountPane.setHgap(6);
		saCreateUserAccountPane.setVgap(6);

		saCreateUserAccountPane.setStyle("-fx-background-color: #DBDBDB;");

		Text welcomeText = new Text("Create user account");
		welcomeText.setFont(Font.font("Arial", FontWeight.BOLD, 24));

		HBox welcomeBox = new HBox(welcomeText);
		welcomeBox.setAlignment(Pos.CENTER_LEFT);

		saCreateUserAccountPane.add(welcomeBox, 1, 0);

		Label userIDLabel = new Label("User ID: ");
		saCreateUserAccountPane.add(userIDLabel, 0, 4);

		TextField userIDTextField = new TextField();
		saCreateUserAccountPane.add(userIDTextField, 1, 4);

		Label nameLabel = new Label("Name: ");
		saCreateUserAccountPane.add(nameLabel, 0, 5);

		TextField nameTextField = new TextField();
		saCreateUserAccountPane.add(nameTextField, 1, 5);

		Label passwordLabel = new Label("Password: ");
		saCreateUserAccountPane.add(passwordLabel, 0, 6);

		TextField passwordTextField = new TextField();
		saCreateUserAccountPane.add(passwordTextField, 1, 6);

		Label userProfileLabel = new Label("User Profile: ");
		saCreateUserAccountPane.add(userProfileLabel, 0, 7);

		// Instantiate a create account controller
		SACreateUserAccountC saCreateUserAccountC = new SACreateUserAccountC();

		Vector<String> userProfileRoles = new Vector<>();

		// The getUserProfileRoles() method in the controller returns a vector of user profile roles
		userProfileRoles = saCreateUserAccountC.getUserProfileRoles();

		ComboBox userProfileRolesComboBox = new ComboBox();

		for (String userProfileRole : userProfileRoles) {
			userProfileRolesComboBox.getItems().add(userProfileRole);
		}

        saCreateUserAccountPane.add(userProfileRolesComboBox, 1, 7);
		userProfileRolesComboBox.setPrefWidth(230); 

		Button backButton = new Button("Back");
		backButton.setStyle("-fx-background-color: #E8E8E8; -fx-effect: dropshadow(three-pass-box, #B9B9B9, 5, 0.5, 0, 0);");
        backButton.setPrefWidth(80); 
        backButton.setPrefHeight(25);
        saCreateUserAccountPane.add(backButton, 0, 10);

		Button createButton = new Button("Create");
		createButton.setStyle("-fx-background-color: #E8E8E8; -fx-effect: dropshadow(three-pass-box, #B9B9B9, 5, 0.5, 0, 0);");
        createButton.setPrefWidth(80); 
        createButton.setPrefHeight(25);
        HBox hbox = new HBox(10);
        GridPane.setFillWidth(hbox, true);
        GridPane.setHalignment(hbox, HPos.RIGHT);
        saCreateUserAccountPane.add(hbox, 1, 10);
        hbox.setAlignment(Pos.CENTER_RIGHT);
		hbox.getChildren().addAll(backButton, createButton);

		createButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
      		public void handle(ActionEvent e) {
      			String uid = userIDTextField.getText();
      			String name = nameTextField.getText();
      			String password = passwordTextField.getText();
      			String userProfileRole = userProfileRolesComboBox.getValue().toString();
      			String isSuspended = "0";

      			if (uid.equals("") || name.equals("") || password.equals("") || userProfileRolesComboBox.getValue() == null) {
      				System.out.println("Invalidity detected");
					Alert alert = new Alert(AlertType.WARNING, "Invalid Information Entered", ButtonType.CLOSE);
					alert.show();
					return;
				}

      			/* The createUserAccount() method in the controller returns a boolean value which is used to determine
				if the user account has been successfully created */
      			boolean result = saCreateUserAccountC.createUserAccount(uid, name, password, userProfileRole, isSuspended);

      			if (result == false) {
      				System.out.println("Failure");
					Alert alert = new Alert(AlertType.ERROR, "Account already exists", ButtonType.CLOSE);
					alert.show();
      			} else {
      				System.out.println("Success");
					Alert alert = new Alert(AlertType.INFORMATION, "Account successfully created", ButtonType.CLOSE);
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

		Scene saCreateUserAccountScene = new Scene(saCreateUserAccountPane, 500, 500);

		return saCreateUserAccountScene;
	}
}
