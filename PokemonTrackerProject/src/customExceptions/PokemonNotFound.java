package customExceptions;

public class PokemonNotFound extends Exception {

	private static final long serialVersionUID = 1L;
	
	public PokemonNotFound(String message) {
        super(message);
    }

}
