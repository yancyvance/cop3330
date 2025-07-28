package cop3330.exceptions;

/**
 * Exception thrown when attempting to instantiate a subclass of the {@code Student} class
 * using an invalid or unrecognized student status argument.
 * <p>
 * This typically occurs when the provided status does not match any of the supported types,
 * such as {@code UndergraduateStudent} or {@code GraduateStudent}.
 */
public class InvalidStudentStatusException extends Exception {

    /**
     * Constructs a new {@code InvalidStudentStatusException} with the specified detail message.
     *
     * @param message the detail message explaining the reason for the exception
     */
    public InvalidStudentStatusException(String message) {
        super(message);
    }

}
