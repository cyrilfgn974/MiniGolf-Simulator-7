package exceptions;

public class InWaterException extends PenaliteException {
    public InWaterException() {
        super("Balle dans l'eau");
    }
}