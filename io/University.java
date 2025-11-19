import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class University {
    
    private String name;
    private ArrayList<Student> students;
    private HashMap<String,Course> courses;
    
    public University() {
        // call the other constructor
        this( "Unknown" );
    }
    
    public University(String name) {
        // set the name and create an empty list
        this.name = name;
        this.students = new ArrayList<>();
        this.courses = new HashMap<>();
    }
    
    public University(University ob) {
        //University newUni = new University( this.getName() );
        this.name = ob.name;
        
        for( Student s : ob.students ) {
            // create a deep copy of the appropriate object
            // this could either be a GraduateStudent
            // or UndergraduateStudent
            this.addStudent( s.duplicate() );
        }       
    }
    
    public void addStudent(Student s) {
        this.students.add( s );
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
            
            // add a space in between
            System.out.println();
        }
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public University duplicate() {
        // call the copy constructor
        return new University( this );
    }
    
    public static University loadFromFile(String fn) throws FileNotFoundException {
        University u = new University("UCF");
        
        Scanner stdin = new Scanner( new File(fn) );
        
        int studentCount = stdin.nextInt();
        stdin.nextLine();
        
        for(int i = 0; i < studentCount; i++) {
            String name = stdin.nextLine();
            
            // read the type of student and info
            String type = stdin.next();
            String info = stdin.nextLine();
            
            // call the simple factory method to create
            // the appropriate object
            Student s = Student.createStudent(type, name);
            
            // is this a graduate student object?
            if( s instanceof GraduateStudent ) {
                String topic = info;

                // downcasting; Student reference variable s to GraduateStudent
                ( (GraduateStudent) s).setThesisTopic( topic );
            }
            // is this an undergraduate student object?
            else if( s instanceof UndergraduateStudent ) {
                int yearLevel = Integer.parseInt( info.trim() );
                
                // downcasting; Student reference variable s to UndergraduateStudent
                ((UndergraduateStudent) s).setYearLevel( yearLevel );
            }
            
            // load the course information of this student
            int courseCount = stdin.nextInt();
            stdin.nextLine();
            
            for(int j = 0; j < courseCount; j++) {
                String code = stdin.next();
                int credit = stdin.nextInt();
                String title = stdin.nextLine().trim();
                
                // create a Course object and register the student to it
                Course c = new Course(code, title, credit);
                s.registerCourse( c );
            }
            
            // after loading all information about the student,
            // add it now to the list of students
            u.addStudent( s );
        }
        
        return u;
    }
    
    public void addCourse(Course c) {
        this.courses.put(c.getCode(), c);
    }
    
    public Course findCourse(String code) {
        return this.courses.get( code );
    }
    
    public Student findStudent(String id) {
        for(int i = 0; i < this.students.size(); i++) {
            if( this.students.get(i).getID().equals( id ) )
                return this.students.get(i);
        }
        
        return null;
    }
    
    public ArrayList<Student> getStudents() {
        return this.students;
    }
    
    public ArrayList<Course> getCourses() {
        ArrayList<Course> res = new ArrayList<>();
        
        for( Course c : this.courses.values() ) {
            res.add( c );
        }
        
        return res;
    }
}
