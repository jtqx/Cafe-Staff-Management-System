package SASuspendUserProfile;

// JavaFX imports
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

// Class imports
import Entities.UserProfile;

public class SASuspendUserProfileC {
	public boolean suspendUserProfile(String role) {
		// Instantiate a user profile entity
		UserProfile userProfile = new UserProfile();
		// Call the suspendUserProfile() method in the entity, which returns a boolean value
		boolean result = false;

		// Disallow the suspension of the system administrator user profile
		if (role.equals("System Administrator")) {
			return result;
		}

		try {
			result = userProfile.suspendUserProfile(role);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return result;
	}

	public boolean reinstateUserProfile(String role) {
		// Instantiate a user profile entity
		UserProfile userProfile = new UserProfile();
		// Call the suspendUserProfile() method in the entity, which returns a boolean value
		boolean result = false;

		try {
			result = userProfile.reinstateUserProfile(role);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return result;
	}
}
