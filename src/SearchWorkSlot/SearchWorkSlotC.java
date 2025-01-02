package SearchWorkSlot;

import java.sql.SQLException;
import java.util.Map;
import java.util.Vector;

import Entities.WorkSlot;

public class SearchWorkSlotC {
    
    public Vector<Map<String, String>> searchWorkSlot(String date, String startTime, String endTime, String status) {
        Vector<Map<String, String>> slotMaps = new Vector<>();
        WorkSlot slot = new WorkSlot();
        try {
            if(startTime.equals("")||endTime.equals("")) {
                // convert status text to boolean form
                if(status.equals("Available")) status = "1";
                else if(status.equals("Closed")) status = "0";
                // add the resutls to slotMaps
                slotMaps.addAll(slot.getManySlots(date, status));
            }
            else {
                Map<String, String> infoMap = slot.getWorkSlot(date, startTime, endTime, status);
                if(infoMap != null) slotMaps.add(infoMap);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return slotMaps;
    }
}
