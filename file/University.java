import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class University {
    
    private String name;
    private ArrayList<Student> students;
    
    public University() {
        this( "Unknown" );
    }
    
    public University(String name) {
        this.name = name;
        this.students = new ArrayList<>();
    }
    
    public University(University ob) {
        this.name = ob.name;
        
        for( Student s : ob.students ) {
            this.addStudent( new Student(s) );
        }
        
        /*
        for( int i = 0; i < ob.students.size(); i++) {
            Student tmp = ob.students.get(i);
            Student newStud = new Student(tmp);
            this.addStudent( newStud );
            //this.addStudent( new Student( ob.students.get(i) ) );
        }
        */
    }
    
    public void addStudent(Student s) {
        this.students.add( s );
    }
    
    public void addStudent(String n) {
        Student s = new Student( n );
        
        this.addStudent( s );
    }
    
    public double getTotalCollectible() {
        double sum = 0.0;
        
        for(Student s : this.students) {
            sum = sum + s.getTuitionDue();
        }
        
        return sum;
    }
    
    public int getStudentCount() {
        return this.students.size();
    }
    
    public void printStudentData() {
        for(Student s : this.students) {
            s.introduce();
        }
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public University duplicate() {
        return new University(this);
    }
    
    public static University loadFromFile(String fn) throws FileNotFoundException {
        Scanner stdin = new Scanner(new File("data.txt"));
        University u = new University();
        
        int count = stdin.nextInt();
        stdin.nextLine();
        
        for(int i = 0; i < count; i++) {
            // read the name
            String name = stdin.nextLine();
            
            // read the number
            int courseCount = Integer.parseInt( stdin.nextLine() );
            
            //System.out.printf("%s %d\n", name, courseCount);
            Student s = new Student(name);
            
            for(int j = 0; j < courseCount; j++) {
                String code = stdin.next();
                int credit = stdin.nextInt();
                String title = stdin.nextLine().trim();
                
                //System.out.printf("\t%s %d %s\n", code, credit, title);
                Course c = new Course(code, title, credit);
                
                s.registerCourse(c);
            } 
            
            u.addStudent(s);
        }
        
        //u.printStudentData();
        return u;
    }
    
}
