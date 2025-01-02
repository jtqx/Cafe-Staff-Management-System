package SAUpdateUserAccount;

// Class imports
import Entities.UserAccount;

public class SAUpdateUserAccountC {

	public boolean updateUserAccount(String uid, String updatedName, String updatedPassword) {

		boolean result = false;

		UserAccount userAcc = new UserAccount();

		try {
			result = userAcc.updateDB(uid, updatedName, updatedPassword);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
			
		return result;
	}
}
