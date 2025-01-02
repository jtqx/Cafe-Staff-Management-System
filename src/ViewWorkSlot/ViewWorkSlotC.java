package ViewWorkSlot;

import java.sql.SQLException;
import java.util.Map;

import Entities.WorkSlot;

public class ViewWorkSlotC {
    public Map<String, String> getWorkSlot(String date, String startTime, String endTime) {
        // try to get the workslot
        try {
            return new WorkSlot().getWorkSlot(date, startTime, endTime, "");
        } 
        catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public int getAssignedCashierCount(String date, String startTime, String endTime) {
        int amount = 0;
        try {
            amount = new WorkSlot().getAssignedCashierCount(date, startTime, endTime);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return amount;
    }

    public int getAssignedChefCount(String date, String startTime, String endTime) {
        int amount = 0;
        try {
            amount = new WorkSlot().getAssignedChefCount(date, startTime, endTime);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return amount;
    }

    public int getAssignedWaiterCount(String date, String startTime, String endTime) {
        int amount = 0;
        try {
            amount = new WorkSlot().getAssignedWaiterCount(date, startTime, endTime);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return amount;
    }
}
