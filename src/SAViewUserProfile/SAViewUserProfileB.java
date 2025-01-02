package SAViewUserProfile;

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
import SASearchUserProfile.SASearchUserProfileB;
import SAUpdateUserProfile.SAUpdateUserProfileB;
import SASuspendUserProfile.SASuspendUserProfileB;

public class SAViewUserProfileB {

    public Scene getSAViewUserProfileScene(String role) {
		// Instantiate a view user profile controller
      	SAViewUserProfileC saViewUserProfileC = new SAViewUserProfileC();

      	Map<String, String> userProfileInfo = new HashMap<>();

      	// Call the only method in the controller, which will return a map of user profile information
      	userProfileInfo = saViewUserProfileC.viewUserProfile(role);

		GridPane saViewUserProfilePane = new GridPane();
		saViewUserProfilePane.setAlignment(Pos.CENTER);
		saViewUserProfilePane.setHgap(6);
		saViewUserProfilePane.setVgap(6);
		saViewUserProfilePane.setStyle("-fx-background-color: #DBDBDB;");

		Text welcomeText = new Text("View user profile");
		welcomeText.setFont(Font.font("Arial", FontWeight.BOLD, 24));

		HBox welcomeBox = new HBox(welcomeText);
		welcomeBox.setAlignment(Pos.CENTER_LEFT);

		saViewUserProfilePane.add(welcomeBox, 1, 0);

		Label roleLabel = new Label("Role: ");
		saViewUserProfilePane.add(roleLabel, 0, 2);

		TextField roleTextField = new TextField(userProfileInfo.get("role"));
		saViewUserProfilePane.add(roleTextField, 1, 2);
		roleTextField.setEditable(false);

		Label descLabel = new Label("Description: ");
		saViewUserProfilePane.add(descLabel, 0, 3);

		TextField descTextField = new TextField(userProfileInfo.get("description"));
		saViewUserProfilePane.add(descTextField, 1, 3);
		descTextField.setEditable(false);

		Button editButton = new Button("Edit");
		editButton.setStyle("-fx-background-color: #E8E8E8; -fx-effect: dropshadow(three-pass-box, #B9B9B9, 5, 0.5, 0, 0);");
        editButton.setPrefWidth(80); 
        editButton.setPrefHeight(25);
        HBox hbox = new HBox(10);
        GridPane.setFillWidth(hbox, true);
        GridPane.setHalignment(hbox, HPos.RIGHT);
        saViewUserProfilePane.add(hbox, 1, 5);
        hbox.setAlignment(Pos.CENTER_RIGHT);

		Button cancelButton = new Button("Back");
		saViewUserProfilePane.add(cancelButton,1, 6);
		cancelButton.setStyle("-fx-background-color: #E8E8E8; -fx-effect: dropshadow(three-pass-box, #B9B9B9, 5, 0.5, 0, 0);");
        cancelButton.setPrefWidth(80); 
        cancelButton.setPrefHeight(25);

		Button suspendButton = new Button("Suspend");
		Button reinstateButton = new Button("Reinstate");

		if (userProfileInfo.get("isSuspended").equals("0")) {
			saViewUserProfilePane.add(suspendButton, 1, 8);
			suspendButton.setStyle("-fx-background-color: #E8E8E8; -fx-effect: dropshadow(three-pass-box, #B9B9B9, 5, 0.5, 0, 0);");
			suspendButton.setPrefWidth(80); 
			suspendButton.setPrefHeight(25);
			GridPane.setHalignment(suspendButton, HPos.RIGHT);
		} else {
			saViewUserProfilePane.add(reinstateButton, 1, 8);
			reinstateButton.setStyle("-fx-background-color: #E8E8E8; -fx-effect: dropshadow(three-pass-box, #B9B9B9, 5, 0.5, 0, 0);");
			reinstateButton.setPrefWidth(80); 
			reinstateButton.setPrefHeight(25);	
			GridPane.setHalignment(reinstateButton, HPos.RIGHT);
		}
		hbox.getChildren().addAll(cancelButton, editButton);

		cancelButton.setOnAction(event -> {
			//SAViewUserAccountB saViewUserAccountB = new SAViewUserAccountB();
			//SASearchUserAccountB.changeSecondaryWindowScene(saViewUserAccountB.getSAViewUserAccountScene(uid));
			CafeApp.changeScene(CafeApp.getLandingScene());
      		
      	});

		editButton.setOnAction(event -> {
  			// Instantiate an update profile boundary
  			SAUpdateUserProfileB saUpdateUserProfileB = new SAUpdateUserProfileB();
  			Scene saUpdateUserProfileScene = saUpdateUserProfileB.getSAUpdateUserProfileScene(
  				roleTextField.getText(), descTextField.getText());
  			SASearchUserProfileB.changeSecondaryWindowScene(saUpdateUserProfileScene);
    	});

    	suspendButton.setOnAction(event -> {
  			// Instantiate a suspend profile boundary
  			SASuspendUserProfileB saSuspendUserProfileB = new SASuspendUserProfileB();
  			saSuspendUserProfileB.getSASuspendUserProfileResult(roleTextField.getText());
    	});

    	reinstateButton.setOnAction(event -> {
  			// Instantiate a suspend profile boundary
  			SASuspendUserProfileB saSuspendUserProfileB = new SASuspendUserProfileB();
  			saSuspendUserProfileB.getSAReinstateUserProfileResult(roleTextField.getText());
    	});

		Scene saViewUserProfileScene = new Scene(saViewUserProfilePane, 500, 500);

    	return saViewUserProfileScene;
	}
}
