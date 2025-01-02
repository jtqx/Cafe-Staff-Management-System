package DeleteBid;

import Entities.Bid;

public class DeleteBidC {
    
    public boolean DeleteBid(String date, String startTime, String endTime, String bid) {

        // try to delete work slot
        try {
            return new Bid().deleteFromDB(date, startTime, endTime, bid);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
}
