package DeleteWorkSlot;

import Entities.Bid;
import Entities.WorkSlot;

public class DeleteWorkSlotC {
    public boolean DeleteWorkSlot(String date, String startTime, String endTime) {

        // try to delete work slot
        try {
            boolean result = new WorkSlot().deleteFromDB(date, startTime, endTime);
            if(result) {
                Bid bid = new Bid();
                bid.deleteFromDB(date, startTime, endTime);
            }
            return result;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
}
