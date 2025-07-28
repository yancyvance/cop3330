package cop3330.models;

import java.util.ArrayList;
import cop3330.util.GenericList;
import cop3330.util.Searchable;
import cop3330.exceptions.DuplicateCourseEnrollmentException;
import cop3330.exceptions.CreditHourLimitExceededException;
import cop3330.exceptions.InvalidStudentStatusException;

/**
 * Represents an abstract student with a unique ID, name, and a list of enrolled courses.
 * <p>
 * This class serves as a base for specific student types such as {@code UndergraduateStudent} 
 * and {@code GraduateStudent}. It implements the {@link Searchable} interface to support 
 * keyword-based searching and equality comparisons based on student attributes.
 * </p>
 * <p>
 * The class also implements {@link Comparable} to allow ordering of students primarily by their
 * status (e.g., "Graduate", "Undergraduate") and secondarily by their names.
 * </p>
 */
public abstract class Student implements Comparable, Searchable<Student> {
    
    /** The unique identifier for the student. */
    private String studentID;
    
    /** The name of the student. */
    protected String name;
    
    /** A list of courses the student is enrolled in. */
    private ArrayList<Course> courses;
    
    
    /**
     * Constructs a {@code Student} with the default name "unknown" and an empty course list.
     */
    public Student() {
        this("unknown");
    }
    
     /**
     * Constructs a {@code Student} with the specified name and an empty course list.
     * 
     * @param name the student's name
     */
    public Student(String name) {
        this(null, name);
    }
    
    /**
     * Constructs a {@code Student} with the specified ID and name, and initializes an empty course list.
     * 
     * @param studentID the student's unique identifier
     * @param name the student's name
     */
    public Student(String studentID, String name) {
        this.studentID = studentID;
        this.name = name;
        this.courses = new ArrayList<>();
    }
    
    /**
     * Copy constructor that creates a deep copy of the specified {@code Student}, 
     * including a duplicate of each enrolled course.
     * 
     * @param ob the {@code Student} object to copy
     */
    public Student(Student ob) {
        this(ob.name);
        
        for(Course tmp : ob.courses) {
            this.courses.add( new Course(tmp) );
        }
    }
    
    /**
     * Returns a string representation of the student, including their status and name.
     * 
     * @return a string in the format "{status}: {name}"
     */
    @Override
    public String toString() {
        return this.getStatus() + ": " + this.name;
    }
    
    /**
     * Adds a course to the student's list of enrolled courses.
     * <p>
     * Throws exceptions if the course is already enrolled or if adding it exceeds the
     * student's allowed credit hour limit (6 for graduate students, 9 for undergraduates).
     * </p>
     * 
     * @param c the course to add
     * @throws DuplicateCourseEnrollmentException if the course is already enrolled
     * @throws CreditHourLimitExceededException if adding the course exceeds credit limits
     */
    public void addCourse(Course c) throws DuplicateCourseEnrollmentException, CreditHourLimitExceededException {
        // check first if this was already added
        if( this.getCourseList().contains( c ) )
            throw new DuplicateCourseEnrollmentException(String.format("Course %s is already registered.", c.getCode()));
            
        // check if within limits
        if( this.getStatus().equals("Graduate") ) {
            if( this.getTotalCredit() + c.getCredit() > 6 )
                throw new CreditHourLimitExceededException("6 Credit Hour Limit Exceeded for Graduate Students");
        }
        else {
            if( this.getTotalCredit() + c.getCredit() > 9 )
                throw new CreditHourLimitExceededException("9 Credit Hour Limit Exceeded for Undergraduate Students");
        }
        
        // accept a course object and add 
        // that to the list (courses)
        Course tmp = new Course(c);
        this.courses.add( tmp );
    }
    
    /**
     * Prints the student's name and their enrolled courses to the standard output.
     * Each course is preceded by its index in the course list.
     */
    public void introduce() {
        System.out.println("Student Name: " + this.name);
        
        for(int i = 0; i < this.courses.size(); i++) {
            Course tmp = this.courses.get(i);
            System.out.printf("[%d] %s\n", i, tmp);
        }
        
        //System.out.println();
    }
    
    /**
     * Returns a deep copy of the student, including their course list.
     *
     * @return a new Student object that is a copy of this student
     */
     /*
    public Student duplicate() {
        // create a student with same name
        Student newStud = new Student(this);
        
        //newStud.courses = this.courses;
        return newStud;
    }
    */
    
    /**
     * Creates and returns a deep copy of this student.
     * <p>
     * Subclasses must implement this to return an instance of their concrete type.
     * </p>
     * 
     * @return a new {@code Student} object duplicating this instance
     */
     public abstract Student duplicate();
    
    /**
     * Returns the student's name.
     * 
     * @return the name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the student's name.
     * 
     * @param name the new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the list of courses the student is enrolled in.
     * 
     * @return the list of courses
     */
    public ArrayList<Course> getCourses() {
        return this.courses;
    }
    
    /**
     * Calculates and returns the total credit hours the student is enrolled in.
     * 
     * @return the total credit hours
     */
    public int getTotalCredit() {
        int total = 0;
        
        for(Course c : this.courses) {
            total = total + c.getCredit();
        }
        
        return total;
    }
    
    /**
     * Calculates the tuition due based on total credits and the rate per credit.
     * Demonstrates the Template Method design pattern.
     * 
     * @return the total tuition due
     */
    public double getTuitionDue() {
        // demonstration of Template Method Pattern
        return this.getTotalCredit() * getRate();
    }
    
    /**
     * Returns the tuition rate per credit hour.
     * Subclasses must provide the implementation.
     * 
     * @return the rate per credit hour
     */
    public abstract double getRate();
    
    /**
     * Returns additional student-specific information.
     * Subclasses must provide the implementation.
     * 
     * @return a string with detailed student information
     */
    public abstract String getInfo();
    
    /**
     * Returns the student's status (e.g., "Undergraduate" or "Graduate").
     * Subclasses must provide the implementation.
     * 
     * @return the student status
     */
    public abstract String getStatus();
    
    /**
     * Compares this Student object with the specified object for order.
     * The comparison is primarily based on the student's status (e.g., undergraduate, graduate).
     * If the statuses are equal, the comparison falls back to lexicographically comparing
     * the students' names.
     *
     * @param obj the object to be compared, which is expected to be a Student.
     * @return a negative integer, zero, or a positive integer as this Student
     *         is less than, equal to, or greater than the specified Student.
     *         - Returns the result of comparing statuses if they differ.
     *         - Returns the result of comparing names if statuses are equal.
     * @throws ClassCastException if the specified object is not a Student.
     */
    @Override
    public int compareTo(Object obj) {
        // explicit downcasting
        Student o = (Student)obj;
        
        // check based on their student type (or status)
        if( this.getStatus().equals( o.getStatus() ) ) {
            // tied student type, break by their name
            // so simply use the compareTo() definition of a
            // string to determine which string is "lesser"
            // than the other
            return this.getName().compareTo( o.getName() );
        }
        else {
            // there is no tie, because the status is a
            // string value, simply use the compareTo()
            // method as well
            return this.getStatus().compareTo( o.getStatus() );
        }
    }
    
    /**
     * Determines whether this student's name contains the given search key (case-insensitive).
     * 
     * @param key the search keyword
     * @return {@code true} if the student's name contains the key, {@code false} otherwise
     */
    @Override
    public boolean isMatch(String key) {
        // make it not case-sensitive
        key = key.toLowerCase();
        
        // check if the key is contained in the name
        return this.getName().toLowerCase().contains(key);
    }
    
    /**
     * Checks equality with another student based on their names.
     * 
     * @param obj the other student to compare to
     * @return {@code true} if the names are equal, {@code false} otherwise
     */
    @Override
    public boolean isEqual(Student obj) {
        // explicit downcasting
        Student o = (Student)obj;
        
        // check only if they have the same name
        // for now, ignore the list of courses
        return this.getName().equals( o.getName() );
    }
    
    /**
     * Returns a {@link GenericList} containing all courses the student is enrolled in.
     * 
     * @return a new {@code GenericList} of courses
     */
    public GenericList<Course> getCourseList() {
        GenericList<Course> list = new GenericList<>();
        
        for(Course c : this.courses)
            list.add(c);
            
        return list;
    }
    
    /**
     * Sets the student's unique ID.
     * 
     * @param studentID the student ID to set
     */
    public void setID(String studentID) {
        this.studentID = studentID;
    }
    
    /**
     * Returns the student's unique ID.
     * 
     * @return the student ID
     */
    public String getID() {
        return this.studentID;
    }
    
    /**
     * Factory method to create a {@code Student} instance of the specified type.
     * 
     * @param type the student type ("graduate" or "undergraduate", case-insensitive)
     * @param id the student ID
     * @param name the student name
     * @return a new {@code GraduateStudent} or {@code UndergraduateStudent} instance
     * @throws InvalidStudentStatusException if the type is unrecognized
     */
    public static Student create(String type, String id, String name) throws InvalidStudentStatusException {
        if( type.toLowerCase().equals("graduate") )
            return new GraduateStudent(id, name);
        else if( type.toLowerCase().equals("undergraduate") )
            return new UndergraduateStudent(id, name);
            
        throw new InvalidStudentStatusException(type + " Invalid Type of Student");
        //return null;
    }
    
}
