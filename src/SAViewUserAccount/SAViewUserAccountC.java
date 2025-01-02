package SAViewUserAccount;

// Java imports
import java.util.Map;

// Class imports
import Entities.UserAccount;

public class SAViewUserAccountC {

	public Map<String, String> viewUserAccount(String uid) {
		// Instantiate a user account entity object
		UserAccount userAcc = new UserAccount();

		// The getUserAccount() method will return a map of user account information
		Map<String, String> infoMap = userAcc.getUserAccount(uid);
		return infoMap;
	}
}
