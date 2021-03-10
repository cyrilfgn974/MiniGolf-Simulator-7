package exceptions;

public class PenaliteException extends Exception {
   	public PenaliteException(String message) {
		super("Un coup de pénalité : " + message);
	}
}