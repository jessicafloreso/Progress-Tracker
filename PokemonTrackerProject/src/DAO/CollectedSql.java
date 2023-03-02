package DAO;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import connection.ConnectionManager;
import customExceptions.MaxLevelException;
import customExceptions.PokemonNotFoundException;

public class CollectedSql implements CollectedInterface {
	private Connection conn;
	
	

	public CollectedSql(Connection conn) {
		super();
		this.conn = conn;
	}
	



	@Override
	public void setConnection() throws FileNotFoundException, ClassNotFoundException, IOException, SQLException {
		conn = ConnectionManager.getConnection();
	}

	@Override
	public List<Collected> getAllCollected(String user) {
		List<Collected> pokemon = new ArrayList<Collected>();
		try( PreparedStatement pstmt = conn.prepareStatement("call get_collection(?)")) {
			pstmt.setString(1, user);
			
			ResultSet rs = pstmt.executeQuery(); 
			while(rs.next()) {
				String userName = rs.getString("user_name");
				String pokemonName = rs.getString("pokemon_name");
				int level = rs.getInt("level");
				boolean completed = rs.getBoolean("completed");
				
				Collected entry = new Collected(userName, pokemonName, level, completed);
				pokemon.add(entry);
			}
			rs.close();
		} catch(SQLException e) {
			System.out.println("sql error");
		}
		return pokemon;
	}


	@Override
	public boolean catchPokemon(String user, String pokemon, int level) throws MaxLevelException {
		if (level > 100 || level < 0) { // TODO: throw custom exception
			// Custom exception thrown here
			throw new MaxLevelException(
	                "Invalid level reached, the level should only be maximized to 100 and be no less than 1! ");
		}
		
		String stmtStr = "call caught(?, ?, ?)";
		try(PreparedStatement pstmt = conn.prepareStatement(stmtStr)) {
			pstmt.setString(1, user);
			pstmt.setString(2, pokemon);
			pstmt.setInt(3, level);
			int count = pstmt.executeUpdate();
			if (count == 1) {
				return true;
			}
		} catch(SQLException e) {
//			e.printStackTrace(); // debug
			System.out.println("sql error");
		} 
//		catch(Exception e) { // TODO: change to custom exception
//			System.out.println("invalid level");
//		}
		return false;
	}

	@Override
	public boolean levelUp(String user, String pokemon, int level) throws MaxLevelException {
		if (level > 100 || level < 0) { // throw custom exception
			// Custom exception thrown here
			throw new MaxLevelException(
	                "Invalid level reached, the level should only be maximized to 100 and be no less than 1! ");
		}
		String stmtStr = "call levelUp(?, ?, ?)";
		try(PreparedStatement pstmt = conn.prepareStatement(stmtStr)) {
			pstmt.setString(1, user);
			pstmt.setString(2, pokemon);
			pstmt.setInt(3, level);
			int count = pstmt.executeUpdate();
			if (count == 1) {
				return true;
			}
		} catch(SQLException e) {
			e.printStackTrace(); // debug
			System.out.println("sql error");
		}
		return false;
	}

	@Override
	public Optional<Collected> getPokemon(String user, String pokemonNameIn) throws PokemonNotFoundException {
		try(PreparedStatement pstmt = conn.prepareStatement("call get_pokemon(?, ?)"
				+ "")) {
		
			pstmt.setString(1, user);
			pstmt.setString(2, pokemonNameIn);
			
			ResultSet rs = pstmt.executeQuery();
			
			if( rs.next()) {
				String userName = rs.getString("user_name");
				String pokemonName = rs.getString("pokemon_name");
				int level = rs.getInt("level");
				boolean completed = rs.getBoolean("completed");
				
				Collected entry = new Collected(userName, pokemonName, level, completed);
				
				Optional<Collected> pokemonFound = Optional.of(entry);
				rs.close();
				return pokemonFound;
			} else {
				rs.close();
				throw new PokemonNotFoundException("Pokemon not found/caught, type search to try again or catch to add new Pokemon");
				//return Optional.empty();
			}
		} catch(SQLException e) {
			System.out.println("sql error");
			return Optional.empty();
		}
	}

}
