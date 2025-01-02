package Entities;

// Java imports
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

// Class imports
import util.MySQLAccess;

public class UserAccount {

    public Map<String, String> getUserAccount(String uid, String password) {
        Map<String, String> infoMap = null;
        try {
            MySQLAccess sqlAccess = new MySQLAccess();
            String query = "SELECT * FROM useraccount WHERE uid = ? AND password = ?";
            PreparedStatement statement = sqlAccess.prepareStatement(query);
            statement.setString(1, uid);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                infoMap = new HashMap<>();
                infoMap.put("uid", resultSet.getString("uid"));
                infoMap.put("password", resultSet.getString("password"));
                infoMap.put("name", resultSet.getString("name"));
                infoMap.put("userProfile", resultSet.getString("userProfile"));
                infoMap.put("isSuspended", resultSet.getString("isSuspended"));
                infoMap.put("cafeRole", resultSet.getString("cafeRole"));
                infoMap.put("maxWorkSlots", resultSet.getString("maxWorkHours"));
                infoMap.put("lastLogIn", resultSet.getString("lastLogIn"));
            }
            sqlAccess.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return infoMap;
    }

    public Map<String, String> getUserAccount(String uid) {
        Map<String, String> infoMap = null;
        try {
            MySQLAccess sqlAccess = new MySQLAccess();
            String query = "SELECT * FROM useraccount WHERE uid = ?";
            PreparedStatement statement = sqlAccess.prepareStatement(query);
            statement.setString(1, uid);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                infoMap = new HashMap<>();
                infoMap.put("uid", resultSet.getString("uid"));
                infoMap.put("password", resultSet.getString("password"));
                infoMap.put("name", resultSet.getString("name"));
                infoMap.put("userProfile", resultSet.getString("userProfile"));
                infoMap.put("isSuspended", resultSet.getString("isSuspended"));
                infoMap.put("cafeRole", resultSet.getString("cafeRole"));
                infoMap.put("maxWorkSlots", resultSet.getString("maxWorkHours"));
                infoMap.put("lastLogIn", resultSet.getString("lastLogIn"));
            }
            sqlAccess.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return infoMap;
    }

    public Vector<Map<String, String>> getManyAccounts(String name) {
        Vector<Map<String, String>> accMaps = new Vector<>();
        try {
            MySQLAccess sqlAccess = new MySQLAccess();
            String query = "SELECT * FROM useraccount WHERE name = ?";
            PreparedStatement statement = sqlAccess.prepareStatement(query);
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Map<String, String> infoMap = new HashMap<>();
                infoMap.put("uid", resultSet.getString("uid"));
                infoMap.put("password", resultSet.getString("password"));
                infoMap.put("name", resultSet.getString("name"));
                infoMap.put("userProfile", resultSet.getString("userProfile"));
                infoMap.put("isSuspended", resultSet.getString("isSuspended"));
                infoMap.put("cafeRole", resultSet.getString("cafeRole"));
                infoMap.put("maxWorkSlots", resultSet.getString("maxWorkHours"));
                infoMap.put("lastLogIn", resultSet.getString("lastLogIn"));
                accMaps.add(infoMap);
            }
            sqlAccess.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return accMaps;
    }

    public boolean insertToDB(String uid, String name, String password, String userProfile, String isSuspended) throws ClassNotFoundException {
        // connect to database and query an insert statement
        try {
            MySQLAccess sqlAccess = new MySQLAccess();
            String query = "INSERT INTO useraccount VALUES (?, ?, ?, ?, ?, NULL, NULL, NULL)";
            PreparedStatement statement = sqlAccess.prepareStatement(query);
            statement.setString(1, uid);
            statement.setString(2, name);
            statement.setString(3, password);
            statement.setString(4, userProfile);
            statement.setString(5, isSuspended);
            boolean status = sqlAccess.executePreparedStatement(statement);     // get the status of the query
            sqlAccess.close();          // close the connection
            return status;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateDB(String uid, String updatedName, String updatedPassword) throws ClassNotFoundException {
        // connect to database and query an update statement
        try {
            MySQLAccess sqlAccess = new MySQLAccess();
            String query = "UPDATE useraccount SET name = ?, password = ? WHERE uid = ?";
            PreparedStatement statement = sqlAccess.prepareStatement(query);
            statement.setString(1, updatedName);
            statement.setString(2, updatedPassword);
            statement.setString(3, uid);
            boolean status = sqlAccess.executePreparedStatement(statement);     // get the status of the query
            sqlAccess.close();          // close the connection
            return status;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // For cafe staff to indicate their cafe roles
    public boolean indicateCafeRoleAndMaxWS(String uid, String cafeRole, String maxWorkSlots) throws ClassNotFoundException {
        // connect to database and query an update statement
        try {
            MySQLAccess sqlAccess = new MySQLAccess();
            String query = "UPDATE useraccount SET cafeRole = ?, maxWorkHours = ? WHERE uid = ?";
            PreparedStatement statement = sqlAccess.prepareStatement(query);
            statement.setString(1, cafeRole);
            statement.setString(2, maxWorkSlots);
            statement.setString(3, uid);
            boolean status = sqlAccess.executePreparedStatement(statement);     // get the status of the query
            sqlAccess.close();          // close the connection
            return status;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteFromDB(String uid) throws ClassNotFoundException {
        // connect to database and query a delete statement
        try {
            MySQLAccess sqlAccess = new MySQLAccess();
            String query = "DELETE FROM useraccount WHERE uid = ?";
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

    public boolean suspendUserAccount(String uid) throws ClassNotFoundException {
        // connect to database and query an update statement
        try {
            MySQLAccess sqlAccess = new MySQLAccess();
            String query = "UPDATE useraccount SET isSuspended = ? WHERE uid = ?";
            PreparedStatement statement = sqlAccess.prepareStatement(query);
            statement.setString(1, "1");
            statement.setString(2, uid);
            boolean status = sqlAccess.executePreparedStatement(statement);     // get the status of the query
            sqlAccess.close();          // close the connection
            return status;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean reinstateUserAccount(String uid) throws ClassNotFoundException {
        // connect to database and query an update statement
        try {
            MySQLAccess sqlAccess = new MySQLAccess();
            String query = "UPDATE useraccount SET isSuspended = ? WHERE uid = ?";
            PreparedStatement statement = sqlAccess.prepareStatement(query);
            statement.setString(1, "0");
            statement.setString(2, uid);
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
