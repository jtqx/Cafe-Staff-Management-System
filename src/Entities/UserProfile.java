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

public class UserProfile {

    public Map<String, String> getUserProfile(String role) {
        Map<String, String> infoMap = null;
        try {
            MySQLAccess sqlAccess = new MySQLAccess();
            String query = "SELECT * FROM userprofile WHERE role = ?";
            PreparedStatement statement = sqlAccess.prepareStatement(query);
            statement.setString(1, role);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                infoMap = new HashMap<>();
                infoMap.put("role", resultSet.getString("role"));
                infoMap.put("description", resultSet.getString("description"));
                infoMap.put("isSuspended", resultSet.getString("isSuspended"));
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

    public Vector<String> getRoles() throws ClassNotFoundException, SQLException {
        Vector<String> roles = new Vector<String>();
        // connect to the database and query for data
        try {
            MySQLAccess sqlAccess = new MySQLAccess();
            String query = "SELECT role FROM userprofile";
            PreparedStatement statement = sqlAccess.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String role = resultSet.getString("role");
                roles.add(role);
            }
            sqlAccess.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return roles;
    }

    public boolean insertToDB(String role, String description, String isSuspended) throws ClassNotFoundException {
        // connect to database and query an insert statement
        try {
            MySQLAccess sqlAccess = new MySQLAccess();
            String query = "INSERT INTO userprofile VALUES(?, ?, ?)";
            PreparedStatement statement = sqlAccess.prepareStatement(query);
            statement.setString(1, role);
            statement.setString(2, description);
            statement.setString(3, isSuspended);
            boolean status = sqlAccess.executePreparedStatement(statement);     // get the status of the query
            sqlAccess.close();          // close the connection
            return status;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateDB(String role, String updatedDesc) throws ClassNotFoundException {
        // connect to database and query an update statement
        try {
            MySQLAccess sqlAccess = new MySQLAccess();
            String query = "UPDATE userprofile SET description = ? WHERE role = ?";
            PreparedStatement statement = sqlAccess.prepareStatement(query);
            statement.setString(1, updatedDesc);
            statement.setString(2, role);
            boolean status = sqlAccess.executePreparedStatement(statement);     // get the status of the query
            sqlAccess.close();          // close the connection
            return status;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean suspendUserProfile(String role) throws ClassNotFoundException {
        // connect to database and query an update statement
        try {
            MySQLAccess sqlAccess = new MySQLAccess();
            String query = "UPDATE userprofile SET isSuspended = ? WHERE role = ?";
            PreparedStatement statement = sqlAccess.prepareStatement(query);
            statement.setString(1, "1");
            statement.setString(2, role);
            boolean status = sqlAccess.executePreparedStatement(statement);     // get the status of the query
            sqlAccess.close();          // close the connection
            return status;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean reinstateUserProfile(String role) throws ClassNotFoundException {
        // connect to database and query an update statement
        try {
            MySQLAccess sqlAccess = new MySQLAccess();
            String query = "UPDATE userprofile SET isSuspended = ? WHERE role = ?";
            PreparedStatement statement = sqlAccess.prepareStatement(query);
            statement.setString(1, "0");
            statement.setString(2, role);
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
