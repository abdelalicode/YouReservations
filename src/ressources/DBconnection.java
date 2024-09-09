package ressources;

import java.sql.*;

public class DBconnection {
    public static void main(String[] args) throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/youreservation";
        String user = "postgres";
        String password = "azerty123";
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(url, user, password);
//            System.out.println("Connected");
        } catch (SQLException e) {
            e.printStackTrace();
        }
//    return connection;
    }
}

