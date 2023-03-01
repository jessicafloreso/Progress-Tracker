package DAO;

import java.sql.Connection;
import java.util.Optional;

import connection.ConnectionManager;

public class PokemonDb {
	private UsersDaoSql users;
	private CollectedSql collection;
	private Connection conn;
//	private String user;
//	
//
//	public String getUser() {
//		return user;
//	}
//	public void setUser(String user) {
//		this.user = user;
//	}
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
	
	public boolean login(String username, String password) {
		if (users.login(username, password)) {
//			user = username;
			return true;
		} else {
			return false;
			// throw invalid login custom exception
		}
		
	}
}
