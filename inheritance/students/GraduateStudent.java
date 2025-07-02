
public class GraduateStudent extends Student {
    
    private String thesisTopic;
    
    public GraduateStudent(String name) {
        // call the parent's constructor
        // and pass the name
        super(name);
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

}
