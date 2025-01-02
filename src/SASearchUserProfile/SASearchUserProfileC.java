package SASearchUserProfile;

// Java imports
import java.util.Vector;
import java.util.Map;

// Class imports
import Entities.UserProfile;

public class SASearchUserProfileC {

	public Map<String, String> searchUserProfile(String role) {
		UserProfile userProfile = new UserProfile();
		Map<String, String> userProfileMap = userProfile.getUserProfile(role);
		
		return userProfileMap;
	}
}
