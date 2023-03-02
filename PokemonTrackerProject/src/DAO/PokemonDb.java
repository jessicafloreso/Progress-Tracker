package DAO;

import java.sql.Connection;
import connection.ConnectionManager;

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
}
