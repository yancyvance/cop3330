
/**
 * The {@code UndergraduateStudent} class represents a student enrolled in an undergraduate program.
 * It extends the {@code Student} class and adds information specific to undergraduates, such as year level.
 */
public class UndergraduateStudent extends Student {
    
    /** The year level of the undergraduate student (e.g., 1 for freshman, 2 for sophomore, etc.). */
    private int yearLevel;
    
    /** The tuition rate per credit hour for undergraduate students. */
    public static final double TUITION_RATE = 212.28;
    
    
    /**
     * Constructs a new {@code UndergraduateStudent} with the specified name.
     *
     * @param name the name of the student
     */
    public UndergraduateStudent(String name) {
        // call the parent's constructor
        // and pass the name
        super(name);
    }
    
    /**
     * Copy constructor. Creates a deep copy of the given {@code UndergraduateStudent}.
     *
     * @param ob the undergraduate student to copy
     */
    public UndergraduateStudent(UndergraduateStudent ob) {
        super(ob);
        
        this.yearLevel = ob.yearLevel;
    }
    
    /**
     * Prints the student's type as "Undergraduate" and then calls the parent class's introduce method.
     */
    @Override
    public void introduce() {
        System.out.println("Type: Undergraduate");
        
        // call and reuse the parent's version
        super.introduce();
    }
    
    /**
     * Returns the year level of the undergraduate student.
     *
     * @return the year level
     */
    public int getYearLevel() {
        return this.yearLevel;
    }
    
    /**
     * Sets the year level of the undergraduate student.
     *
     * @param yearLevel the year level to set
     */
    public void setYearLevel(int yearLevel) {
        this.yearLevel = yearLevel;
    }
    
    /*
    @Override
    public double getTuitionDue() {
        return this.getTotalCredit() * 212.28;
    }
    */
    
    /**
     * Returns the tuition rate per credit hour for undergraduate students.
     *
     * @return the tuition rate
     */
    @Override
    public double getRate() {
        //return 212.28;
        return TUITION_RATE;
    }
    
    /**
     * Returns a formatted string containing the student's name and year level.
     *
     * @return a string with student information
     */
    @Override
    public String getInfo() {
        return this.getName() + " Year Level is " + Integer.toString( this.yearLevel );
    }
    
    /**
     * Returns the status of the student as "Undergraduate".
     *
     * @return the student's status
     */
    @Override
    public String getStatus() {
        return "Undergraduate";
    }
    
    /**
     * Creates and returns a duplicate of this undergraduate student.
     * This implementation uses the copy constructor to create a new instance
     * with the same data as the current object.
     *
     * @return a copy of this UndergraduateStudent
     */
    @Override
    public UndergraduateStudent duplicate() {
        UndergraduateStudent s = new UndergraduateStudent(this);
        
        return s;
    }

}

