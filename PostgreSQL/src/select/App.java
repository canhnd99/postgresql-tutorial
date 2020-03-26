package select;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class App {
	
	private static List<Actor> listOfActors;
	
	private static void getAllFirstname() {
		Connection cnt = null;
		PreparedStatement stm = null;
		ResultSet res = null;
		
		try {
			String sql = "SELECT * FROM actor";
			cnt = DatabaseConnection.getConnection();
			stm = cnt.prepareStatement(sql);
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
			return;
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
		listOfActors = new ArrayList<>();
		getAllFirstname();
		for (Actor actor: listOfActors) {
			System.out.println(actor.toString());
		}
	}
}
