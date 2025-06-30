import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Main {
    
    public static void main(String[] args) throws FileNotFoundException {
        GraduateStudent gs = new GraduateStudent("John Doe");
        
        //System.out.println( gs.getName() );
        
        
        gs.addCourse( new Course("COP3330", "Object-Oriented Programming", 3) );
        gs.addCourse( new Course("COP3502", "Computer Science I", 3) );
        //gs.name = "Jane";
        gs.introduce();
        
        System.out.println( gs.getTotalCredit() );
        //gs.m2();
        /*
        //Scanner input = new Scanner(System.in);   // standard input
        Scanner input = new Scanner( new File("input.txt") );   // from file
        
        int T = input.nextInt();
        input.nextLine();
        
        for(int i = 0; i < T; i++) {
            String name = input.nextLine();
            
            int C = input.nextInt();
            input.nextLine();
            
            for(int j = 0; j < C; j++) {
                String code = input.next();
                int credit = input.nextInt();
                String title = input.nextLine().trim();
                
                System.out.println(code + " " + credit + " " + title);
            }
        }
        
        input.close();
        */
        
        /*
        Student s = new Student("John Doe");
        
        s.addCourse( new Course("COP3330", "Object-Oriented Programming", 3) );
        s.addCourse( new Course("COP3502", "Computer Science I", 3) );
        
        s.introduce();
        
        //Student t = s;
        Student t = s.duplicate();
        
        t.introduce();
        t.courses.remove(0);
        
        t.introduce();
        
        s.introduce();
        
        
        Course c = new Course("COP3223", "Intro to Programming", 3);
        
        System.out.println(c);
        
        Student u = new Student("Super Mario");
        
        u.addCourse(c);
        
        u.introduce();
        
        s.addCourse(c);
        
        s.introduce();
        
        c.setCode("123");
        
        u.introduce();
        
        s.introduce();
        */
    }
    
}
