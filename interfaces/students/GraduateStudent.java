
/**
 * The {@code GraduateStudent} class represents a student enrolled in a graduate program.
 * It extends the {@code Student} class and adds specific information such as thesis topic.
 */
public class GraduateStudent extends Student {
    
    /** The thesis topic of the graduate student. */
    private String thesisTopic;

    /** The tuition rate per credit hour for graduate students. */
    public static final double TUITION_RATE = 369.95;
    
    
    /**
     * Constructs a new {@code GraduateStudent} with the specified name.
     *
     * @param name the name of the student
     */
    public GraduateStudent(String name) {
        // call the parent's constructor
        // and pass the name
        super(name);
    }
    
    /**
     * Copy constructor. Creates a deep copy of the given {@code GraduateStudent}.
     *
     * @param ob the graduate student to copy
     */
    public GraduateStudent(GraduateStudent ob) {
        super(ob);
        
        this.thesisTopic = ob.thesisTopic;
    }
    
    /**
     * Prints the student's type as "Graduate" and then calls the parent class's introduce method.
     */
    @Override
    public void introduce() {
        System.out.println("Type: Graduate");
        
        // call and reuse the parent's version
        super.introduce();
    }
    
    /**
     * Returns the thesis topic of the graduate student.
     *
     * @return the thesis topic
     */
    public String getThesisTopic() {
        return this.thesisTopic;
    }
    
    /**
     * Sets the thesis topic of the graduate student.
     *
     * @param thesisTopic the new thesis topic to assign
     */
    public void setThesisTopic(String thesisTopic) {
        this.thesisTopic = thesisTopic;
    }
    
    /*
    @Override
    public double getTuitionDue() {
        return this.getTotalCredit() * 369.95;
    }
    */
    
    /**
     * Returns the tuition rate per credit hour for graduate students.
     *
     * @return the tuition rate
     */
    @Override
    public double getRate() {
        //return 369.95;
        return TUITION_RATE;
    }
    
    /**
     * Returns a formatted string containing the student's name and thesis topic.
     *
     * @return a string with student information
     */
    @Override
    public String getInfo() {
        return this.getName() + " Thesis Topic is " + this.thesisTopic;
    }
    
    /**
     * Returns the status of the student as "Graduate".
     *
     * @return the student's status
     */
    @Override
    public String getStatus() {
        return "Graduate";
    }
    
    /**
     * Creates and returns a duplicate of this graduate student.
     * This implementation uses the copy constructor to create a new instance
     * with the same data as the current object.
     *
     * @return a copy of this GraduateStudent
     */
    @Override
    public GraduateStudent duplicate() {
        GraduateStudent s = new GraduateStudent(this);
        
        return s;
    }
    
}
