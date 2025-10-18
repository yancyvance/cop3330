import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    
    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        
        Course c = new Course("COP3330", "Object-Oriented Programming", 3);
        Student s = new Student( "John Doe" );
        
        s.registerCourse( c );
        
        s.introduce();
        
        //System.out.println( c );
        /*
        
        University uni = new University();
        
        System.out.println( uni.getStudentCount() );
        
        uni.addStudent( "John Doe" );
        uni.addStudent( new Student("Jane Smith") );
        
        System.out.println( uni.getStudentCount() );
        
        uni.printStudentData();
        
        */
        /*
        ArrayList[] containers = new ArrayList[10];
        
        Student[] studs = new Student[3];
        ArrayList<Student> container = new ArrayList<>();
        
        String choice;
        int count = 0;
        
        do {
            System.out.print("Enter Student Name: ");
            String name = stdin.nextLine();
            
            System.out.print("Enter Total Credit Hours: ");
            int credit = stdin.nextInt();
            
            stdin.nextLine();
            
            System.out.print("Continue (y/n) ? ");
            choice = stdin.nextLine();
            
            System.out.println(name + " " + credit);
            
            Student tmp = new Student( name, credit );
            
            studs[count] = tmp;
            container.add( tmp );
            
            count++;
            
        } while( !choice.equals("n") );
        
        
        
        for(int i = 0; i < count; i++) {
            studs[i].introduce();
        }
        
        for(int i = 0; i < container.size(); i++) {
            //studs[i].introduce();
            Student s = container.get(i);
            s.introduce();
            
            container.get(i).introduce();
        }
        
        
        double sum = 0.0;
        
         for(int i = 0; i < container.size(); i++) {
            //studs[i].introduce();
            Student s = container.get(i);
            sum = sum + s.getTuitionDue();
            
            
        }
        
        
        for(Student s : container) {
            sum = sum + s.getTuitionDue();
        }
        
        System.out.printf("Total Amount: $ %.2f\n", sum);
        */
    }
    
}
        
        
