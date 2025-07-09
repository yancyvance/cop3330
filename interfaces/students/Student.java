import java.util.ArrayList;

/**
 * The {@code Student} class represents a student with a name and a list of enrolled courses.
 * It is an abstract class that provides a foundation for different types of students (e.g., undergraduate, graduate).
 * Implements the {@code Searchable} interface to allow keyword-based matching and equality checks.
 */
public abstract class Student implements Searchable, Comparable<Student> {
    
    /** The name of the student. */
    protected String name;
    
    /** A list of courses the student is enrolled in. */
    private ArrayList<Course> courses;
    
    
    /**
     * Constructs a Student object with a default name "unknown".
     */
    public Student() {
        this("unknown");
    }
    
    /**
     * Constructs a new Student object with the specified name.
     * Initializes an empty course list.
     *
     * @param name the name of the student
     */
    public Student(String name) {
        this.name = name;
        this.courses = new ArrayList<>();
    }
    
    /**
     * Copy constructor. Creates a deep copy of the given Student object.
     * Copies the name and duplicates all courses.
     *
     * @param ob the Student object to copy
     */
    public Student(Student ob) {
        this(ob.name);
        
        for(Course tmp : ob.courses) {
            this.courses.add( new Course(tmp) );
        }
    }
    
    /**
     * Returns the name of the student as a string.
     *
     * @return the student's name
     */
    public String toString() {
        return this.getStatus() + ": " + this.name;
    }
    
    /**
     * Adds a course to the student's course list.
     * A deep copy of the course is added to avoid aliasing.
     *
     * @param c the course to add
     */
    public void addCourse(Course c) {
        // accept a course object and add 
        // that to the list (courses)
        Course tmp = new Course(c);
        this.courses.add( tmp );
    }
    
    /**
     * Prints the student's name followed by their list of enrolled courses.
     * Each course is listed with its index in the course list.
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
     * Returns the name of the student.
     *
     * @return the name of the student
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the name of the student.
     *
     * @param name the new name to assign to the student
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
     * Calculates and returns the total number of credits
     * the student is currently enrolled in.
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
     * Calculates the total tuition due based on the total credits
     * and the rate returned by {@code getRate()}.
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
     * Must be implemented by subclasses.
     *
     * @return the rate per credit
     */
    public abstract double getRate();
    
    /**
     * Returns additional student information.
     * Must be implemented by subclasses.
     *
     * @return a string with student-specific information
     */
    public abstract String getInfo();
    
    /**
     * Returns the status of the student (e.g., undergraduate or graduate).
     * Must be implemented by subclasses.
     *
     * @return the student's status
     */
    public abstract String getStatus();
    
    /**
     * Determines if the student's name contains the given search key (case-insensitive).
     *
     * @param key the string to search for
     * @return true if the student's name contains the key, false otherwise
     */
    @Override
    public boolean isMatch(String key) {
        key = key.toLowerCase();
        return this.getName().toLowerCase().contains(key);
    }
    
    /**
     * Checks if this student is equal to another object by comparing names.
     *
     * @param obj the object to compare with
     * @return true if the names are equal, false otherwise
     */
    @Override
    public boolean isEqual(Object obj) {
        return this.getName().equals( ((Student)obj).getName() );
    }
    
    /**
     * Returns a CourseList object containing all the student's enrolled courses.
     * A new CourseList is constructed and populated with the student's courses.
     *
     * @return a CourseList of the student's courses
     */
    public CourseList getCourseList() {
        CourseList list = new CourseList();
        
        for(Course c : courses)
            list.add(c);
            
        return list;
    }
    
    @Override
    public int compareTo(Student obj) {
        // check based on their student type (or status)
        if( this.getStatus().equals( obj.getStatus() ) ) {
            // tied student type, break by their name
            // so simply use the compareTo() definition of a
            // string to determine which string is "lesser"
            // than the other
            return this.getName().compareTo( obj.getName() );
        }
        else {
            // there is no tie, because the status is a
            // string value, simply use the compareTo()
            // method as well
            return this.getStatus().compareTo( obj.getStatus() );
        }
    }
    
}
