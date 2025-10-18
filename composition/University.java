import java.util.ArrayList;

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
        
        for( int i = 0; i < ob.students.size(); i++) {
            Student tmp = ob.students.get(i);
            Student newStud = new Student(tmp);
            this.addStudent( newStud );
            
            //this.addStudent( new Student( ob.students.get(i) ) );
        }
        
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
        University newUni = new University( this.getName() );
        
        for( Student s : this.students ) {
            newUni.addStudent( s );
        }
        
        return newUni;
    }
    
    
    
    
    
    
    
}
