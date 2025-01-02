package Entities;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import util.MySQLAccess;

public class WorkSlot {
    
    public Map<String, String> getWorkSlot(String date, String startTime, String endTime, String status) throws ClassNotFoundException, SQLException {
        MySQLAccess sqlAccess = new MySQLAccess();
        Map<String, String> infoMap = null;

        try {
            // executing the query to get a result set
            String query = "SELECT * FROM workslot WHERE date = ? AND startTime = ? AND endTime = ?";
            // if status string not empty, modify query
            if  (!status.equals("")) query += "AND availabilityStatus = ?";
            PreparedStatement statement = sqlAccess.prepareStatement(query);
            statement.setString(1, date);
            statement.setString(2, startTime);
            statement.setString(3, endTime);
            // add a parameter index for status
            if  (!status.equals("")) statement.setString(4, status);
            ResultSet result = sqlAccess.executeQuery(statement);       // get the results from the query
            // extracting result set information
            while (result.next()) {
                infoMap = new HashMap<>();
                infoMap.put("date", result.getString("date"));
                infoMap.put("startTime", result.getString("startTime"));
                infoMap.put("endTime", result.getString("endTime"));
                infoMap.put("maxCashier", result.getString("maxCashier"));
                infoMap.put("maxChef", result.getString("maxChef"));
                infoMap.put("maxWaiter", result.getString("maxWaiter"));
                infoMap.put("availabilityStatus", result.getString("availabilityStatus"));
            }
            sqlAccess.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return infoMap;
    }

    public Vector<Map<String, String>> getManySlots(String date, String status) throws ClassNotFoundException, SQLException {
        MySQLAccess sqlAccess = new MySQLAccess();
        Vector<Map<String, String>> slotMaps = new Vector<>();

        try {
            // executing the proper query to get a result set
            PreparedStatement statement = null;
            // both parameters are empty
            if(status.equals("") && date.equals("")) {
                sqlAccess.close();
                return slotMaps;
            }
            // date is empty while status is not
            else if(!status.equals("") && date.equals("")) 
            {
                String query = "SELECT * FROM workslot WHERE availabilityStatus = ?";
                statement = sqlAccess.prepareStatement(query);
                statement.setString(1, status);
            }
            // status is empty while date is not
            else if(status.equals("") && !date.equals("")) {

                String query = "SELECT * FROM workslot WHERE date = ?";
                statement = sqlAccess.prepareStatement(query);
                statement.setString(1, date);
            }
            // both not empty
            else {
                String query = "SELECT * FROM workslot WHERE date = ? AND availabilityStatus = ?";
                statement = sqlAccess.prepareStatement(query);
                statement.setString(1, date);
                statement.setString(2, status);
            }

            ResultSet result = sqlAccess.executeQuery(statement);
            // extracting result set information
            while (result.next()) {
                Map<String, String> infoMap = new HashMap<>();
                infoMap.put("date", result.getString("date"));
                infoMap.put("startTime", result.getString("startTime"));
                infoMap.put("endTime", result.getString("endTime"));
                infoMap.put("maxCashier", result.getString("maxCashier"));
                infoMap.put("maxChef", result.getString("maxChef"));
                infoMap.put("maxWaiter", result.getString("maxWaiter"));
                infoMap.put("availabilityStatus", result.getString("availabilityStatus"));
                slotMaps.add(infoMap);
            }
            sqlAccess.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return slotMaps;
    }

    public boolean checkIfExist(String date, String startTime, String endTime) throws ClassNotFoundException {
        // connect to database and query an insert statement
        try {
            MySQLAccess sqlAccess = new MySQLAccess();
            String query = "SELECT * FROM workslot WHERE date = ? AND startTime = ? AND endTime = ?";
            PreparedStatement statement = sqlAccess.prepareStatement(query);
            statement.setString(1, date);
            statement.setString(2, startTime);
            statement.setString(3, endTime);
            ResultSet result = sqlAccess.executeQuery(statement);       // get the results from the query
            boolean exists = result.next();                             // get the status
            sqlAccess.close();          // close the connection
            return exists;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public int getAssignedCashierCount(String date, String startTime, String endTime) throws ClassNotFoundException {
        int amount = 0;
        // connect to database and query an insert statement
        try {
            MySQLAccess sqlAccess = new MySQLAccess();
            String query = "SELECT COUNT(*) as amount FROM workslot w JOIN bid b ON w.date = b.date AND w.startTime = b.startTime AND w.endTime = b.endTime WHERE b.cafeRole = 'Cashier' AND b.status = 'APPROVED' GROUP BY w.date, w.startTime, w.endTime";
            ResultSet result = sqlAccess.executeQuery(query);       // get the results from the query
            if(result.next()) {
                amount = result.getInt("amount");
            }
            sqlAccess.close();          // close the connection
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return amount;
    }

    public int getAssignedChefCount(String date, String startTime, String endTime) throws ClassNotFoundException {
        int amount = 0;
        // connect to database and query an insert statement
        try {
            MySQLAccess sqlAccess = new MySQLAccess();
            String query = "SELECT COUNT(*) as amount FROM workslot w JOIN bid b ON w.date = b.date AND w.startTime = b.startTime AND w.endTime = b.endTime WHERE b.cafeRole = 'Chef' AND b.status = 'APPROVED' GROUP BY w.date, w.startTime, w.endTime";
            ResultSet result = sqlAccess.executeQuery(query);       // get the results from the query
            if(result.next()) {
                amount = result.getInt("amount");
            }
            sqlAccess.close();          // close the connection
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return amount;
    }

    public int getAssignedWaiterCount(String date, String startTime, String endTime) throws ClassNotFoundException {
        int amount = 0;
        // connect to database and query an insert statement
        try {
            MySQLAccess sqlAccess = new MySQLAccess();
            String query = "SELECT COUNT(*) as amount FROM workslot w JOIN bid b ON w.date = b.date AND w.startTime = b.startTime AND w.endTime = b.endTime WHERE b.cafeRole = 'Waiter' AND b.status = 'APPROVED' GROUP BY w.date, w.startTime, w.endTime";
            ResultSet result = sqlAccess.executeQuery(query);       // get the results from the query
            if(result.next()) {
                amount = result.getInt("amount");
            }
            sqlAccess.close();          // close the connection
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return amount;
    }

    public boolean InsertToDB(String date, String startTime, String endTime, String cashierNum, String chefNum, String waiterNum, String status) throws ClassNotFoundException {
        // connect to database and query an insert statement
        try {
            MySQLAccess sqlAccess = new MySQLAccess();
            String query = "INSERT INTO workslot(date, startTime, endTime, maxCashier, maxChef, maxWaiter, availabilityStatus) VALUES(?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = sqlAccess.prepareStatement(query);
            statement.setString(1, date);
            statement.setString(2, startTime);
            statement.setString(3, endTime);
            statement.setString(4, cashierNum);
            statement.setString(5, chefNum);
            statement.setString(6, waiterNum);
            statement.setString(7, status);
            boolean result = sqlAccess.executePreparedStatement(statement);     // get the status of the query
            sqlAccess.close();          // close the connection
            return result;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateDB(String date, String startTime, String endTime, String maxCashier, String maxChef, String maxWaiter, String status) throws ClassNotFoundException {
        // connect to database and query an update statement
        try {
            MySQLAccess sqlAccess = new MySQLAccess();
            String query = "UPDATE workslot SET maxCashier = ?, maxChef = ?, maxWaiter = ?, availabilityStatus = ? WHERE date = ? AND startTime = ? AND endTime = ?";
            PreparedStatement statement = sqlAccess.prepareStatement(query);
            statement.setString(1, maxCashier);
            statement.setString(2, maxChef);
            statement.setString(3, maxWaiter);
            statement.setString(4, status);
            statement.setString(5, date);
            statement.setString(6, startTime);
            statement.setString(7, endTime);
            boolean result = sqlAccess.executePreparedStatement(statement);     // get the status of the query
            sqlAccess.close();          // close the connection
            return result;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteFromDB(String date, String startTime, String endTime) throws ClassNotFoundException {
        // connect to database and query a delete statement
        try {
            MySQLAccess sqlAccess = new MySQLAccess();
            String query = "DELETE FROM workslot WHERE date = ? AND startTime = ? AND endTime = ?";
            PreparedStatement statement = sqlAccess.prepareStatement(query);
            statement.setString(1, date);
            statement.setString(2, startTime);
            statement.setString(3, endTime);
            boolean status = sqlAccess.executePreparedStatement(statement);     // get the status of the query
            sqlAccess.close();          // close the connection
            //TODO: delete bids also
            return status;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
