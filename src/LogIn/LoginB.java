package LogIn;

// Java imports
import java.util.Map;

// JavaFX imports
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;

// Class imports
import main.CafeApp;

public class LoginB {
	public Scene getLoginScene() {
		GridPane loginPane = new GridPane();
		loginPane.setAlignment(Pos.CENTER);
		loginPane.setHgap(6);
		loginPane.setVgap(6);

		loginPane.setStyle("-fx-background-color: #DBDBDB;");

		Image image = new Image("resources/user.png");

		// Create an ImageView and set the image
        ImageView imageView = new ImageView(image);

		imageView.setFitWidth(70); // Set the desired width
        imageView.setFitHeight(70); // Set the desired height

        // Add the ImageView to the GridPane
		loginPane.add(imageView, 1, 0, 2, 1);
		GridPane.setHalignment(imageView, HPos.CENTER);

		Label userIDLabel = new Label("User ID: ");
		loginPane.add(userIDLabel, 0, 2);

		TextField userIDTextField = new TextField();
		loginPane.add(userIDTextField, 1, 2);

		Label passwordLabel = new Label("Password: ");
		loginPane.add(passwordLabel, 0, 4);

		PasswordField passwordTextField = new PasswordField();
		loginPane.add(passwordTextField, 1, 4);

		userIDTextField.setStyle("-fx-background-color: #FFFFFF;"); // White background
        passwordTextField.setStyle("-fx-background-color: #FFFFFF;"); // White background
		
		Button loginButton = new Button("Login");
		loginPane.add(loginButton, 1, 6);
		GridPane.setHalignment(loginButton, HPos.RIGHT);
		loginButton.setStyle("-fx-background-color: #F2CB56; -fx-effect: dropshadow(three-pass-box, #D9B342, 5, 0.5, 0, 0);");
		loginButton.setPrefWidth(150);
        loginButton.setPrefHeight(25);
		
		loginButton.setOnAction(event -> {
			String uid = userIDTextField.getText();
			String password = passwordTextField.getText();

			// Instantiate a login controller
			LoginC loginC = new LoginC();

			// Call the only method in the controller, which will return a map of user account information
			Map<String, String> infoMap = loginC.loginUser(uid, password);
			if (infoMap != null) {
				CafeApp.addUserInfo(infoMap);
				CafeApp.changeScene(CafeApp.getLandingScene());
			}
			else {
				Alert alert = new Alert(AlertType.WARNING, "Login Failure", ButtonType.CLOSE);
				alert.show();
			}
		});
		
		Scene loginScene = new Scene(loginPane, 500, 500);

		return loginScene;
	}
}
