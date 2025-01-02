package SearchBid;

import java.sql.SQLException;
import java.util.Map;
import java.util.Vector;

import Entities.Bid;

public class SearchBidC {
    
    public Vector<Map<String, String>> searchBid(String date, String startTime, String endTime, String uid, String status) {
        Vector<Map<String, String>> bidMaps = new Vector<>();
        Bid bid = new Bid();
        try {
            // search specific bid
            if(!date.equals("") && !startTime.equals("") && !endTime.equals("") && !uid.equals("")) {
                Map<String, String> infoMap = bid.getBid(date, startTime, endTime, uid, status);
                if(infoMap != null) bidMaps.add(infoMap);
            }
            // search by date and time
            else if(!date.equals("") && !startTime.equals("") && !endTime.equals("")
            && uid.equals("")) {
                bidMaps.addAll(bid.getManyBids(date, startTime, endTime, status));
            }
            // search by date or uid or status or by all
            else if(startTime.equals("") || endTime.equals("")) {
                bidMaps.addAll(bid.getManyBids(date, uid, status));
            } 
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return bidMaps;
    }
}
