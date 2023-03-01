package DAO;

import java.sql.Connection;

import connection.ConnectionManager;

public class PokemonDb {
	private UsersDaoSql users;
	private CollectedSql collection;
	private Connection conn;
	private String user;
	

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
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
		System.out.println(this.user);
	}
	
	public void login(String username, String password) {
		if (users.login(username, password)) {
			user = username;
		} else {
			// throw invalid login custom exception
		}
		
	}
	
	
}
