package SAUpdateUserProfile;

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
import SAViewUserProfile.SAViewUserProfileB;
import SASearchUserProfile.SASearchUserProfileB;

public class SAUpdateUserProfileB {

	public Scene getSAUpdateUserProfileScene(String role, String description) {
		GridPane saUpdateUserProfilePane = new GridPane();
		saUpdateUserProfilePane.setAlignment(Pos.CENTER);
		saUpdateUserProfilePane.setHgap(6);
		saUpdateUserProfilePane.setVgap(6);
		saUpdateUserProfilePane.setStyle("-fx-background-color: #DBDBDB;");

		Text welcomeText = new Text("Update user profile");
		welcomeText.setFont(Font.font("Arial", FontWeight.BOLD, 24));

		HBox welcomeBox = new HBox(welcomeText);
		welcomeBox.setAlignment(Pos.CENTER_LEFT);

		saUpdateUserProfilePane.add(welcomeBox, 1, 0);

		Label descLabel = new Label("Description: ");
		saUpdateUserProfilePane.add(descLabel, 0, 4);

		TextField descTextField = new TextField(description);
		saUpdateUserProfilePane.add(descTextField, 1, 4);

		Button saveChangesButton = new Button("Save Changes");
		saUpdateUserProfilePane.add(saveChangesButton, 1, 5);
		saveChangesButton.setStyle("-fx-background-color: #E8E8E8; -fx-effect: dropshadow(three-pass-box, #B9B9B9, 5, 0.5, 0, 0);");
        saveChangesButton.setPrefWidth(100); 
        saveChangesButton.setPrefHeight(25);

		Button cancelButton = new Button("Back");
		cancelButton.setStyle("-fx-background-color: #E8E8E8; -fx-effect: dropshadow(three-pass-box, #B9B9B9, 5, 0.5, 0, 0);");
        cancelButton.setPrefWidth(100); 
        cancelButton.setPrefHeight(25);
        HBox hbox = new HBox(10);
        GridPane.setFillWidth(hbox, true);
        GridPane.setHalignment(hbox, HPos.RIGHT);
        saUpdateUserProfilePane.add(hbox, 1, 6);
        hbox.setAlignment(Pos.CENTER_RIGHT);
		hbox.getChildren().addAll(cancelButton, saveChangesButton);

		saveChangesButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
      		public void handle(ActionEvent e) {
      			String updatedDesc = descTextField.getText();

      			if (updatedDesc.equals("")) {
      				System.out.println("Invalidity detected");
					Alert alert = new Alert(AlertType.WARNING, "Invalid Information Entered", ButtonType.CLOSE);
					alert.show();
					return;
				}

      			// Instantiate an update account controller
      			SAUpdateUserProfileC saUpdateUserProfileC = new SAUpdateUserProfileC();

      			// Call the only method in the controller, which will return a boolean value
      			boolean result = saUpdateUserProfileC.updateUserProfile(role, updatedDesc);

      			if (result == false) {
      				System.out.println("Failure");
					Alert alert = new Alert(AlertType.ERROR, "Changes not saved", ButtonType.CLOSE);
					alert.show();
      			} else {
      				System.out.println("Success");
					Alert alert = new Alert(AlertType.INFORMATION, "Changes successfully saved", ButtonType.CLOSE);
					alert.show();
      			}
      		}
    	});

    	cancelButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
      		public void handle(ActionEvent e) {
      			SAViewUserProfileB saViewUserProfileB = new SAViewUserProfileB();
      			SASearchUserProfileB.changeSecondaryWindowScene(saViewUserProfileB.getSAViewUserProfileScene(role));
      		}
      	});

		Scene saUpdateUserProfileScene = new Scene(saUpdateUserProfilePane, 500, 500);
		
		return saUpdateUserProfileScene;
	}
}
