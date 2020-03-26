package update;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
	public static String url = "jdbc:postgresql://localhost:5432/dvdrental";
	public static String user = "postgres";
	public static String password = "1234";
	
	public static Connection getConnection() {
		Connection cnt = null;
		try {
			cnt = DriverManager.getConnection(url, user, password);
			return cnt;
		} catch (Exception e) {
			return null;
		}
	}
}
