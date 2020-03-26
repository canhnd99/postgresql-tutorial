package delete;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class App {
	
	private static Scanner in = new Scanner(System.in);
	private static Connection cnt;
	private static PreparedStatement stm;
	
	private static void deleteActor(Integer id) {
		try {
			String sql = "DELETE FROM student WHERE id = ?";
			cnt = DatabaseConnection.getConnection();
			cnt.setAutoCommit(false);
			stm = cnt.prepareStatement(sql);
			stm.setInt(1, id);
			stm.executeUpdate();
			cnt.commit();
			System.out.println("deleted!");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			try {
				cnt.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				if(cnt != null) {
					cnt.close();
				}
				if(stm != null){
					stm.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		System.out.println("enter id: ");
		Integer id = in.nextInt();
		deleteActor(id);
	}
}
