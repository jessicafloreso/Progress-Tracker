package DAO;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import connection.ConnectionManager;

public class CollectedSql implements CollectedInterface {
	private Connection conn;
	private String user;

	@Override
	public void setConnection() throws FileNotFoundException, ClassNotFoundException, IOException, SQLException {
		conn = ConnectionManager.getConnection();
	}

	@Override
	public List<Collected> getAllCollected() {
		List<Collected> pokemon = new ArrayList<Collected>();
		try( PreparedStatement pstmt = conn.prepareStatement("call get_collection(?)")) {
			pstmt.setString(1, user);
			
			ResultSet rs = pstmt.executeQuery(); 
			while(rs.next()) {
				String user = rs.getString("user_name");
				String pokemonName = rs.getString("pokemon_name");
				int level = rs.getInt("level");
				boolean completed = rs.getBoolean("completed");
				
				Collected entry = new Collected(user, pokemonName, level, completed);
				pokemon.add(entry);
			}
			rs.close();
		} catch(SQLException e) {
			System.out.println("sql error");
		}
		return pokemon;
	}


	@Override
	public boolean catchPokemon(String pokemon, int level) {
		if (level > 100 || level < 0) { // TODO: throw custom exception
		}
		
		String stmtStr = "select caught(?, ?, ?)";
		try(PreparedStatement pstmt = conn.prepareStatement(stmtStr)) {
			pstmt.setString(1, user);
			pstmt.setString(2, pokemon);
			pstmt.setInt(3, level);
			int count = pstmt.executeUpdate();
			if (count == 1) {
				return true;
			}
		} catch(SQLException e) {
			System.out.println("sql error");
		} catch(Exception e) { // TODO: change to custom exception
			System.out.println("invalid level");
		}
		return false;
	}

	@Override
	public boolean levelUp(String pokemon, int level) {
		if (level > 100 || level < 0) { // TODO: throw custom exception
		}
		
		String stmtStr = "select levelUp(?, ?, ?)";
		try(PreparedStatement pstmt = conn.prepareStatement(stmtStr)) {
			pstmt.setString(1, user);
			pstmt.setString(2, pokemon);
			pstmt.setInt(3, level);
			int count = pstmt.executeUpdate();
			if (count == 1) {
				return true;
			}
		} catch(SQLException e) {
			System.out.println("sql error");
		} catch(Exception e) { // TODO: change to custom exception
			System.out.println("invalid level");
		}
		return false;
	}

	@Override
	public Optional<Collected> getPokemon(String pokemonNameIn) {
		try(PreparedStatement pstmt = conn.prepareStatement("call get_pokemon(?, ?)"
				+ "")) {
			pstmt.setString(1, user);
			pstmt.setString(1, pokemonNameIn);
			
			ResultSet rs = pstmt.executeQuery();
			
			if( rs.next()) {
				String user = rs.getString("user_name");
				String pokemonName = rs.getString("pokemon_name");
				int level = rs.getInt("level");
				boolean completed = rs.getBoolean("completed");
				
				Collected entry = new Collected(user, pokemonName, level, completed);
				
				Optional<Collected> pokemonFound = Optional.of(entry);
				rs.close();
				return pokemonFound;
			} else {
				rs.close();
				return Optional.empty();
			}
		} catch(SQLException e) {
			System.out.println("sql error");
			return Optional.empty();
		}
	}

}