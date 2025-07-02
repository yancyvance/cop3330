
public class GraduateStudent extends Student {
    
    private String thesisTopic;
    
    public static final double TUITION_RATE = 369.95;
    
    public GraduateStudent(String name) {
        // call the parent's constructor
        // and pass the name
        super(name);
    }
    
    public GraduateStudent(GraduateStudent ob) {
        super(ob);
        
        this.thesisTopic = ob.thesisTopic;
    }
    
    @Override
    public void introduce() {
        System.out.println("Type: Graduate");
        
        // call and reuse the parent's version
        super.introduce();
    }
    
    public String getThesisTopic() {
        return this.thesisTopic;
    }
    
    public void setThesisTopic(String thesisTopic) {
        this.thesisTopic = thesisTopic;
    }
    
    /*
    @Override
    public double getTuitionDue() {
        return this.getTotalCredit() * 369.95;
    }
    */
    
    @Override
    public double getRate() {
        //return 369.95;
        return TUITION_RATE;
    }
    
}
