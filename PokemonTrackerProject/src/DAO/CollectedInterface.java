package DAO;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import customExceptions.MaxLevelException;
import customExceptions.PokemonNotFoundException;


public interface CollectedInterface {
	public void setConnection()  throws FileNotFoundException, ClassNotFoundException, IOException, SQLException;
	public List<Collected> getAllCollected(String user);
	public Optional<Collected> getPokemon(String user, String pokemonName) throws PokemonNotFoundException;
	public boolean catchPokemon(String user, String pokemon, int level) throws MaxLevelException;
	public boolean levelUp(String user, String pokemon, int level ) throws MaxLevelException;
	
}
