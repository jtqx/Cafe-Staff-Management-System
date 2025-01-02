package SASuspendUserProfile;

// JavaFX imports
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

// Class imports
import Entities.UserProfile;

public class SASuspendUserProfileB {
	public void getSASuspendUserProfileResult(String role) {
		// Instantiate a suspend user profile controller
		SASuspendUserProfileC saSuspendUserProfileC = new SASuspendUserProfileC();
		// Call the suspendUserProfile() method in the controller, which returns a boolean value
		boolean result = saSuspendUserProfileC.suspendUserProfile(role);

		// Display the result of the operation
		if (result == false) {
			// The only time the operation returns a 'false' value is when the system administrator tries to suspend their own user profile
			Alert failureAlert = new Alert(AlertType.ERROR, "Suspension of the 'System Administrator' user profile is not allowed", ButtonType.CLOSE);
			failureAlert.show();
  		} else {
			Alert successAlert = new Alert(AlertType.INFORMATION, "User profile suspended", ButtonType.CLOSE);
			successAlert.show();
  		}
	}

	public void getSAReinstateUserProfileResult(String role) {
		// Instantiate a suspend user profile controller
		SASuspendUserProfileC saSuspendUserProfileC = new SASuspendUserProfileC();
		// Call the reinstateUserProfile() method in the controller, which returns a boolean value
		boolean result = saSuspendUserProfileC.reinstateUserProfile(role);

		// Display the result of the operation
		if (result == false) {
			Alert failureAlert = new Alert(AlertType.ERROR, "User profile not reinstated", ButtonType.CLOSE);
			failureAlert.show();
  		} else {
			Alert successAlert = new Alert(AlertType.INFORMATION, "User profile reinstated", ButtonType.CLOSE);
			successAlert.show();
  		}
	}
}
