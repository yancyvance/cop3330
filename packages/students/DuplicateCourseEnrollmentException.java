package cop3330.exceptions;

/**
 * Exception thrown when a student attempts to enroll in a course
 * that they are already registered for.
 */
public class DuplicateCourseEnrollmentException extends Exception {

    /**
     * Constructs a new {@code DuplicateCourseEnrollmentException} with the specified detail message.
     *
     * @param message the detail message explaining the reason for the exception
     */
    public DuplicateCourseEnrollmentException(String message) {
        super(message);
    }

}
