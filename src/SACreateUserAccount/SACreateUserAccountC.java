package SACreateUserAccount;

// Java imports
import java.sql.SQLException;
import java.util.Vector;

// Class imports
import Entities.UserAccount;
import Entities.UserProfile;

public class SACreateUserAccountC {

	public Vector<String> getUserProfileRoles() {

		UserProfile userProfile = new UserProfile();
		Vector<String> userProfileRoles = new Vector<String>();
		try {
			userProfileRoles = userProfile.getRoles();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return userProfileRoles;
	}

	public boolean createUserAccount(String uid, String name, String password, String userProfileRole, String isSuspended) {

		UserAccount userAcc = new UserAccount();

		boolean result = true;
			
		try {
			result = userAcc.insertToDB(uid, name, password, userProfileRole, isSuspended);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return result;
	}
}
