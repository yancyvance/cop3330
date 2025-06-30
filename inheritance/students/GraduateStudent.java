
public class GraduateStudent extends Student {
    
    private String thesisTopic;
    
    public GraduateStudent(String name) {
        super(name);
        //this.name = "stranger";
    }
    
    @Override
    public void introduce() {
        System.out.println("Type: Graduate");
        
        super.introduce();
    }
    
    /*
    public void m2() {
        super.introduce();
        
        this.introduce();
        
        introduce();
    }
    */

    
}
