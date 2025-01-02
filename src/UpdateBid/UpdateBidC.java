package UpdateBid;

import java.sql.SQLException;

import Entities.Bid;

public class UpdateBidC {
    public boolean updateBid(String date, String startTime, String endTime, String uid, String cafeRole, String status) {

        try {
            return new Bid().updateDB(date, startTime, endTime, uid, cafeRole, status);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateBid(String orgDate, String orgStartTime, String orgEndTime, String uid, String newDate, String newStartTime, String newEndTime) throws SQLException {

        try {
            return new Bid().updateDateTime(orgDate, orgStartTime, orgEndTime, uid, newDate, newStartTime, newEndTime);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean checkIfExist(String date, String startTime, String endTime, String uid) {
        try {
            return new Bid().checkIfExist(date, startTime, endTime, uid);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
