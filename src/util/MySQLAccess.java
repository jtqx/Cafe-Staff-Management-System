package util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLAccess {
    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    public MySQLAccess() throws SQLException, ClassNotFoundException {
        connect = DriverManager.getConnection("jdbc:mysql://localhost/insufferable", "Insufferable", "CSIT314Pass");
    }

    public PreparedStatement prepareStatement(String query) throws SQLException {
        return connect.prepareStatement(query);
    }

    public ResultSet executeQuery(String query) throws SQLException {
        statement = connect.createStatement();
        return statement.executeQuery(query);
    }

    public ResultSet executeQuery(PreparedStatement statement) throws SQLException {
        return statement.executeQuery();
    }

    public boolean executePreparedStatement(PreparedStatement statement) throws SQLException {
        if(statement.executeUpdate() > 0) return true;
        else return false;
    }

    public void close() {
        try {
            if (resultSet != null) resultSet.close();
            if (statement != null) statement.close();
            if (connect != null) connect.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}