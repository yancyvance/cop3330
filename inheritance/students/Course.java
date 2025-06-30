/**
 * The {@code Course} class represents a course offered in an academic institution.
 * Each course has a unique course code, a title, and a number of credit hours.
 * 
 * <p>Fields:
 * <ul>
 *   <li>code - the course code (e.g., "COP3330")</li>
 *   <li>title - the course title (e.g., "Object-Oriented Programming")</li>
 *   <li>credit - the number of credit hours the course is worth</li>
 * </ul>
 */
public class Course {
    
    /** The unique course code. */
    private String code;
    
    /** The title of the course. */
    private String title;
    
    /** The number of credit hours for the course. */
    private int credit;
    
    
    /**
     * Constructs a new Course object with the specified course code, title, and credit hours.
     *
     * @param code the course code
     * @param title the title of the course
     * @param credit the number of credit hours
     */
    public Course(String code, String title, int credit) {
        this.code = code;
        this.title = title;
        this.credit = credit;
    }
    
    /**
     * Copy constructor. Constructs a new Course object by copying another Course object.
     *
     * @param ob the Course object to copy
     */
    public Course(Course ob) {
        this(ob.code, ob.title, ob.credit);
    }
    
    /**
     * Returns a string representation of the Course object.
     *
     * @return a formatted string showing credit hours, course code, and title
     */
    public String toString() {
        return String.format("%d - %s: %s", this.credit, this.code, this.title);
    }
    
    /**
     * Sets the course code.
     *
     * @param code the new course code
     */
    public void setCode(String code) {
        this.code = code;
    }
    
    /**
     * Returns the course code.
     *
     * @return the course code
     */
    public String getCode() {
        return this.code;
    }
    
    /**
     * Sets the title of the course.
     *
     * @param title the new title
     */
    public void setTitle(String title) {
        this.title = title;
    }
    
     /**
     * Returns the title of the course.
     *
     * @return the course title
     */
    public String getTitle() {
        return this.title;
    }
    
    /**
     * Sets the number of credit hours for the course.
     *
     * @param credit the new number of credit hours
     */
    public void setCredit(int credit) {
        this.credit = credit;
    }
    
    /**
     * Returns the number of credit hours for the course.
     *
     * @return the credit hours
     */
    public int getCredit() {
        return this.credit;
    }
    
}
