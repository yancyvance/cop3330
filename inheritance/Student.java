import java.util.ArrayList;

public abstract class Student {
    
    private String name;
    //private int creditHours;
    private ArrayList<Course> courses;
    
    private static final double TUITION_RATE = 105.07;
    
    public Student(String name) {
        this.setName( name );
        this.courses = new ArrayList<>();
    }
    
    public Student() {
        //this( null, 0 );
        this( "" );
    }
    /*
    public Student(String name, int credit) {
        this.setName( name );
        //this.setCreditHours( credit );
        
        this.courses = new ArrayList<>();
    }
    */
    public Student(Student ob) {
        this.setName( ob.getName() );
        //this.setCreditHours( ob.getCreditHours() );
        
        this.courses = new ArrayList<>();
        
        for( Course c : ob.courses ) {
            this.registerCourse( new Course(c) );
        }
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    /*
    public void setCreditHours(int c) {
        if(c < 0)
            c = 0;
        creditHours = c;
    }
    */
    
    public int getCreditHours() {
        //return creditHours;
        int sum = 0;
        
        for(Course c : this.courses) {
            sum = sum + c.getCreditHour();
        }
        
        return sum;
    }
    
    public double getTuitionDue() {
        return this.getRate() * this.getCreditHours();
    }
    
    public abstract double getRate();
    
    public void introduce() {
        System.out.printf("Hi, I'm %s with %d credit hours - $ %.2f\n", this.getName(), this.getCreditHours(), this.getTuitionDue());
        
        for( Course c : this.courses ) {
            System.out.println( c );
        }
    }
    
    public boolean isEnrolled() {
        if( this.getCreditHours() >= 1 )
            return true;
        return false;
        //return this.creditHours > 0;
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
    
    /*
    public Student duplicate() {
        return new Student( this );

        Student newStud = new Student();
        
        //String myName = ;
        //int myCredit = ;
        
        newStud.setName( this.getName() );
        //newStud.setCreditHours( this.getCreditHours() );
        
        return newStud;
        
    }
    */
    
    public void registerCourse(Course c) {
        this.courses.add( c );
    }
    
    public ArrayList<Course> getCourses() {
        return this.courses;
    }
    
}
