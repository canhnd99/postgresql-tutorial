package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    public static String url = "jdbc:postgresql://localhost:5432/smsdb";
    public static String user = "postgres";
    public static String password = "1234";

    public static Connection getConnection() {
        Connection cnt = null;
        try {
            cnt = DriverManager.getConnection(url, user, password);
            return cnt;
        } catch (SQLException e) {
            return null;
        }
    }
}
