import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class University {
    
    private String name;
    private ArrayList<Student> students;
    
    public University() {
        //this.name = "Unknown";
        
        //students = new ArrayList<>();
        this( "Unknown" );
    }
    
    public University(String name) {
        this.name = name;
        
        this.students = new ArrayList<>();
    }
    
    public University(University ob) {
        //University newUni = new University( this.getName() );
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
        
        //this.students.add( s );
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
        return new University( this );
        /*
        University newUni = new University( this.getName() );
        
        for( Student s : this.students ) {
            newUni.addStudent( s );
        }
        
        return newUni;
        */
    }
    
    public static University loadFromFile(String fn) throws FileNotFoundException {
        University u = new University("UCF");
        
        //Scanner stdin = new Scanner(System.in);
        Scanner stdin = new Scanner( new File(fn) );
        
        int studentCount = stdin.nextInt();
        stdin.nextLine();
        
        for(int i = 0; i < studentCount; i++) {
            String name = stdin.nextLine();
            
            int courseCount = stdin.nextInt();
            stdin.nextLine();
            
            //System.out.printf("%s %d\n", name, courseCount);
            Student s = new Student(name);
            
            for(int j = 0; j < courseCount; j++) {
                String code = stdin.next();
                int credit = stdin.nextInt();
                String title = stdin.nextLine().trim();
                
                //System.out.printf("\t%s %d %s\n", code, credit, title);
                Course c = new Course(code, title, credit);
                
                s.registerCourse( c );
            }
            
            u.addStudent( s );
        }
        
        return u;
    }
    
}
