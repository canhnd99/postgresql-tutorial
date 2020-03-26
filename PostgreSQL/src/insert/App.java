package insert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class App {
	
	private static Scanner in = new Scanner(System.in);
	
	public static void insertNewActor(String firstName, String lastName, Timestamp lastUpdate) {
		Connection cnt = null;
		PreparedStatement stm = null;
		ResultSet res = null;
		try {
			StringBuilder sql = new StringBuilder("INSERT INTO actor ");
			sql.append("(first_name, last_name, last_update) ");
			sql.append("VALUES (?, ?, ?)");
			cnt = DatabaseConnection.getConnection();
			stm = cnt.prepareStatement(sql.toString());
			stm.setString(1, firstName);
			stm.setString(2, lastName);
			stm.setTimestamp(3, lastUpdate);
			stm.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if(cnt != null) {
					cnt.close();
				}
				if(stm != null){
					stm.close();
				}
				if(res != null) {
					res.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		System.out.print("Enter FirstName:");
		String firstName = in.nextLine();
		System.out.print("Enter LastName:");
		String lastName = in.nextLine();
		Timestamp lastUpdate = new Timestamp(System.currentTimeMillis());
		insertNewActor(firstName, lastName, lastUpdate);
	}
}
