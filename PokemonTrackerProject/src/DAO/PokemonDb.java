package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
	
	public List<Pokemon> getAllPokemon() {
		List<Pokemon> pokemon = new ArrayList<Pokemon>();
		try( PreparedStatement pstmt = conn.prepareStatement("call get_all_pokemon()")) {			
			ResultSet rs = pstmt.executeQuery(); 
			while(rs.next()) {
				int id = rs.getInt("id");
				String pokemonName = rs.getString("name");
				
				Pokemon entry = new Pokemon(id, pokemonName);
				pokemon.add(entry);
			}
			rs.close();
		} catch(SQLException e) {
			System.out.println("sql error");
		}
		return pokemon;
	} 
	
	public List<Pokemon> gottaCatchem(String user) {
		List<Pokemon> pokemon = new ArrayList<Pokemon>();
		try( PreparedStatement pstmt = conn.prepareStatement("call gotta_catchem(?)")) {
			pstmt.setString(1, user);
			ResultSet rs = pstmt.executeQuery(); 
			while(rs.next()) {
				int id = rs.getInt("id");
				String pokemonName = rs.getString("name");
				
				Pokemon entry = new Pokemon(id, pokemonName);
				pokemon.add(entry);
			}
			rs.close();
		} catch(SQLException e) {
			System.out.println("sql error");
		}
		return pokemon;
	} 
}
