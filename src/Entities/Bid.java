package Entities;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import util.MySQLAccess;

public class Bid {
    
    public Map<String, String> getBid(String date, String startTime, String endTime, String uid, String status) throws ClassNotFoundException, SQLException {
        MySQLAccess sqlAccess = new MySQLAccess();
        Map<String, String> infoMap = null;

        try {
            // executing the query to get a result set
            String query = "SELECT * FROM bid WHERE date = ? AND startTime = ? AND endTime = ? AND uid = ?"; 
            // modify the query if the status is empty
            if(!status.equals("")) {
                query += " AND status = ?";
            }
            PreparedStatement statement = sqlAccess.prepareStatement(query);
            statement.setString(1, date);
            statement.setString(2, startTime);
            statement.setString(3, endTime);
            statement.setString(4, uid);
            // modify the query if the status is empty
            if(!status.equals("")) {
                statement.setString(5, status);;
            }
            ResultSet result = sqlAccess.executeQuery(statement);       // get the results from the query
            // extracting result set information
            while (result.next()) {
                infoMap = new HashMap<>();
                infoMap.put("date", result.getString("date"));
                infoMap.put("startTime", result.getString("startTime"));
                infoMap.put("endTime", result.getString("endTime"));
                infoMap.put("uid", result.getString("uid"));
                infoMap.put("cafeRole", result.getString("cafeRole"));
                infoMap.put("status", result.getString("status"));
            }
            sqlAccess.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return infoMap;
    }

    public Vector<Map<String, String>> getManyBids(String date, String startTime, String endTime, String status) throws ClassNotFoundException, SQLException {
        MySQLAccess sqlAccess = new MySQLAccess();
        Vector<Map<String, String>> bidMaps = new Vector<>();

        try {
            // executing the proper query to get a result set
            PreparedStatement statement = null;
            String query = "SELECT * FROM bid WHERE date = ? AND startTime = ? AND endTime = ?";
            // modify query if the status is not empty
            if(!status.equals("")) {
                query += " AND status = ?";
            }
            statement = sqlAccess.prepareStatement(query);
            statement.setString(1, date);
            statement.setString(2, startTime);
            statement.setString(3, endTime);
            // modify query if the status is not empty
            if(!status.equals("")) { 
                statement.setString(4, status);
            }
            ResultSet result = sqlAccess.executeQuery(statement);
            // extracting result set information
            while (result.next()) {
                Map<String, String> infoMap = new HashMap<>();
                infoMap.put("date", result.getString("date"));
                infoMap.put("startTime", result.getString("startTime"));
                infoMap.put("endTime", result.getString("endTime"));
                infoMap.put("uid", result.getString("uid"));
                infoMap.put("cafeRole", result.getString("cafeRole"));
                infoMap.put("status", result.getString("status"));
                bidMaps.add(infoMap);
            }
            sqlAccess.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return bidMaps;
    }

    public Vector<Map<String, String>> getManyBids(String date, String uid, String status) throws ClassNotFoundException, SQLException {
        MySQLAccess sqlAccess = new MySQLAccess();
        Vector<Map<String, String>> bidMaps = new Vector<>();

        try {
            // executing the proper query to get a result set
            PreparedStatement statement = null;
            // find only date
            if(!date.equals("") && uid.equals("") && status.equals("")) {
                String query = "SELECT * FROM bid WHERE date = ?";
                statement = sqlAccess.prepareStatement(query);
                statement.setString(1, date);
            }
            // find only uid
            else if(date.equals("") && !uid.equals("") && status.equals("")) {
                String query = "SELECT * FROM bid WHERE uid = ?";
                statement = sqlAccess.prepareStatement(query);
                statement.setString(1, uid);
            } 
            // find only status
            else if(date.equals("") && uid.equals("") && !status.equals("")) {
                String query = "SELECT * FROM bid WHERE status = ?";
                statement = sqlAccess.prepareStatement(query);
                statement.setString(1, status);
            }
            // find by date and uid
            else if(!date.equals("") && !uid.equals("") && status.equals("")) {
                String query = "SELECT * FROM bid WHERE date = ? AND uid = ?";
                statement = sqlAccess.prepareStatement(query);
                statement.setString(1, date);
                statement.setString(2, uid);
            }
            // find by uid and status
            else if(date.equals("") && !uid.equals("") && !status.equals("")) {
                String query = "SELECT * FROM bid WHERE uid = ? AND status = ?";
                statement = sqlAccess.prepareStatement(query);
                statement.setString(1, uid);
                statement.setString(2, status);
            }
            // find by date and status
            else if(date.equals("") && !uid.equals("") && status.equals("")) {
                String query = "SELECT * FROM bid WHERE date = ? AND status = ?";
                statement = sqlAccess.prepareStatement(query);
                statement.setString(1, date);
                statement.setString(2, status);
            }
            // find by all
            else if(!date.equals("") && !uid.equals("") && !status.equals("")) {
                String query = "SELECT * FROM bid WHERE date = ? AND uid = ? AND status = ?";
                statement = sqlAccess.prepareStatement(query);
                statement.setString(1, date);
                statement.setString(2, uid);
                statement.setString(3, status);
            }
            ResultSet result = sqlAccess.executeQuery(statement);
            // extracting result set information
            while (result.next()) {
                Map<String, String> infoMap = new HashMap<>();
                infoMap.put("date", result.getString("date"));
                infoMap.put("startTime", result.getString("startTime"));
                infoMap.put("endTime", result.getString("endTime"));
                infoMap.put("uid", result.getString("uid"));
                infoMap.put("cafeRole", result.getString("cafeRole"));
                infoMap.put("status", result.getString("status"));
                bidMaps.add(infoMap);
            }
            sqlAccess.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return bidMaps;
    }

    public boolean checkIfExist(String date, String startTime, String endTime, String uid) throws ClassNotFoundException, SQLException {
        MySQLAccess sqlAccess = new MySQLAccess();

        try {
            // executing the query to get a result set
            String query = "SELECT * FROM bid WHERE date = ? AND startTime = ? AND endTime = ? AND uid = ?";
            PreparedStatement statement = sqlAccess.prepareStatement(query);
            statement.setString(1, date);
            statement.setString(2, startTime);
            statement.setString(3, endTime);
            statement.setString(4, uid);
            ResultSet result = sqlAccess.executeQuery(statement);       // get the results from the query
            boolean exists = result.next();
            sqlAccess.close();
            return exists;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean InsertToDB(String date, String startTime, String endTime, String uid, String cafeRole, String status) throws ClassNotFoundException {
        // connect to database and query an insert statement
        try {
            MySQLAccess sqlAccess = new MySQLAccess();
            String query = "INSERT INTO bid(date, startTime, endTime, uid, cafeRole, status) VALUES(?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = sqlAccess.prepareStatement(query);
            statement.setString(1, date);
            statement.setString(2, startTime);
            statement.setString(3, endTime);
            statement.setString(4, uid);
            statement.setString(5, cafeRole);
            statement.setString(6, status);
            boolean result = sqlAccess.executePreparedStatement(statement);     // get the status of the query
            sqlAccess.close();          // close the connection
            return result;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateDB(String date, String startTime, String endTime, String uid, String cafeRole, String status) throws ClassNotFoundException {
        // connect to database and query an update statement
        try {
            MySQLAccess sqlAccess = new MySQLAccess();
            String query = "UPDATE bid SET cafeRole = ?, status = ? WHERE date = ? AND startTime = ? AND endTime = ? AND uid = ?";
            PreparedStatement statement = sqlAccess.prepareStatement(query);
            statement.setString(1, cafeRole);
            statement.setString(2, status);
            statement.setString(3, date);
            statement.setString(4, startTime);
            statement.setString(5, endTime);
            statement.setString(6, uid);
            boolean result = sqlAccess.executePreparedStatement(statement);     // get the status of the query
            sqlAccess.close();          // close the connection
            return result;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateDateTime(String orgDate, String orgStartTime, String orgEndTime, String uid, String newDate, String newStartTime, String newEndTime) throws ClassNotFoundException {
        // connect to database and query an update statement
        try {
            MySQLAccess sqlAccess = new MySQLAccess();
            String query = "UPDATE bid SET date = ?, startTime = ?, endTime = ? WHERE date = ? AND startTime = ? AND endTime = ? AND uid = ?";
            PreparedStatement statement = sqlAccess.prepareStatement(query);
            statement.setString(1, newDate);
            statement.setString(2, newStartTime);
            statement.setString(3, newEndTime);
            statement.setString(4, orgDate);
            statement.setString(5, orgStartTime);
            statement.setString(6, orgEndTime);
            statement.setString(7, uid);
            boolean result = sqlAccess.executePreparedStatement(statement);     // get the status of the query
            sqlAccess.close();          // close the connection
            return result;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // delete all bid that is in a certain timeslot
    public boolean deleteFromDB(String date, String startTime, String endTime) throws ClassNotFoundException {
        // connect to database and query a delete statement
        try {
            MySQLAccess sqlAccess = new MySQLAccess();
            String query = "DELETE FROM bid WHERE date = ? AND startTime = ? AND endTime = ?";
            PreparedStatement statement = sqlAccess.prepareStatement(query);
            statement.setString(1, date);
            statement.setString(2, startTime);
            statement.setString(3, endTime);
            boolean status = sqlAccess.executePreparedStatement(statement);     // get the status of the query
            sqlAccess.close();          // close the connection
            return status;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }    

    // delete a specific bid
    public boolean deleteFromDB(String date, String startTime, String endTime, String uid) throws ClassNotFoundException {
        // connect to database and query a delete statement
        try {
            MySQLAccess sqlAccess = new MySQLAccess();
            String query = "DELETE FROM bid WHERE date = ? AND startTime = ? AND endTime = ? AND uid = ?";
            PreparedStatement statement = sqlAccess.prepareStatement(query);
            statement.setString(1, date);
            statement.setString(2, startTime);
            statement.setString(3, endTime);
            statement.setString(4, uid);
            boolean status = sqlAccess.executePreparedStatement(statement);     // get the status of the query
            sqlAccess.close();          // close the connection
            return status;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // delete all related to a user
    public boolean deleteFromDB(String uid) throws ClassNotFoundException {
        // connect to database and query a delete statement
        try {
            MySQLAccess sqlAccess = new MySQLAccess();
            String query = "DELETE FROM bid WHERE uid = ?";
            PreparedStatement statement = sqlAccess.prepareStatement(query);
            statement.setString(1, uid);
            boolean status = sqlAccess.executePreparedStatement(statement);     // get the status of the query
            sqlAccess.close();          // close the connection
            return status;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
