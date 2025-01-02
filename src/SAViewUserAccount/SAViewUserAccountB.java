package SAViewUserAccount;

import javafx.geometry.HPos;
// JavaFX imports
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import main.CafeApp;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;

// Java imports
import java.util.HashMap;
import java.util.Map;

// Class imports
import SASearchUserAccount.SASearchUserAccountB;
import SAUpdateUserAccount.SAUpdateUserAccountB;
import SASuspendUserAccount.SASuspendUserAccountB;

public class SAViewUserAccountB {

	public Scene getSAViewUserAccountScene(String uid) {
		// Instantiate a view user account controller
      	SAViewUserAccountC saViewUserAccountC = new SAViewUserAccountC();

      	Map<String, String> userAccountInfo = new HashMap<>();

      	// Call the only method in the controller, which will return a map of user information
      	userAccountInfo = saViewUserAccountC.viewUserAccount(uid);

		GridPane saViewUserAccountPane = new GridPane();
		saViewUserAccountPane.setAlignment(Pos.CENTER);
		saViewUserAccountPane.setHgap(6);
		saViewUserAccountPane.setVgap(6);
		saViewUserAccountPane.setStyle("-fx-background-color: #DBDBDB;");

		Text welcomeText = new Text("View user account");
		welcomeText.setFont(Font.font("Arial", FontWeight.BOLD, 24));

		HBox welcomeBox = new HBox(welcomeText);
		welcomeBox.setAlignment(Pos.CENTER_LEFT);

		saViewUserAccountPane.add(welcomeBox, 1, 0);

		Label userIDLabel = new Label("User ID: ");
		saViewUserAccountPane.add(userIDLabel, 0, 3);

		TextField userIDTextField = new TextField(userAccountInfo.get("uid"));
		saViewUserAccountPane.add(userIDTextField, 1, 3);
		userIDTextField.setEditable(false);

		Label nameLabel = new Label("Name: ");
		saViewUserAccountPane.add(nameLabel, 0, 4);

		TextField nameTextField = new TextField(userAccountInfo.get("name"));
		saViewUserAccountPane.add(nameTextField, 1, 4);
		nameTextField.setEditable(false);

		Label passwordLabel = new Label("Password: ");
		saViewUserAccountPane.add(passwordLabel, 0, 5);

		TextField passwordTextField = new TextField(userAccountInfo.get("password"));
		saViewUserAccountPane.add(passwordTextField, 1, 5);
		passwordTextField.setEditable(false);

		Label userProfileLabel = new Label("User Profile: ");
		saViewUserAccountPane.add(userProfileLabel, 0, 6);

		TextField userProfileTextField = new TextField(userAccountInfo.get("userProfile"));
		saViewUserAccountPane.add(userProfileTextField, 1, 6);
		userProfileTextField.setEditable(false);

		if(CafeApp.getUserInfo("userProfile") != null && CafeApp.getUserInfo("userProfile").equals("System Administrator")) {
			Button editButton = new Button("Edit");
			editButton.setStyle("-fx-background-color: #E8E8E8; -fx-effect: dropshadow(three-pass-box, #B9B9B9, 5, 0.5, 0, 0);");
			editButton.setPrefWidth(80); 
			editButton.setPrefHeight(25);
			HBox hbox = new HBox(10);
			GridPane.setFillWidth(hbox, true);
			GridPane.setHalignment(hbox, HPos.RIGHT);
			saViewUserAccountPane.add(hbox, 1, 8);
			hbox.setAlignment(Pos.CENTER_RIGHT);
	
			Button suspendButton = new Button("Suspend");
			Button reinstateButton = new Button("Reinstate");

			Button backBtn = new Button("Back");
			saViewUserAccountPane.add(backBtn, 1, 7);
			backBtn.setStyle("-fx-background-color: #E8E8E8; -fx-effect: dropshadow(three-pass-box, #B9B9B9, 5, 0.5, 0, 0);");
			backBtn.setPrefWidth(80); 
			backBtn.setPrefHeight(25);
	
			if (userAccountInfo.get("isSuspended").equals("0")) {
				suspendButton.setStyle("-fx-background-color: #E8E8E8; -fx-effect: dropshadow(three-pass-box, #B9B9B9, 5, 0.5, 0, 0);");
				suspendButton.setPrefWidth(80); 
				suspendButton.setPrefHeight(25);
				saViewUserAccountPane.add(suspendButton, 1, 10);
				GridPane.setHalignment(suspendButton, HPos.RIGHT);
			} else {
				reinstateButton.setStyle("-fx-background-color: #E8E8E8; -fx-effect: dropshadow(three-pass-box, #B9B9B9, 5, 0.5, 0, 0);");
				reinstateButton.setPrefWidth(80); 
				reinstateButton.setPrefHeight(25);
				saViewUserAccountPane.add(reinstateButton, 1, 10);
				GridPane.setHalignment(reinstateButton, HPos.RIGHT);
			}
			hbox.getChildren().addAll(backBtn, editButton);

			backBtn.setOnAction(event -> {
				CafeApp.changeScene(CafeApp.getLandingScene());
			  });
	
			editButton.setOnAction(event -> {
				  // Instantiate an update account boundary
				  SAUpdateUserAccountB saUpdateUserAccountB = new SAUpdateUserAccountB();
				  Scene saUpdateUserAccountScene = saUpdateUserAccountB.getSAUpdateUserAccountScene(
					  userIDTextField.getText(), nameTextField.getText(), passwordTextField.getText());
				  SASearchUserAccountB.changeSecondaryWindowScene(saUpdateUserAccountScene);
			});
	
			suspendButton.setOnAction(event -> {
				  // Instantiate a suspend account boundary
				  SASuspendUserAccountB saSuspendUserAccountB = new SASuspendUserAccountB();
				  saSuspendUserAccountB.getSASuspendUserAccountResult(userIDTextField.getText());
			});
	
			reinstateButton.setOnAction(event -> {
				  // Instantiate a suspend account boundary
				  SASuspendUserAccountB saSuspendUserAccountB = new SASuspendUserAccountB();
				  saSuspendUserAccountB.getSAReinstateUserAccountResult(userIDTextField.getText());
			});
		}
		else if(CafeApp.getUserInfo("userProfile") != null && CafeApp.getUserInfo("userProfile").equals("Cafe Manager")) {
			Button backBtn = new Button("Back");
			saViewUserAccountPane.add(backBtn, 1, 7);
			backBtn.setStyle("-fx-background-color: #E8E8E8; -fx-effect: dropshadow(three-pass-box, #B9B9B9, 5, 0.5, 0, 0);");
			backBtn.setPrefWidth(80); 
			backBtn.setPrefHeight(25);
			GridPane.setHalignment(backBtn, HPos.RIGHT);
			backBtn.setOnAction(event -> {
				CafeApp.changeScene(CafeApp.getLandingScene());
			  });
	
		}

		Scene saViewUserAccountScene = new Scene(saViewUserAccountPane, 500, 500);

    	return saViewUserAccountScene;
	}
}
