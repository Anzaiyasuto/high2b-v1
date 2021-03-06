package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import model.dThread;

public class dThreadDAO {
	private final String JDBC_URL = "jdbc:h2:tcp://localhost/~/example";
	private final String DB_USER = "sa";
	private final String DB_PASS = "abcd";

	public List<dThread> findall() {
		List<dThread> threadList = new ArrayList<>();
		try(Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			String sql = "SELECT ID, DTITLE, DTIME FROM DTHREAD ORDER BY ID DESC";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			ResultSet rs = pStmt.executeQuery();


			while(rs.next()) {
				int id = rs.getInt("ID");
				String dTitle = rs.getString("DTITLE");
				Timestamp dt = rs.getTimestamp("DTIME");
				dThread thread = new dThread(id, dTitle, dt);
				threadList.add(0,thread);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return threadList;
	}

	public boolean create(dThread thread) {
		try(Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)){
			int threadId=thread.getId();

			String sql = "INSERT INTO DTHREAD(ID, DTITLE, DTIME) VALUES (?, ?, ?)";
			String createsql = "CREATE TABLE MUTTER"+ threadId + "( ID INT PRIMARY KEY NOT NULL, NAME VARCHAR(255) NOT NULL, TEXT VARCHAR(255) NOT NULL, TIME TIMESTAMP NOT NULL);";


			PreparedStatement pStmt = conn.prepareStatement(sql);
			PreparedStatement createStmt = conn.prepareStatement(createsql);
			pStmt.setInt(1, thread.getId());
			pStmt.setString(2, thread.getTitle());
			pStmt.setTimestamp(3, thread.getData());

			int result = pStmt.executeUpdate();
			createStmt.executeUpdate();


			if(result != 1) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public dThread findTitle(int threadId) {
		// TODO 自動生成されたメソッド・スタブ

		dThread thread = null;
		try(Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			String sql = "SELECT * FROM DTHREAD WHERE ID = " + threadId + " ";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			ResultSet rs = pStmt.executeQuery();

			while(rs.next()) {
				int id = rs.getInt("ID");
				String dTitle = rs.getString("DTITLE");
				Timestamp dt = rs.getTimestamp("DTIME");
				thread = new dThread(id, dTitle, dt);
			}
			return thread;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return thread;

	}
}
