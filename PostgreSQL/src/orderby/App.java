package orderby;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import select.Actor;

public class App {
	
	private static Scanner in = new Scanner(System.in);
	
	//columName: column that you want to use to sort.
	//option: 0: ASC, 1: DESC 
	private static List<Actor> listOfSortedActors(String columnName, int option){
		List<Actor> list = new ArrayList<>();
		Connection cnt = null;
		PreparedStatement stm = null;
		ResultSet res = null;
		try {
			String ascSql = "SELECT * FROM actor ORDER BY " + columnName + " ASC";
			String descSql = "SELECT * FROM actor ORDER BY " + columnName + " DESC";
			cnt = DatabaseConnection.getConnection();
			if(option == 0) {
				stm = cnt.prepareStatement(ascSql);
			}else {
				stm = cnt.prepareStatement(descSql);
			}
			res = stm.executeQuery();
			while(res.next()) {
				Integer id = res.getInt("actor_id");
				String fistName = res.getString("first_name");
				String lastName = res.getString("last_name");
				String lastUpdate = res.getTimestamp("last_update").toString();
				Actor actor = new Actor(id, fistName, lastName, lastUpdate);
				list.add(actor);
			}
			return list;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
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
		List<Actor> listOfActors = new ArrayList<>();
		System.out.print("Enter columnName: ");
		String col = in.nextLine();
		System.out.print("Enter option: ");
		Integer opt = in.nextInt();
		listOfActors = listOfSortedActors(col, opt);
		for (Actor actor : listOfActors) {
			System.out.println(actor.toString());
		}
	}
}