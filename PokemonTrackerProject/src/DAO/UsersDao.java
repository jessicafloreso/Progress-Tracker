package DAO;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

public interface UsersDao {
	public void setConnection() throws FileNotFoundException, ClassNotFoundException, IOException, SQLException;
	
	public Optional<Users> getUserById(int id);
	public Optional<Users> login(String username, String password);
	
}
