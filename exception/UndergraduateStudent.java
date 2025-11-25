

public class UndergraduateStudent extends Student {
    
    private int yearLevel;
    
    private static final double TUITION_RATE = 105.07;
    
    public UndergraduateStudent() {
        // this implicitly calls the parent's no-arg constructor
        // super();
    }
    
    public UndergraduateStudent(String name) {
        // call the parent's constructor
        super(name);
    }
    
    public UndergraduateStudent(UndergraduateStudent ob) {
        // call the parent's copy constructor
        super(ob);
        
        // copy the child specific fields
        this.setYearLevel( ob.getYearLevel() );
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
        
        // added outside of the lecture
        System.out.println("Year Level: " + this.getYearLevel());
    }
    
    @Override
    public double getRate() {
        return TUITION_RATE;
    }
    
    @Override
    public UndergraduateStudent duplicate() {
        // call the copy constructor
        return new UndergraduateStudent(this);
    }
    
}

