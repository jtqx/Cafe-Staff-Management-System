package CSUpdateUserAccount;

import javafx.geometry.HPos;
// JavaFX imports
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

// Java imports
import java.util.Vector;

// Class imports
import main.CafeApp;

public class CSUpdateUserAccountB {

	public Scene getCSUpdateUserAccountScene() {
		GridPane csUpdateUserAccountPane = new GridPane();
		csUpdateUserAccountPane.setAlignment(Pos.CENTER);
		csUpdateUserAccountPane.setHgap(6);
		csUpdateUserAccountPane.setVgap(6);
		csUpdateUserAccountPane.setStyle("-fx-background-color: #DBDBDB;");
		
		Label instructionLabel = new Label("Please indicate your cafe role and\nmax work slots");
		csUpdateUserAccountPane.add(instructionLabel, 0, 1);
		instructionLabel.setStyle("-fx-font-size: 12; -fx-font-weight: bold;");


		Label cafeRoleLabel = new Label("Cafe Role: ");
		csUpdateUserAccountPane.add(cafeRoleLabel, 0, 2);

		TextField cafeRoleTextField = new TextField();
		csUpdateUserAccountPane.add(cafeRoleTextField, 1, 2);

		Label maxWorkSlotsLabel = new Label("Max Work Slots: ");
		csUpdateUserAccountPane.add(maxWorkSlotsLabel, 0, 3);

		TextField maxWorkSlotsTextField = new TextField();
		csUpdateUserAccountPane.add(maxWorkSlotsTextField, 1, 3);

		Button submitButton = new Button("Submit");
		csUpdateUserAccountPane.add(submitButton, 1, 4);
		submitButton.setStyle("-fx-background-color: #E8E8E8; -fx-effect: dropshadow(three-pass-box, #B9B9B9, 5, 0.5, 0, 0);");
        submitButton.setPrefWidth(80); 
        submitButton.setPrefHeight(25);
		GridPane.setHalignment(submitButton, HPos.RIGHT);

		submitButton.setOnAction(event -> {
  			String uid = CafeApp.getUserInfo("uid");
  			String cafeRole = cafeRoleTextField.getText();
  			String maxWorkSlots = maxWorkSlotsTextField.getText();

  			// Instantiate a indicate cafe role controller
  			CSUpdateUserAccountC csUpdateUserAccountC = new CSUpdateUserAccountC();

  			// Call the only method in the contoller, which returns a boolean value
  			boolean result = csUpdateUserAccountC.indicateCafeRoleAndMaxWS(uid, cafeRole, maxWorkSlots);

  			if (result == false) {
  				System.out.println("Failure");
				Alert failureAlert = new Alert(AlertType.ERROR, "Not submitted", ButtonType.CLOSE);
				failureAlert.show();
  			} else {
  				System.out.println("Success");
				/* Update the userInfo map in the CafeApp class so that the landing page will be displayed
				when getLandingScene() method is called again. */
				CafeApp.updateUserInfo("cafeRole", cafeRole);
				CafeApp.updateUserInfo("maxHours", maxWorkSlots);
				CafeApp.changeScene(CafeApp.getLandingScene());
				Alert successAlert = new Alert(AlertType.INFORMATION, "Cafe role and max work slots successfully indicated", ButtonType.CLOSE);
				successAlert.show();
  			}
    	});

		Scene csUpdateUserAccountScene = new Scene(csUpdateUserAccountPane, 500, 500);

		return csUpdateUserAccountScene;
	}
}
