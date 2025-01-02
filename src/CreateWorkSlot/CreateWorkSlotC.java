package CreateWorkSlot;

import Entities.WorkSlot;

public class CreateWorkSlotC {
    
    public boolean CreateWorkSlot(String date, String startTime, String endTime, String cashierNum, String chefNum, String waiterNum, String status) {
        if (cashierNum.length() > 2 || chefNum.length() > 2 || waiterNum.length() > 2 ||
        cashierNum.equals("0") || chefNum.equals("0") || waiterNum.equals("0")) 
        return false;
        
        WorkSlot slot = new WorkSlot();
        try {
            if(!slot.checkIfExist(date, startTime, endTime)) {
                boolean result = slot.InsertToDB(date, startTime, endTime, cashierNum, chefNum, waiterNum, status);
                return result;
            }
            else return false;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean checkIfExist(String date, String startTime, String endTime) {
        WorkSlot slot = new WorkSlot();
        try {
            boolean result = slot.checkIfExist(date, startTime, endTime); 
            return result;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
}
