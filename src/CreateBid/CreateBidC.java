package CreateBid;

import java.sql.SQLException;

import Entities.Bid;

public class CreateBidC {
    public boolean createBid(String date, String startTime, String endTime, String uid, String cafeRole) {

        try {
            return new Bid().InsertToDB(date, startTime, endTime, uid, cafeRole, "PENDING");
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
