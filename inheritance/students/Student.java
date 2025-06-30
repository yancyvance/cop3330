import java.util.ArrayList;

/**
 * The {@code Student} class represents a student with a name and a list of enrolled courses.
 */
public class Student {
    
    /** The name of the student. */
    protected String name;
    
    /** A list of courses the student is enrolled in. */
    private ArrayList<Course> courses;
    
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
        return this.name;
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
        
        System.out.println();
    }
    
    /**
     * Returns a deep copy of the student, including their course list.
     *
     * @return a new Student object that is a copy of this student
     */
    public Student duplicate() {
        // create a student with same name
        Student newStud = new Student(this);
        
        //newStud.courses = this.courses;
        return newStud;
    }
    
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
    
    public int getTotalCredit() {
        int total = 0;
        
        for(Course c : this.courses) {
            total = total + c.getCredit();
        }
        
        return total;
    }
    
}
