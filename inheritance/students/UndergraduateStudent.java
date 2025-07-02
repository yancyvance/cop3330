
public class UndergraduateStudent extends Student {
    
    private int yearLevel;
    
    public static final double TUITION_RATE = 212.28;
    
    public UndergraduateStudent(String name) {
        // call the parent's constructor
        // and pass the name
        super(name);
    }
    
    public UndergraduateStudent(UndergraduateStudent ob) {
        super(ob);
        
        this.yearLevel = ob.yearLevel;
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
    
    /*
    @Override
    public double getTuitionDue() {
        return this.getTotalCredit() * 212.28;
    }
    */
    
    @Override
    public double getRate() {
        //return 212.28;
        return TUITION_RATE;
    }

}


