package cop3330.exceptions;

/**
 * Exception thrown when a student attempts to register for courses 
 * that would result in exceeding their allowed credit hour limit.
 */
public class CreditHourLimitExceededException extends Exception {

    /**
     * Constructs a new {@code CreditHourLimitExceededException} with the specified detail message.
     *
     * @param message the detail message explaining the reason for the exception
     */
    public CreditHourLimitExceededException(String message) {
        super(message);
    }

}
