package DAO;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

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
	public boolean login(String username, String password) throws InvalidLoginException {
		String stmtStr = "select login(?, ?)";
		try(PreparedStatement pstmt = conn.prepareStatement(stmtStr)) {
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			
			if( rs.getBoolean(1)) {
				return true;
			} else {
				throw new InvalidLoginException("Invalid login");
			}
		} catch (SQLException e) {
			System.out.println("invalid sql command");
			return false;
		}
	}
	
	@Override
	public boolean addUser(String user, String password) {
		String stmtStr = "call add_new_user(?, ?)";
		try(PreparedStatement pstmt = conn.prepareStatement(stmtStr)) {
			pstmt.setString(1, user);
			pstmt.setString(2, password);
			int count = pstmt.executeUpdate();
			if (count == 1) {
				return true;
			}
		} catch(SQLException e) {
			e.printStackTrace(); // debug
			System.out.println("sql error");
		} catch(Exception e) { // TODO: change to custom exception
			System.out.println("invalid level");
		}
		return false;
	}
	
}
