

public class GraduateStudent extends Student {
    
    private String thesisTopic;
    
    private static final double TUITION_RATE = 288.16;
    
    public GraduateStudent() {
        
    }
    
    public GraduateStudent(String name) {
        //this.setName(name);
        //super.setName(name);
        super(name);
    }
    
    public void setThesisTopic(String t) {
        this.thesisTopic = t;
    }
    
    public String getThesisTopic() {
        return this.thesisTopic;
    }
    
    @Override
    public void introduce() {
        System.out.println("Type: Graduate");
        
        super.introduce();
        
        /*
        System.out.printf("Hi, I'm %s with %d credit hours - $ %.2f\n", this.getName(), this.getCreditHours(), this.getTuitionDue());
        
        for( Course c : this.getCourses() ) {
            System.out.println( c );
        }
        */
    }
    
    /*
    @Override
    public double getTuitionDue() {
        return TUITION_RATE * this.getCreditHours();
    }
    */
    
    @Override
    public double getRate() {
        return TUITION_RATE;
    }
    
}
