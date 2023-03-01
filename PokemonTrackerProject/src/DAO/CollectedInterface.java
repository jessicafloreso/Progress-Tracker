package DAO;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


public interface CollectedInterface {
	public void setConnection()  throws FileNotFoundException, ClassNotFoundException, IOException, SQLException;
	public List<Collected> getAllCollected();
	public Optional<Collected> getPokemon(String pokemonName);
	public boolean catchPokemon(String pokemon, int level);
	public boolean levelUp( String pokemon, int level );
	
}
