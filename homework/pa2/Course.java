
/**
 * The {@code Course} class represents a course offered in an academic institution.
 * Each course has a unique course code, a title, and a number of credit hours.
 *
 * <p>Fields:
 * <ul>
 *   <li>{@code code} - the course code (e.g., "COP3330")</li>
 *   <li>{@code title} - the course title (e.g., "Object-Oriented Programming")</li>
 *   <li>{@code credit} - the number of credit hours the course is worth</li>
 * </ul>
 *
 * <p>This class implements the {@code Searchable} interface, allowing it to be used
 * in search and comparison operations within collections.
 */
public class Course implements Searchable {
    
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
    
    /**
     * Determines if the course matches the given search key.
     * A match occurs if the key is found in either the course code or the title (case-insensitive).
     *
     * @param key the search key to compare against the code and title
     * @return true if the key matches either the code or the title, false otherwise
     */
    @Override
    public boolean isMatch(String key) {
        key = key.toLowerCase();
        return this.getCode().toLowerCase().contains(key) || this.getTitle().toLowerCase().contains(key);
    }
    
    /**
     * Compares this course with another object for equality.
     * Two courses are considered equal if their code, title, and credit hours are all equal.
     *
     * @param obj the object to compare with
     * @return true if the courses are equal, false otherwise
     */
    @Override
    public boolean isEqual(Object obj) {
        Course c = (Course)obj;
        return this.getCode().equals( c.getCode() ) && this.getTitle().equals( c.getTitle() ) && this.getCredit() == c.getCredit();
    }
    
}
