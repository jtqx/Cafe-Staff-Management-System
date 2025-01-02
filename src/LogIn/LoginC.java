package LogIn;

// Java imports
import java.util.Map;

// Class imports
import Entities.UserAccount;
import Entities.UserProfile;

public class LoginC {

	public Map<String, String> loginUser(String uid, String password) {
		// Instantiate a user account entity object
		UserAccount userAccount = new UserAccount();

		/* The getUserAccount() method will return a map of user account information if a matching
		row exists in the database, otherwise it returns a null map */
		Map<String, String> userAccountInfoMap = userAccount.getUserAccount(uid, password);

		/* If userAccountInfoMap is still null, the user has entered either an invalid uid or password.
		Return it to the login boundary to display the appropriate page */
		if (userAccountInfoMap == null) {
			return userAccountInfoMap;
		}

		// Check if the user account is suspended. If it is, nullify userAccountInfoMap and return it
		if (userAccountInfoMap.get("isSuspended").equals("1")) {
			userAccountInfoMap = null;
			return userAccountInfoMap;
		}

		// Instantiate a user profile entity object
		UserProfile userProfile = new UserProfile();

		/* The getUserProfile() method will return a map of user profile information according to the 
		user profile of the specified user account */
		Map<String, String> userProfileInfoMap = userProfile.getUserProfile(userAccountInfoMap.get("userProfile"));

		// Check if the user profile is suspended. If it is, nullify userAccountInfoMap and return it
		if (userProfileInfoMap.get("isSuspended").equals("1")) {
			userAccountInfoMap = null;
			return userAccountInfoMap;
		}

		return userAccountInfoMap;
	}
}
