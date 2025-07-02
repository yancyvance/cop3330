
public class UndergraduateStudent extends Student {
    
    private int yearLevel;
    
    public UndergraduateStudent(String name) {
        // call the parent's constructor
        // and pass the name
        super(name);
    }
    
    @Override
    public void introduce() {
        System.out.println("Type: Undergraduate");
        
        // call and reuse the parent's version
        super.introduce();
    }
    
    public int getYearLevel() {
        return this.yearLevel;
    }
    
    public void setYearLevel(int yearLevel) {
        this.yearLevel = yearLevel;
    }

}

