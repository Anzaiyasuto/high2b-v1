package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Mutter;

public class MutterDAO {
	private final String JDBC_URL = "jdbc:h2:tcp://localhost/~/example";
	private final String DB_USER = "sa";
	private final String DB_PASS = "abcd";

	public List<Mutter> findall() {
		List<Mutter> mutterList = new ArrayList<>();
		try(Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			String sql = "SELECT ID, NAME, TEXT, TIME FROM MUTTER2 ORDER BY ID DESC";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			ResultSet rs = pStmt.executeQuery();


			while(rs.next()) {
				int id = rs.getInt("ID");
				String userName = rs.getString("NAME");
				String text = rs.getString("TEXT");
				String time = rs.getString("TIME");
				Date dt = rs.getDate("TIME");
				Mutter mutter = new Mutter(id, userName, text, dt);
				mutterList.add(mutter);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return mutterList;
	}

	public boolean create(Mutter mutter) {
		try(Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)){
			String sql = "INSERT INTO MUTTER3(NAME, TEXT, TIME) VALUES (?, ?, ?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			pStmt.setString(1, mutter.getUserName());
			pStmt.setString(2, mutter.getText());
			pStmt.setDate(3, (java.sql.Date) mutter.getDate());

			int result = pStmt.executeUpdate();
			if(result != 1) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
