import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;

public class Main {
    
    public static void main(String[] args) throws FileNotFoundException {
        University u = new University("UCF");
        
        //Scanner stdin = new Scanner(System.in);
        Scanner stdin = new Scanner( new File("data2.txt") );
        
        int studentCount = stdin.nextInt();
        stdin.nextLine();
        
        for(int i = 0; i < studentCount; i++) {
            String name = stdin.nextLine();
            
            // read the type of student
            String type = stdin.next();
            
            String info = stdin.nextLine();
            
            // if grad student, then extract thesis topic
            if( type.equals("Graduate") ) {
                String topic = info;
                System.out.println("Grad " + topic);
            }
            // otherwise, extract year level
            else {
                int yearLevel = Integer.parseInt(info.trim());
                System.out.println("Undergrade " + yearLevel);
            }
            
            int courseCount = stdin.nextInt();
            stdin.nextLine();
            
            //System.out.printf("%s %d\n", name, courseCount);
            //Student s = new Student(name);
            
            for(int j = 0; j < courseCount; j++) {
                String code = stdin.next();
                int credit = stdin.nextInt();
                String title = stdin.nextLine().trim();
                
                //System.out.printf("\t%s %d %s\n", code, credit, title);
                Course c = new Course(code, title, credit);
                
                //s.registerCourse( c );
            }
            
            //u.addStudent( s );
        }
        
        //return u;
        
    }
    
}
        
        
