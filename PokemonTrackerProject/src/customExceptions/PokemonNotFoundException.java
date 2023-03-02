package customExceptions;

public class PokemonNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public PokemonNotFoundException(String message) {
        super(message);
    }

}
