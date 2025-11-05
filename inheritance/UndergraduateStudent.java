

public class UndergraduateStudent extends Student {
    
    private int yearLevel;
    
    private static final double TUITION_RATE = 288.16;
    
    public UndergraduateStudent() {
        
    }
    
    public UndergraduateStudent(String name) {
        super(name);
    }
    
    public void setYearLevel(int y) {
        this.yearLevel = y;
    }
    
    public int getYearLevel() {
        return this.yearLevel;
    }
    
    @Override
    public void introduce() {
        System.out.println("Type: Undergraduate");
        
        super.introduce();
    }
    
    @Override
    public double getTuitionDue() {
        return TUITION_RATE * this.getCreditHours();
    }
    
}

