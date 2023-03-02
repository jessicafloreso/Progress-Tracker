package DAO;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import connection.ConnectionManager;
import customExceptions.InvalidLoginException;

public class UsersDaoSql implements UsersDao {
	private Connection conn;

	public UsersDaoSql(Connection conn) {
		super();
		this.conn = conn;
	}

	@Override
	public void setConnection() throws FileNotFoundException, ClassNotFoundException, IOException, SQLException {
		// TODO
		
	}

	@Override
	public Optional<Users> getUserById(int id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public boolean login(String username, String password) {
		String stmtStr = "select login(?, ?)";
		try(PreparedStatement pstmt = conn.prepareStatement(stmtStr)) {
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			return rs.getBoolean(1);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
}
