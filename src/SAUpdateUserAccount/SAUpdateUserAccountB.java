package SAUpdateUserAccount;

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
import SAViewUserAccount.SAViewUserAccountB;
import SASearchUserAccount.SASearchUserAccountB;

public class SAUpdateUserAccountB {

	public Scene getSAUpdateUserAccountScene(String uid, String name, String password) {
		GridPane saUpdateUserAccountPane = new GridPane();
		saUpdateUserAccountPane.setAlignment(Pos.CENTER);
		saUpdateUserAccountPane.setHgap(6);
		saUpdateUserAccountPane.setVgap(6);
		saUpdateUserAccountPane.setStyle("-fx-background-color: #DBDBDB;");

		Text welcomeText = new Text("Update user account");
		welcomeText.setFont(Font.font("Arial", FontWeight.BOLD, 24));

		HBox welcomeBox = new HBox(welcomeText);
		welcomeBox.setAlignment(Pos.CENTER_LEFT);

		saUpdateUserAccountPane.add(welcomeBox, 1, 0);

		Label nameLabel = new Label("Name: ");
		saUpdateUserAccountPane.add(nameLabel, 0, 4);

		TextField nameTextField = new TextField(name);
		saUpdateUserAccountPane.add(nameTextField, 1, 4);

		Label passwordLabel = new Label("Password: ");
		saUpdateUserAccountPane.add(passwordLabel, 0, 6);

		TextField passwordTextField = new TextField(password);
		saUpdateUserAccountPane.add(passwordTextField, 1, 6);

		Button saveChangesButton = new Button("Save Changes");
		saUpdateUserAccountPane.add(saveChangesButton, 1, 7);
		saveChangesButton.setStyle("-fx-background-color: #E8E8E8; -fx-effect: dropshadow(three-pass-box, #B9B9B9, 5, 0.5, 0, 0);");
        saveChangesButton.setPrefWidth(100); 
        saveChangesButton.setPrefHeight(25);

		Button cancelButton = new Button("Back");
		saUpdateUserAccountPane.add(cancelButton, 1, 8);
		cancelButton.setStyle("-fx-background-color: #E8E8E8; -fx-effect: dropshadow(three-pass-box, #B9B9B9, 5, 0.5, 0, 0);");
        cancelButton.setPrefWidth(100); 
        cancelButton.setPrefHeight(25);
        HBox hbox = new HBox(10);
        GridPane.setFillWidth(hbox, true);
        GridPane.setHalignment(hbox, HPos.RIGHT);
        saUpdateUserAccountPane.add(hbox, 1, 9);
        hbox.setAlignment(Pos.CENTER_RIGHT);
		hbox.getChildren().addAll(cancelButton, saveChangesButton);

		saveChangesButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
      		public void handle(ActionEvent e) {
      			String updatedName = nameTextField.getText();
      			String updatedPassword = passwordTextField.getText();

      			if (updatedName.equals("") || updatedPassword.equals("")) {
      				System.out.println("Invalidity detected");
					Alert alert = new Alert(AlertType.WARNING, "Invalid Information Entered", ButtonType.CLOSE);
					alert.show();
					return;
				}

      			// Instantiate an update account controller
      			SAUpdateUserAccountC saUpdateUserAccountC = new SAUpdateUserAccountC();

      			// Call the only method in the controller, which will return a boolean value
      			boolean result = saUpdateUserAccountC.updateUserAccount(uid, updatedName, updatedPassword);

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
      			SAViewUserAccountB saViewUserAccountB = new SAViewUserAccountB();
      			SASearchUserAccountB.changeSecondaryWindowScene(saViewUserAccountB.getSAViewUserAccountScene(uid));
      		}
      	});

		Scene saUpdateUserAccountScene = new Scene(saUpdateUserAccountPane, 500, 500);
		
		return saUpdateUserAccountScene;
	}
}
