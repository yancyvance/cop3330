import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * The {@code Controller} class acts as the central coordinator
 * for managing lists of students and courses. It provides methods
 * to load data from a file, query students and courses, and convert
 * the lists into formats suitable for display in UI tables.
 * 
 * <p>This class maintains internal {@link StudentList} and {@link CourseList}
 * objects that hold all student and course data respectively.</p>
 */
public class Controller {

    /** List of all students managed by this controller */
    private StudentList students;
    
    /** List of all courses managed by this controller */
    private CourseList courses;


    /**
     * Constructs a Controller with empty student and course lists.
     */
    public Controller() {
        // instantiate empty lists for students and courses
        this.students = new StudentList();
        this.courses = new CourseList();
    }
    
    /**
     * Retrieves all student data as a 2D array of Strings
     * formatted for UI display.
     * 
     * @return a 2D String array representing all students' data
     */
    public String[][] getStudentListData() {
        // convert the student list into a 2D String array
        return Utility.studentListToArray( this.students );
    }
    
    /**
     * Retrieves student data filtered by the given key
     * as a 2D array of Strings formatted for UI display.
     * Only students matching the query key are included.
     * 
     * @param key the search query string to filter students
     * @return a 2D String array of filtered student data
     */
    public String[][] getStudentListData(String key) {
        // convert a filtered student list into a 2D String array
        // the student list will contain all those that matches
        // the given query key
        return Utility.studentListToArray( this.students.query(key) );
    }
    
    /**
     * Retrieves all course data as a 2D array of Strings
     * formatted for UI display.
     * 
     * @return a 2D String array representing all courses' data
     */
    public String[][] getCourseListData() {
        // convert the course list into a 2D String array
        return Utility.courseListToArray( this.courses );
    }
    
    /**
     * Retrieves the course data of a specific student
     * as a 2D array of Strings formatted for UI display.
     * 
     * @param student the student whose courses to retrieve
     * @return a 2D String array of the student's course data
     */
    public String[][] getCourseListData(Student student) {
        // convert a student's course list into a 2D String array
        return Utility.courseListToArray( student.getCourseList() );
    }
    
    /**
     * Finds and returns the first {@link Student} object
     * that matches the provided key query.
     * 
     * @param key the search key to find the student
     * @return the first matching Student, or null if none found
     */
    public Student getStudent(String key) {
        return students.findMatch(key);
    }
    
    /**
     * Finds and returns the first {@link Course} object
     * that matches the provided key query.
     * 
     * @param key the search key to find the course
     * @return the first matching Course, or null if none found
     */
    public Course getCourse(String key) {
        return courses.findMatch(key);
    }
    
    /**
     * Loads student and course data from a file with the specified filename.
     * The file format must follow the expected structure with student and
     * course information. Students can be either Graduate or Undergraduate.
     * 
     * @param filename the path to the data file
     * @throws FileNotFoundException if the specified file does not exist
     */
    public void loadData(String filename) throws FileNotFoundException {       
        Scanner input = new Scanner( new File(filename) );   // from file
        
        int T = input.nextInt();
        input.nextLine();
        
        for(int i = 0; i < T; i++) {
            String name = input.nextLine();
            
            // read the student type
            String type = input.next();
            
            Student stud;
            
            // check what type of student
            if( type.equals("Graduate") ) {                
                // get the thesis topic
                String topic = input.nextLine().trim();
                
                // create a graduate student object
                GraduateStudent gs = new GraduateStudent(name);
                
                // set the thesis topic
                gs.setThesisTopic(topic);
                
                stud = gs;
            }
            else {
                // get the year level
                int yearLevel = input.nextInt();

                // create an undergraduate student object
                UndergraduateStudent ugs = new UndergraduateStudent(name);
                
                // set the year level
                ugs.setYearLevel( yearLevel );
                
                stud = ugs;
            }
            
            int C = input.nextInt();
            input.nextLine();
            
            for(int j = 0; j < C; j++) {
                String code = input.next();
                int credit = input.nextInt();
                String title = input.nextLine().trim();
                
                // create a course object
                Course cTemp = new Course(code, title, credit);
                
                // no need for an if and else statement
                stud.addCourse( cTemp );
                
                // add this to the list of courses
                this.courses.add(cTemp);
            }
            
            // add this student
            students.add( stud );
        }
        
        // close the file
        input.close();
    }
    
}
