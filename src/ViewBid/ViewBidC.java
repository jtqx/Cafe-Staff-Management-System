package ViewBid;

import java.sql.SQLException;
import java.util.Map;

import Entities.Bid;

public class ViewBidC {
    public Map<String, String> viewBid(String date, String startTime, String endTime, String uid) {
        
        try {
            return new Bid().getBid(date, startTime, endTime, uid, "");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
