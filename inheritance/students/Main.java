import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Main {
    
    public static void main(String[] args) throws FileNotFoundException {                
        //Scanner input = new Scanner(System.in);   // standard input
        Scanner input = new Scanner( new File("input_updated.txt") );   // from file
        
        // create the two lists
        ArrayList<GraduateStudent> gradStudents = new ArrayList<>();
        ArrayList<UndergraduateStudent> undergradStudents = new ArrayList<>();
        
        // temporary variables
        GraduateStudent gs = null;
        UndergraduateStudent ugs = null;
        boolean isGrad;
            
        int T = input.nextInt();
        input.nextLine();
        
        for(int i = 0; i < T; i++) {            
            String name = input.nextLine();
            
            // read the student type
            String type = input.next();
            
            // check
            if( type.equals("Graduate") ) {
                // create an object
                gs = new GraduateStudent(name);
                
                // get the thesis topic
                String topic = input.nextLine();
                gs.setThesisTopic(topic);
                
                // add to the list
                gradStudents.add( gs );
                
                // set flag to true
                isGrad = true;
            }
            else {
                // create an object
                ugs = new UndergraduateStudent(name);
                
                // get the year level
                int yearLevel = input.nextInt();
                ugs.setYearLevel(yearLevel);
                
                // add to the list
                undergradStudents.add(ugs);
                
                // set flag to false
                isGrad = false;
            }
            
            int C = input.nextInt();
            input.nextLine();
            
            for(int j = 0; j < C; j++) {
                String code = input.next();
                int credit = input.nextInt();
                String title = input.nextLine().trim();
                
                // create a course object
                Course c = new Course(code, title, credit);
                
                // add this course to the appropriate student
                // based on the flag
                if(isGrad)
                    gs.addCourse(c);
                else
                    ugs.addCourse(c);
                
                //System.out.println(code + " " + credit + " " + title);
            }
        }
        
        // close the input file
        input.close();
        
        
        // display all the graduate students
        for( GraduateStudent s : gradStudents ) {
            s.introduce();
        }
        
        // display all the undergraduate students
        for( UndergraduateStudent s : undergradStudents ) {
            s.introduce();
        }

    }
    
}
