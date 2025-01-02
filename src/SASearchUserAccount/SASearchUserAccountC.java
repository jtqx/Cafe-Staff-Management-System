package SASearchUserAccount;

// Java imports
import java.util.Vector;
import java.util.Map;

// Class imports
import Entities.UserAccount;

public class SASearchUserAccountC {

	public Vector<Map<String, String>> searchUserAccount(String name) {
		UserAccount userAccount = new UserAccount();
		Vector<Map<String, String>> accMaps = userAccount.getManyAccounts(name);
		
		return accMaps;
	}
}