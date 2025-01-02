package CSUpdateUserAccount;

// Class imports
import Entities.UserAccount;

public class CSUpdateUserAccountC {

	public boolean indicateCafeRoleAndMaxWS(String uid, String cafeRole, String maxWorkSlots) {

		// Insantiate a user account entity
		UserAccount userAccount = new UserAccount();

		boolean result = true;
			
		/* Call the updateDB() method in the entity and pass in uid and cafeRole,
		which returns a boolean value */
		try {
			result = userAccount.indicateCafeRoleAndMaxWS(uid, cafeRole, maxWorkSlots);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return result;
	}
}