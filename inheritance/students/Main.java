import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Main {
    
    public static void main(String[] args) throws FileNotFoundException {       
        // container of all students
        //GraduateStudent[] gStudents;
        //UndergraduateStudent[] uStudents;
        Student[] students;
    
        //Scanner input = new Scanner(System.in);   // standard input
        Scanner input = new Scanner( new File("input_updated.txt") );   // from file
        
        int T = input.nextInt();
        input.nextLine();
        
        //gStudents = new GraduateStudent[T];
        //uStudents = new UndergraduateStudent[T];
        students = new Student[T];
        
        
        /*
        int gPtr = 0;
        int uPtr = 0;
        */
        int ptr = 0;
        
        
        for(int i = 0; i < T; i++) {
            // not needed anymore
            //boolean isGrad = false;
            
            String name = input.nextLine();
            
            // read the student type
            String type = input.next();
            
            // check what type of student
            if( type.equals("Graduate") ) {                
                // get the thesis topic
                String topic = input.nextLine();
                
                // create a graduate student object
                GraduateStudent gs = new GraduateStudent(name);
                //isGrad = true;
                
                // set the thesis topic
                gs.setThesisTopic(topic);
                
                //gStudents[gPtr++] = gs;
                students[ptr++] = gs;
            }
            else {
                // get the year level
                int yearLevel = input.nextInt();

                // create an undergraduate student object
                UndergraduateStudent ugs = new UndergraduateStudent(name);
                //isGrad = false;'
                
                // set the year level
                ugs.setYearLevel( yearLevel );
                
                //uStudents[uPtr++] = ugs;
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
                
                /*
                if(isGrad) {
                    //gStudents[gPtr-1].addCourse( cTemp );
                    students[ptr-1].addCourse( cTemp );
                }
                else {
                    //uStudents[uPtr-1].addCourse( cTemp );
                    students[ptr-1].addCourse( cTemp );
                }
                */
                
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
            }
        }
        
        /*
        for( GraduateStudent s : gStudents ) {
            //System.out.println(s);
            if(s != null) {
                s.introduce();
                System.out.printf("%,.2f\n", s.getTuitionDue());
            }
        }
        
        for( UndergraduateStudent s : uStudents ) {
            //System.out.println(s);
            if(s != null) {
                s.introduce();
                System.out.printf("%,.2f\n", s.getTuitionDue());
            }
        }
        */
        
    }
    
}
