package DAO;

import java.sql.Connection;
import java.util.Optional;

import connection.ConnectionManager;
import customExceptions.InvalidLoginException;

public class PokemonDb {
	private UsersDaoSql users;
	private CollectedSql collection;
	private Connection conn;

	
	public UsersDaoSql getUsers() {
		return users;
	}
	public CollectedSql getCollection() {
		return collection;
	}

	public PokemonDb() {
		super();
		try {
			this.conn = ConnectionManager.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.users = new UsersDaoSql(conn);
		this.collection = new CollectedSql(conn);
	}
	
<<<<<<< HEAD
	public boolean login(String username, String password) throws InvalidLoginException{
		if (users.login(username, password)) {
//			user = username;
			return true;
		} else {
			
			throw new InvalidLoginException("Invalid Login, please enter a valid username and password");
			//return false;
			
		}
		
	}
=======
>>>>>>> 1935665243f7b9e20544369bb250b86a8f1952cc
}
