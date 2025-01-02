package SASuspendUserAccount;

// JavaFX imports
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

// Class imports
import Entities.UserAccount;
import main.CafeApp;

public class SASuspendUserAccountC {
	public boolean suspendUserAccount(String uid) {
		// Instantiate a user profile entity
		UserAccount userAccount = new UserAccount();
		// Call the suspendUserAccount() method in the entity, which returns a boolean value
		boolean result = false;

		// Disallow the suspension of the system administrator's own account
		if (CafeApp.getUserInfo("uid").equals(uid)) {
			return result;
		}

		try {
			result = userAccount.suspendUserAccount(uid);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return result;
	}

	public boolean reinstateUserAccount(String uid) {
		// Instantiate a user profile entity
		UserAccount userAccount = new UserAccount();
		// Call the suspendUserAccount() method in the entity, which returns a boolean value
		boolean result = false;

		try {
			result = userAccount.reinstateUserAccount(uid);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return result;
	}
}
