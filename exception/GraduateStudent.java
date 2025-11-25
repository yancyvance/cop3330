

public class GraduateStudent extends Student {
    
    private String thesisTopic;
    
    private static final double TUITION_RATE = 288.16;
    
    public GraduateStudent() {
        // this implicitly calls the parent's no-arg constructor
        // super();
    }
    
    public GraduateStudent(String name) {
        // call the parent's constructor
        super(name);
    }
    
    public GraduateStudent(GraduateStudent ob) {
        // call the parent's copy constructor
        super(ob);
        
        // copy the child specific fields
        this.setThesisTopic( ob.getThesisTopic() );
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
        
        // added outside of the lecture
        System.out.println("Thesis Topic: " + this.getThesisTopic());
    }
    
    @Override
    public double getRate() {
        return TUITION_RATE;
    }
    
    @Override
    public GraduateStudent duplicate() {
        // call the copy constructor
        return new GraduateStudent(this);
    }
    
}
