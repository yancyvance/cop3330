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
        
    }
    
    public void loadEnrollments() throws FileNotFoundException {
        // TODO: Complete method
        
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
        
        // write out the header
        sOut.println( String.join(",", STUDENTS_HEADER) );
        
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
        }
        
        sOut.close();
    }
    
    public void saveCourses() throws FileNotFoundException {
        // TODO: Complete method
        
    }
    
    
    // Helper Methods
    public StudentListModel getStudentListModel() {
        return new StudentListModel( university.getStudents() );
    }
    
}
