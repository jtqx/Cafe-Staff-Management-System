package UpdateWorkSlot;

import Entities.WorkSlot;

public class UpdateWorkSlotC {
    
    public boolean UpdateWorkSlot(String date, String startTime, String endTime, String maxCashier, String maxChef, String maxWaiter, String status) { 
        // get the proper status string
        String statusStr = (status.equals("Available")) ? "1" : "0";
        // try to update
        try {
            return new WorkSlot().updateDB(date, startTime, endTime, maxCashier, maxChef, maxWaiter, statusStr);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }

    }
}
