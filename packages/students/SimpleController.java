package cop3330.controller;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import cop3330.exceptions.DuplicateCourseEnrollmentException;
import cop3330.exceptions.CreditHourLimitExceededException;
import cop3330.exceptions.InvalidStudentStatusException;

import cop3330.misc.StudentListModel;
import cop3330.misc.CourseListModel;

import cop3330.models.Student;
import cop3330.models.GraduateStudent;
import cop3330.models.UndergraduateStudent;
import cop3330.models.Course;

import cop3330.util.GenericList;
import cop3330.util.JSONUtility;


/**
 * The {@code SimpleController} class manages the core logic of the application,
 * including loading, saving, and manipulating data related to students, courses,
 * and their enrollments.
 *
 * <p>It supports operations such as registering students to courses, saving
 * and loading from CSV and JSON files, and interfacing with UI models.</p>
 *
 * <p>This controller uses {@link GenericList} for internal data management
 * and supports persistence through both CSV files and a JSON export.</p>
 *
 * <p>Note: Some methods return Swing-compatible table models for integration with UI components,
 * but these are secondary to the business logic provided here. You can simply ignore them.</p>
 */
public class SimpleController {
    
    // constants needed for file reading and writing
    
    /**
     * The CSV file that stores student data.
     * <p>
     * This version uses an updated file with error-prone rows included for testing input validation.
     */
    private static final String STUDENTS_FILE = "students_updated.csv";
    //private static final String STUDENTS_FILE = "students.csv";

    /**
     * The CSV file that stores course data.
     */
    private static final String COURSES_FILE = "courses.csv";

    /**
     * The CSV file that stores student-course enrollment relationships.
     */
    private static final String ENROLLMENTS_FILE = "enrollments.csv";

    /**
     * The header fields for the students CSV file.
     * <ul>
     *   <li>StudentID — Unique identifier for each student.</li>
     *   <li>StudentName — Full name of the student.</li>
     *   <li>Status — Undergraduate or Graduate.</li>
     *   <li>ThesisTopic — Only applicable for graduate students.</li>
     *   <li>YearLevel — Only applicable for undergraduate students.</li>
     * </ul>
     */
    private static final String[] STUDENTS_HEADER = {
        "StudentID", "StudentName", "Status", "ThesisTopic", "YearLevel"
    };

    /**
     * The header fields for the courses CSV file.
     * <ul>
     *   <li>Code — Unique course code (e.g., COP3330).</li>
     *   <li>Title — Course title.</li>
     *   <li>Credit — Number of credit hours.</li>
     * </ul>
     */
    private static final String[] COURSES_HEADER = {
        "Code", "Title", "Credit"
    };

    /**
     * The header fields for the enrollments CSV file.
     * <ul>
     *   <li>StudentID — ID of the student enrolled.</li>
     *   <li>Code — Code of the enrolled course.</li>
     * </ul>
     */
    private static final String[] ENROLLMENTS_HEADER = {
        "StudentID", "Code"
    };

    /**
     * The JSON file used for storing and retrieving the entire student list.
     */
    private static final String JSON_FILE = "data.json";
    
    
    // instance variables
    
    /** A generic list holding student records. */
    private GenericList<Student> students;
    
    /** A generic list holding course records. */
    private GenericList<Course> courses;
    
    
    /**
     * Constructs a new {@code SimpleController} instance with empty student and course lists.
     */
    public SimpleController() {
        // instantiate the students and course lists
        this.students = new GenericList<>();
        this.courses = new GenericList<>();
        
        try {
            // load the student data
            loadStudents();
            
            // load the course data
            loadCourses();
            
            // load the enrollment data
            loadEnrollments();
        }
        catch (Exception ex) {
            System.out.println("There is an error!");
            ex.printStackTrace();
        }
    }
    
    /**
     * Loads student data from the students.csv file.
     * 
     * @throws FileNotFoundException if the file cannot be opened
     */
    public void loadStudents() throws FileNotFoundException {
        // open the file for reading
        Scanner in = new Scanner(new File(STUDENTS_FILE));
        
        // read the first line for columns
        in.nextLine();
        
        // while there is still something to read
        while( in.hasNext() ) {
            // read the entire line
            String line = in.nextLine();
            
            // split based on delimiter
            String[] tokens = line.split(",");
            
            try {
                // create a student
                Student s = Student.create( tokens[2], tokens[0], tokens[1] );
                
                // determine what kind of student this is
                if( s instanceof GraduateStudent ) {
                    // downcast without an extra variable
                    ((GraduateStudent)s).setThesisTopic( tokens[3] );
                }
                else {
                    // downcast with an extra variable
                    UndergraduateStudent ugs = (UndergraduateStudent)s;
                    ugs.setYearLevel( Integer.parseInt(tokens[4]) );
                }
                
                //s.introduce();
                
                // add student to the list of students
                students.add( s );
            }
            catch (ArrayIndexOutOfBoundsException ex) {
                System.out.println("Invalid Row!");
            }
            catch (InvalidStudentStatusException ex) {
                System.out.println("Invalid Student Type Found!");
            }
        }
        
        // close the file
        in.close();
    }
    
    /**
     * Loads course data from the courses.csv file and sorts the course list.
     * 
     * @throws FileNotFoundException if the file cannot be opened
     */
    public void loadCourses() throws FileNotFoundException {
        // open the file for reading
        Scanner in = new Scanner(new File(COURSES_FILE));
        
        // read the first line for columns
        in.nextLine();
        
        // while there is still something to read
        while( in.hasNext() ) {
            // read the entire line
            String line = in.nextLine();
            
            // split based on delimiter
            String[] tokens = line.split(",");
            
            // add course to the list of courses
            courses.add( new Course( tokens[0], tokens[1], Integer.parseInt(tokens[2]) ) );
        }
        
        // close the file
        in.close();
        
        // sort the courses
        this.courses.sort();
    }
    
    /**
     * Loads enrollment data from the enrollments.csv file.
     * 
     * @throws FileNotFoundException if the file cannot be opened
     */
    public void loadEnrollments() throws FileNotFoundException {
        // open the file for reading
        Scanner in = new Scanner(new File(ENROLLMENTS_FILE));
        
        // read the first line for columns
        in.nextLine();
        
        // while there is still something to read
        while( in.hasNext() ) {
            // read the entire line
            String line = in.nextLine();
            
            // split based on delimiter
            String[] tokens = line.split(",");
            
            try {
                // register the student to the course
                registerStudentToCourse( tokens[0], tokens[1] );
            }
            catch (DuplicateCourseEnrollmentException | CreditHourLimitExceededException ex) {
                System.out.println("There was an invalid record found. Skipping!");
                ex.printStackTrace();
            }
        }
        
        // close the file
        in.close();
    }
    
    /**
     * Finds and returns a student based on ID.
     * 
     * @param studentID the ID of the student
     * @return the Student object if found; otherwise, null
     */
    public Student lookupStudent(String studentID) {
        // loop through all the students in the list
        for(int i = 0; i < this.students.size(); i++) {
            // downcast to a student
            Student s = (Student)(this.students.get(i));
            
            // if this student has the same ID
            // return reference to it
            if( s.getID().equals( studentID ) )
                return s;
        }
        
        // not found so return null
        return null;
    }
    
    /**
     * Finds and returns a course based on code.
     * 
     * @param courseCode the code of the course
     * @return the Course object if found; otherwise, null
     */
    public Course lookupCourse(String courseCode) {
        // loop through all the courses in the list
        for(int i = 0; i < this.courses.size(); i++) {
            // downcast to a course
            Course c = (Course)(this.courses.get(i));
            
            // if this course has the course code
            // return reference to it
            if( c.getCode().equals( courseCode ) )
                return c;
        }
        
        // not found so return null
        return null;
    }
    
    /**
     * Registers a student to a course based on the student's ID and course code.
     *
     * @param student the ID of the student to register
     * @param code the code of the course to register in
     * @throws CreditHourLimitExceededException if the student exceeds their allowed credit limit
     * @throws DuplicateCourseEnrollmentException if the student is already enrolled in the course
     */
    public void registerStudentToCourse(String student, String code) throws DuplicateCourseEnrollmentException, CreditHourLimitExceededException {
        // get the Student object who has this ID
        Student s = lookupStudent( student );
        
        // get the Course object who has this code
        Course c = lookupCourse( code );
        
        //System.out.printf("Lookup: %s and %s\n", s, c);
        if( s == null || c == null ) {
            System.out.println("Incomplete information!");
            return;
        }
        
        // add this course to the student's list of courses
        s.addCourse( c );
    }
    
    /**
     * Adds a new course to the course list.
     * 
     * @param code the course code
     * @param title the course title
     * @param credit the credit value of the course
     */
    public void addNewCourse(String code, String title, String credit) {
        this.courses.add( new Course(code, title, Integer.parseInt(credit) ) );
    }
    
    /**
     * Saves students and their enrollments to their respective CSV files.
     * 
     * @throws FileNotFoundException if files cannot be written
     */
    public void saveStudentsAndEnrollments() throws FileNotFoundException {
        PrintWriter sOut = new PrintWriter( new File(STUDENTS_FILE) );
        PrintWriter eOut = new PrintWriter( new File(ENROLLMENTS_FILE) );
        
        // print the column names first
        sOut.println( String.join(",", STUDENTS_HEADER) );
        eOut.println( String.join(",", ENROLLMENTS_HEADER) );
        
        // loop through each students
        for(int i = 0; i < students.size(); i++) {
            Student s = (Student)students.get(i);
            
            sOut.printf("%s,%s,%s,", s.getID(), s.getName(), s.getStatus());
            
            if( s.getStatus().equals("Graduate") ) {
                sOut.printf("%s", ((GraduateStudent)s).getThesisTopic());
            }
            else {
                sOut.printf(",%d", ((UndergraduateStudent)s).getYearLevel());
            }
            
            sOut.println();
            
            // get the courses
            GenericList<Course> courses = s.getCourseList();
            
            // loop through each course
            for(int j = 0; j < courses.size(); j++) {
                Course c = (Course)courses.get(j);
                eOut.printf("%s,%s\n", s.getID(), c.getCode());
            }
        }
        
        sOut.close();
        eOut.close();
    }
    
    /**
     * Saves all course information to the courses.csv file.
     * 
     * @throws FileNotFoundException if the file cannot be written
     */
    public void saveCourses() throws FileNotFoundException {
        PrintWriter out = new PrintWriter( new File(COURSES_FILE) );
        
        // print the column names first
        out.println( String.join(",", COURSES_HEADER) );
        
        // loop through each courses
        for(int i = 0; i < courses.size(); i++) {
            Course c = (Course)courses.get(i);
            
            out.printf("%s,%s,%d\n", c.getCode(), c.getTitle(), c.getCredit());
        }
        
        out.close();
    }
    
    /**
     * Saves all data to CSV and JSON files.
     * 
     * @return true if all data was saved successfully; false otherwise
     */
    public boolean saveData() {
        try {
            // save the CSV files
            saveStudentsAndEnrollments();
            saveCourses();
            
            // export the json file
            JSONUtility.exportJSON(students, JSON_FILE);
        }
        catch (FileNotFoundException ex) {
            System.out.println("There is a problem writing to a file!");
            ex.printStackTrace();
            
            return false;
        }
        
        return true;
    }
    
    /**
     * Adds a new student to the system.
     * 
     * @param studentID the ID of the student
     * @param name the name of the student
     * @param status "Graduate" or "Undergraduate"
     * @param thesisTopic thesis topic (for graduates)
     * @param yearLevel year level (for undergraduates)
     */
    public void addNewStudent(String studentID, String name, String status, String thesisTopic, String yearLevel) {
        Student s;
        if( status.equals("Graduate") ) {
            s = new GraduateStudent(studentID, name);
            ((GraduateStudent)s).setThesisTopic(thesisTopic);
        }
        else {
            s = new UndergraduateStudent(studentID, name);
            ((UndergraduateStudent)s).setYearLevel( Integer.parseInt(yearLevel) );
        }
        this.students.add( s );
    }
    
    /**
     * Returns the internal list of students.
     * 
     * @return the list of students
     */
    public GenericList<Student> getStudentList() {
        return this.students;
    }
    
     /**
     * Returns the internal list of courses.
     * 
     * @return the list of courses
     */
    public GenericList<Course> getCourseList() {
        return this.courses;
    }
    
    
    // advanced topic, no need to read beyond this point
    
    /**
     * Returns a table model for displaying all students.
     * Intended for UI integration.
     *
     * @return the student list table model
     */
    public StudentListModel getStudentListModel() {
        return new StudentListModel( this.students );
    }
    
    /**
     * Returns a table model for displaying all students
     * that matches the given key string.
     * Intended for UI integration.
     *
     * @param key the key string
     * @return the student list table model of all matched students
     */
    public StudentListModel getStudentListModel(String key) {
        return new StudentListModel( this.students.query(key) );
    }
    
    /**
     * Returns a table model for displaying all courses.
     * Intended for UI integration.
     *
     * @return the course list table model
     */
    public CourseListModel getCourseListModel() {
        return new CourseListModel( this.courses );
    }
    
    /**
     * Returns a table model for displaying the courses taken by a specific student.
     * Intended for UI integration.
     *
     * @param s the student whose course list to display
     * @return the student's course list table model
     */
    public CourseListModel getCourseListModel(Student s) {
        return new CourseListModel( s.getCourseList() );
    }    
        
}
