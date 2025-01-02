package SASuspendUserAccount;

// JavaFX imports
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

// Class imports
import Entities.UserAccount;

public class SASuspendUserAccountB {
	public void getSASuspendUserAccountResult(String uid) {
		// Instantiate a suspend user account controller
		SASuspendUserAccountC saSuspendUserAccountC = new SASuspendUserAccountC();
		// Call the suspendUserAccount() method in the controller, which returns a boolean value
		boolean result = saSuspendUserAccountC.suspendUserAccount(uid);

		// Display the result of the operation
		if (result == false) {
			// The only time the operation returns a 'false' value is when the system administrator tries to suspend their own account
			Alert failureAlert = new Alert(AlertType.ERROR, "Suspension of the your own account is not allowed", ButtonType.CLOSE);
			failureAlert.show();
  		} else {
			Alert successAlert = new Alert(AlertType.INFORMATION, "User account suspended", ButtonType.CLOSE);
			successAlert.show();
  		}
	}

	public void getSAReinstateUserAccountResult(String uid) {
		// Instantiate a suspend user account controller
		SASuspendUserAccountC saSuspendUserAccountC = new SASuspendUserAccountC();
		// Call the reinstateUserAccount() method in the controller, which returns a boolean value
		boolean result = saSuspendUserAccountC.reinstateUserAccount(uid);

		// Display the result of the operation
		if (result == false) {
			Alert failureAlert = new Alert(AlertType.ERROR, "User account not reinstated", ButtonType.CLOSE);
			failureAlert.show();
  		} else {
			Alert successAlert = new Alert(AlertType.INFORMATION, "User account reinstated", ButtonType.CLOSE);
			successAlert.show();
  		}
	}
}
