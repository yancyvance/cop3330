import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class SimpleController {
    
    // constants needed for file reading and writing
    private static final String STUDENTS_FILE = "students.csv";
    private static final String COURSES_FILE = "courses.csv";
    private static final String ENROLLMENTS_FILE = "enrollments.csv";
    
    private static final String[] STUDENTS_HEADER = {
        "StudentID", "StudentName", "Status", "ThesisTopic", "YearLevel"
    };
    private static final String[] COURSES_HEADER = {
        "Code", "Title", "Credit"
    };
    private static final String[] ENROLLMENTS_HEADER = {
        "StudentID", "Code"
    };
    
    private static final String JSON_FILE = "data.json";
    
    
    // instance variables
    private GenericList<Student> students;
    private GenericList<Course> courses;
    
    
    public SimpleController() throws FileNotFoundException {
        // instantiate the students and course lists
        this.students = new GenericList<>();
        this.courses = new GenericList<>();
        
        // load the student data
        loadStudents();
        
        // load the course data
        loadCourses();
        
        // load the enrollment data
        loadEnrollments();
    }
    
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
            
            Student s;
            
            // determine what kind of student this is
            if( tokens[2].equals("Graduate") ) {
                // create a graduate student
                s = new GraduateStudent( tokens[0], tokens[1] );
                // downcast without an extra variable
                ((GraduateStudent)s).setThesisTopic( tokens[3] );
            }
            else {
                // create an undergraduate student
                s = new UndergraduateStudent( tokens[0], tokens[1] );
                // downcast with an extra variable
                UndergraduateStudent ugs = (UndergraduateStudent)s;
                ugs.setYearLevel( Integer.parseInt(tokens[4]) );
            }
            
            //s.introduce();
            
            // add student to the list of students
            students.add( s );
        }
        
        // close the file
        in.close();
    }
    
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
            
            // register the student to the course
            registerStudentToCourse( tokens[0], tokens[1] );
        }
        
        // close the file
        in.close();
    }
    
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
    
    public void registerStudentToCourse(String student, String code) {
        // get the Student object who has this ID
        Student s = lookupStudent( student );
        
        // get the Course object who has this code
        Course c = lookupCourse( code );
        
        // add this course to the student's list of courses
        s.addCourse( c );
    }
    
    public void addNewCourse(String code, String title, String credit) {
        this.courses.add( new Course(code, title, Integer.parseInt(credit) ) );
    }
    
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
    
    public GenericList<Student> getStudentList() {
        return this.students;
    }
    
    public GenericList<Course> getCourseList() {
        return this.courses;
    }
    
    
    // advanced topic, no need to read beyond this point
    public StudentListModel getStudentListModel() {
        return new StudentListModel( this.students );
    }
    
    public StudentListModel getStudentListModel(String key) {
        return new StudentListModel( this.students.query(key) );
    }
    
    public CourseListModel getCourseListModel() {
        return new CourseListModel( this.courses );
    }
    
    public CourseListModel getCourseListModel(Student s) {
        return new CourseListModel( s.getCourseList() );
    }    
        
}
