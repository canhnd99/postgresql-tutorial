package select_equals_like;

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
	private static List<Actor> listOfActors;
	
	public static void findActorBySelect(String value) {
		Connection cnt = null;
		PreparedStatement stm = null;
		ResultSet res = null;
		try {
			StringBuilder sql_like = new StringBuilder("SELECT * FROM actor ");
			sql_like.append("WHERE first_name LIKE ?");
			cnt = DatabaseConnection.getConnection();
			stm = cnt.prepareStatement(sql_like.toString());
			stm.setString(1, value);
			res = stm.executeQuery();
			while(res.next()) {
				Integer id = res.getInt("actor_id");
				String fistName = res.getString("first_name");
				String lastName = res.getString("last_name");
				String lastUpdate = res.getTimestamp("last_update").toString();
				Actor actor = new Actor(id, fistName, lastName, lastUpdate);
				listOfActors.add(actor);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void main(String[] args) {
		listOfActors = new ArrayList<>();
		System.out.print("Enter search value: ");
		String value = in.nextLine();
		findActorBySelect(value+"%");
		for (Actor actor : listOfActors) {
			System.out.println(actor.toString());
		}
	}
}
