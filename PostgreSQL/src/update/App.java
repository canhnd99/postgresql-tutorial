package update;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Scanner;

import select.Actor;

public class App {

	//step1: find the actor who you want to update -> view actor details.
	//step2: create new actor's details.
	//step3: update actor.
	
	public static Connection cnt;
	public static PreparedStatement stm;
	public static ResultSet res;
	
	//step1:
	public static Actor findActorById(Integer id) {
		try {
			String sql = "SELECT * FROM actor WHERE actor_id = ?";
			cnt = DatabaseConnection.getConnection();
			stm = cnt.prepareStatement(sql);
			stm.setInt(1, id);
			res = stm.executeQuery();
			if(res.next()) {
				Integer actor_id = res.getInt("actor_id");
				String fistName = res.getString("first_name");
				String lastName = res.getString("last_name");
				String lastUpdate = res.getTimestamp("last_update").toString();
				Actor actor = new Actor(actor_id, fistName, lastName, lastUpdate);
				return actor;
			}
		} catch (SQLException e) {
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
		return null;
	}
	
	//step2, 3
	public static void updateActor(Integer id, String firstName, String lastName, Timestamp lastUpdate) {
		try {
			StringBuilder sql = new StringBuilder("UPDATE actor ");
			sql.append("SET first_name = ?, last_name = ?, last_update = ? ");
			sql.append("WHERE actor_id = ?");
			cnt = DatabaseConnection.getConnection();
			cnt.setAutoCommit(false);
			stm = cnt.prepareStatement(sql.toString());
			stm.setString(1, firstName);
			stm.setString(2, lastName);
			stm.setTimestamp(3, lastUpdate);
			stm.setInt(4, id);
			stm.executeUpdate();
			cnt.commit();
			System.out.println("update!");
		} catch (SQLException e) {
			try {
				System.out.println(e.getMessage());
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
		Scanner in = new Scanner(System.in);
		System.out.print("Enter Id:");
		Integer id = Integer.parseInt(in.nextLine());
		System.out.println(findActorById(id).toString());
		System.out.print("Enter new firstName: ");
		String fname = in.nextLine();
		System.out.print("Enter new lastName: ");
		String lname = in.nextLine();
		Timestamp ltime = new Timestamp(System.currentTimeMillis());
		updateActor(id, fname, lname, ltime);
	}
}
