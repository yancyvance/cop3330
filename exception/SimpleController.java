import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

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
    
    private University university;
    
    public SimpleController() throws FileNotFoundException {
        this.university = new University();
        
        // load all data
        this.loadData();
    }
    
    public void loadData() throws FileNotFoundException {
        // populate the containers
        this.loadStudents();
        this.loadCourses();
        this.loadEnrollments();
        
        System.out.println("Loaded Data:");
        System.out.printf("%d Students\n", university.getStudentCount());
        System.out.printf("%d Courses\n", university.getCourses().size());
    }
    
    public void loadStudents() throws FileNotFoundException {
        Scanner in = new Scanner(new File(STUDENTS_FILE));
        
        // read the header
        in.nextLine();
        
        while( in.hasNext() ) {
            String line = in.nextLine();
            
            // split token
            String[] tok = line.split(",");
            
            String sType = tok[2].toLowerCase();
            
            // create the appropriate type of student
            Student s = Student.createStudent(sType, tok[1]);
            
            // set ID
            s.setID( tok[0] );
            
            // check type of student
            if( s instanceof GraduateStudent ) {
                ((GraduateStudent)s).setThesisTopic( tok[3] );
            }
            else if( s instanceof UndergraduateStudent ) {
                ((UndergraduateStudent)s).setYearLevel( Integer.parseInt( tok[4] ) );
            }
            
            // add to the university
            university.addStudent( s );
        }
        
        in.close();
    }
    
    public void loadCourses() throws FileNotFoundException {
        // TODO: Complete method
        Scanner in = new Scanner(new File(COURSES_FILE));
        
        // read the header
        in.nextLine();
        
        while( in.hasNext() ) {
            String line = in.nextLine();
            
            // split token
            String[] tok = line.split(",");
                        
            // add to the university
            university.addCourse( new Course( tok[0], tok[1], Integer.parseInt(tok[2]) ) );
        }
        
        in.close();
    }
    
    public void loadEnrollments() throws FileNotFoundException {
        // TODO: Complete method
        Scanner in = new Scanner(new File(ENROLLMENTS_FILE));
        
        // read the header
        in.nextLine();
        
        while( in.hasNext() ) {
            String line = in.nextLine();
            
            // split token
            String[] tok = line.split(",");
                        
            // get the Student and Course objects
            Student s = university.findStudent( tok[0] );
            Course c = university.findCourse( tok[1] );
            
            if( s != null && c != null ) {
                try {
                    s.registerCourse(c);
                }
                catch(CreditHourLimitExceededException ex) {
                    System.out.println("Ignoring this course!");
                }
            }
                
        }
        
        in.close();
    }
    
    public boolean saveData() throws FileNotFoundException {
        // save all data to files
        this.saveStudentsAndEnrollments();
        this.saveCourses();
        
        return true;      
    }
    
    public void saveStudentsAndEnrollments() throws FileNotFoundException {
        // TODO: Complete method
        PrintWriter sOut = new PrintWriter(STUDENTS_FILE);
        PrintWriter eOut = new PrintWriter(ENROLLMENTS_FILE);
        
        // write out the header
        sOut.println( String.join(",", STUDENTS_HEADER) );
        eOut.println( String.join(",", ENROLLMENTS_HEADER) );
        
        // for every student
        for( Student s : university.getStudents() ) {
            sOut.printf("%s,%s,", s.getID(), s.getName());
            
            if( s instanceof GraduateStudent ) {
                sOut.printf("%s,%s", "Graduate", ((GraduateStudent)s).getThesisTopic());
            }
            else if( s instanceof UndergraduateStudent ) {
                sOut.printf("%s,,%d", "Undergraduate", ((UndergraduateStudent)s).getYearLevel());
            }
            
            sOut.println();
            
            
            // for every enrollment of this student
            for( Course c : s.getCourses() )
                eOut.printf("%s,%s\n", s.getID(), c.getCode());
        }
        
        sOut.close();
        eOut.close();
    }
    
    public void saveCourses() throws FileNotFoundException {
        // TODO: Complete method
        PrintWriter out = new PrintWriter(COURSES_FILE);
        
        // write out the header
        out.println( String.join(",", COURSES_HEADER) );
        
        // for every student
        for( Course c : university.getCourses() ) {
            out.printf("%s,%s,%d\n", c.getCode(), c.getTitle(), c.getCreditHour());
        }
        
        out.close();
    }
    
    
    
    public void registerStudentToCourse(String id, String code) throws CreditHourLimitExceededException {
        Student s = this.university.findStudent(id);
        Course c = this.university.findCourse(code);
        
        if(s != null && c != null)
            s.registerCourse(c);
    }
    
    
    
    // Helper Methods Needed by the UI (view)
    public Student lookupStudent(String id) {
        return this.university.findStudent(id);
    }
    
    public void addNewCourse(String code, String title, String credit) {
        this.university.addCourse( new Course(code, title, Integer.parseInt(credit)) );
    }
    
    public void addNewStudent(String id, String name, String status, String info) {
        // create the appropriate type of student
        Student s = Student.createStudent(status, name);
        
        // set ID
        s.setID( id );
        
        // check type of student
        if( s instanceof GraduateStudent ) {
            ((GraduateStudent)s).setThesisTopic( info );
        }
        else if( s instanceof UndergraduateStudent ) {
            ((UndergraduateStudent)s).setYearLevel( Integer.parseInt( info ) );
        }
        
        // add to the university
        university.addStudent( s );
    }
    
    
    
    // advanced topic, no need to read beyond this point
    public StudentListModel getStudentListModel() {
        return new StudentListModel( this.university.getStudents() );
    }
    
    public StudentListModel getStudentListModel(String key) {
        ArrayList<Student> res = new ArrayList<>();
        
        for(Student s : this.university.getStudents())
            if( s.matches(key) )
                res.add(s);
                
        return new StudentListModel( res );
    }
    
    public CourseListModel getCourseListModel() {
        return new CourseListModel( this.university.getCourses() );
    }
    
    public CourseListModel getCourseListModel(Student s) {
        return new CourseListModel( s.getCourses() );
    }    
    
}
