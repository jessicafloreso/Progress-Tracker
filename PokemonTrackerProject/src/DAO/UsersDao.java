package DAO;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

import customExceptions.InvalidLoginException;

public interface UsersDao {
	public void setConnection() throws FileNotFoundException, ClassNotFoundException, IOException, SQLException;
	
	public Optional<Users> getUserById(int id);
	public boolean login(String username, String password) throws InvalidLoginException;
  public boolean addUser(String user, String password);	
}
