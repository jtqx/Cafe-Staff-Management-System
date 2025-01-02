package SAUpdateUserProfile;

// Class imports
import Entities.UserProfile;

public class SAUpdateUserProfileC {

	public boolean updateUserProfile(String role, String updatedDesc) {

		boolean result = false;

		UserProfile userProf = new UserProfile();

		try {
			result = userProf.updateDB(role, updatedDesc);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
			
		return result;
	}
}
