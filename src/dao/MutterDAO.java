package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import model.Mutter;

public class MutterDAO {
	private final String JDBC_URL = "jdbc:h2:tcp://localhost/~/example";
	private final String DB_USER = "sa";
	private final String DB_PASS = "abcd";

	public List<Mutter> findall(int threadId) {
		List<Mutter> mutterList = new ArrayList<>();
		try(Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			String sql = "SELECT ID, NAME, TEXT, TIME FROM MUTTER"+threadId +" ORDER BY ID DESC";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			ResultSet rs = pStmt.executeQuery();


			while(rs.next()) {
				int id = rs.getInt("ID");
				String userName = rs.getString("NAME");
				String text = rs.getString("TEXT");
				Timestamp dt = rs.getTimestamp("TIME");
				Mutter mutter = new Mutter(id, userName, text, dt);
				mutterList.add(0,mutter);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return mutterList;
	}

	public boolean create(Mutter mutter, int id) {
		try(Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)){
			String sql = "INSERT INTO MUTTER" + id + "(ID, NAME, TEXT, TIME) VALUES (?, ?, ?, ?)";


			PreparedStatement pStmt = conn.prepareStatement(sql);

			pStmt.setInt(1, mutter.getId());
			pStmt.setString(2, mutter.getUserName());
			pStmt.setString(3, mutter.getText());
			pStmt.setTimestamp(4, mutter.getDate());

			int result = pStmt.executeUpdate();

			/*
			 * テスト用
			 */
			//String test = "CREATE TABLE TEST(ID INT);";
			//PreparedStatement pStmt1 = conn.prepareStatement(test);
			//int test_result = pStmt1.executeUpdate();

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
