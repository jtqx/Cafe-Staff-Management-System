package SACreateUserProfile;

// Class imports
import Entities.UserProfile;

public class SACreateUserProfileC {

	public boolean createProfile(String role, String description, String isSuspended) {

		UserProfile userProf = new UserProfile();

		boolean result = true;
			
		try {
			result = userProf.insertToDB(role, description, isSuspended);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return result;
	}
}
