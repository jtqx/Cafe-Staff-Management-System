package LogOut;

// JavaFX imports
import javafx.scene.Scene;

// Class imports
import main.CafeApp;
import LogIn.LoginB;

public class LogoutB {
	public void displayLoginPage() {
		// Instantiate a log in boundary
		LoginB loginB = new LoginB();

		// Call the only method in the boundary, which returns the log in scene
		Scene loginScene = loginB.getLoginScene();

		// Display the log in scene
		CafeApp.changeScene(loginScene);
	}
}