package SAViewUserProfile;

// Java imports
import java.sql.SQLException;
import java.util.Vector;
import java.util.HashMap;
import java.util.Map;

// Class imports
import Entities.UserAccount;
import Entities.UserProfile;

public class SAViewUserProfileC {

	public Map<String, String> viewUserProfile(String role) {
		UserProfile userProf = new UserProfile();
		Map<String, String> infoMap = userProf.getUserProfile(role);
		return infoMap;
	}
}
