import java.util.ArrayList;

public class Student {
    
    private String name;
    //private int creditHours;
    private ArrayList<Course> courses;
    
    private static final double TUITION_RATE = 105.07;
    
    public Student(String name) {
        this.setName( name );
        this.courses = new ArrayList<>();
    }
    
    public Student() {
        this( "" );
    }

    public Student(Student ob) {
        this.setName( ob.getName() );
        
        this.courses = new ArrayList<>();
        
        // deep copy each course objects as well
        for( Course c : ob.courses ) {
            this.registerCourse( new Course(c) );
        }
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getName() {
        return this.name;
    }
    
    public int getCreditHours() {
        int sum = 0;
        
        for(Course c : this.courses) {
            sum = sum + c.getCreditHour();
        }
        
        return sum;
    }
    
    public double getTuitionDue() {
        return TUITION_RATE * this.getCreditHours();
    }
    
    public void introduce() {
        System.out.printf("Hi, I'm %s with %d credit hours - $ %.2f\n", this.getName(), this.getCreditHours(), this.getTuitionDue());
        
        for( Course c : this.courses ) {
            System.out.println( c );
        }
    }
    
    public boolean isEnrolled() {
        /*
        if( this.getCreditHours() > 0 )
            return true;
        return false;
        */
        return this.getCreditHours() > 0;
    }
    
    public boolean isEqual(Student another) {
        String myName = this.getName();
        String anotherName = another.getName();
        
        int myCredit = this.getCreditHours();
        int anotherCredit = another.getCreditHours();
        
        return myName.equals(anotherName) && myCredit == anotherCredit;
        /*
        if( myName.equals(anotherName) && myCredit == anotherCredit ) {
            
                return true;
        }
        
        return false;
        */
    }
    
    public String toString() {
        return this.getName();
    }
    
    public Student duplicate() {
        return new Student(this);
    }
    
    public void registerCourse(Course c) {
        this.courses.add( c );
    }
    
}
