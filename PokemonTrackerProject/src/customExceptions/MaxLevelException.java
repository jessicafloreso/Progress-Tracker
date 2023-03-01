package customExceptions;

public class MaxLevelException extends Exception {
 
    private static final long serialVersionUID = 1L;

	public MaxLevelException(String message) {
        super(message);
    }
}