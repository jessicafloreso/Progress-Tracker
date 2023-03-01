package DAO;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

import connection.ConnectionManager;

public class UsersDaoSql implements UsersDao {

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
	public Optional<Users> login(String username, String password) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}
	
}
