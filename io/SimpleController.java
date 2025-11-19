import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class SimpleController {
    
    private University university;
    
    public SimpleController() throws FileNotFoundException {
        this.university = new University();
        
        // populate the containers
        this.loadStudents();
        this.loadCourses();
        this.loadEnrollments();
    }
    
    public void loadStudents() throws FileNotFoundException {
        Scanner in = new Scanner(new File("students.csv"));
        
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
    
    
    // Helper Methods
    public StudentListModel getStudentListModel() {
        return new StudentListModel( university.getStudents() );
    }
    
}
