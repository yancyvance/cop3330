import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Main {
    
    public static void main(String[] args) throws FileNotFoundException {       
        Student[] students;
    
        Scanner input = new Scanner( new File("input_updated.txt") );   // from file
        
        int T = input.nextInt();
        input.nextLine();
        
        // create an array of students
        students = new Student[T];
        
        int ptr = 0;
        
        
        for(int i = 0; i < T; i++) {           
            String name = input.nextLine();
            
            // read the student type
            String type = input.next();
            
            // check what type of student
            if( type.equals("Graduate") ) {                
                // get the thesis topic
                String topic = input.nextLine().trim();
                
                // create a graduate student object
                GraduateStudent gs = new GraduateStudent(name);
                
                // set the thesis topic
                gs.setThesisTopic(topic);
                
                // example of upcasting
                students[ptr++] = gs;
            }
            else {
                // get the year level
                int yearLevel = input.nextInt();

                // create an undergraduate student object
                UndergraduateStudent ugs = new UndergraduateStudent(name);
                
                // set the year level
                ugs.setYearLevel( yearLevel );
                
                // example of upcasting
                students[ptr++] = ugs;
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
                students[ptr-1].addCourse( cTemp );
                
                //System.out.println(code + " " + credit + " " + title);
            }
        }
        
        // close the file
        input.close();
        
        for( Student s : students ) {
            //System.out.println(s);
            if(s != null) {
                s.introduce();
                System.out.printf("%,.2f\n", s.getTuitionDue());
                
                // example of runtime polymorphism
                System.out.print(s.getStatus());
                
                if( s instanceof GraduateStudent ) {
                    // example of downcasting
                    System.out.print(" Thesis Topic: ");
                    System.out.println( ((GraduateStudent)s).getThesisTopic() );
                }
                else {
                    // example of downcasting
                    System.out.print(" Year Level: ");
                    System.out.println( ((UndergraduateStudent)s).getYearLevel() );
                }
                    
                System.out.println();
            }
        }        
    }
    
}
