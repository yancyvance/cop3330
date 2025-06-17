import java.util.ArrayList;

public class Person {
    
    private String name;                    // reference data type
    private double rate;                    // primitive data type
    private MyDate birthday;                // reference data type
    public ArrayList<Fraction> dailyLogs;   // reference data type
    
    public Person() {
        // default constructor of Person class
        // this calls the other constructor
        this("", 0);
    }
    
    public Person(String n, double r) {
        // constructor of Person class that 
        // accepts 2 parameters
        setInformation(n, r);
        
        // make sure to instantiate a reference data type
        // otherwise, it will lead to NullPointerException
        this.dailyLogs = new ArrayList<>();
    }
    
    // this setter allows us to set the birthday field
    // so that we don't have to instantiate it in
    // this class, it will be the user's responsibility
    public void setBirthday(MyDate birth) {
        this.birthday = birth;
    }
    
    // this will potentially cause a NullPointerException
    // if the person doesn't have a birthday information
    public int getAge() {
        // for now, we hard code today's year
        // but there is a way to get the system's date
        return 2025 - this.birthday.getYear();
    }
    
    // this will potentially cause a NullPointerException
    // if this list was not instantiated (created) above
    public void addLog(Fraction f) {
        this.dailyLogs.add( f );
    }
    
    // this will potentially cause a NullPointerException
    // if the list was not instantiated (created) above
    public double computeSalary() {
        Fraction totalFraction = new Fraction(0, 1);
        
        // loop through all the fractions
        for(Fraction tmp : this.dailyLogs) {
            totalFraction = totalFraction.add(tmp);
        }
        
        // convert the total of all fractions to double
        double workRatio = totalFraction.toDecimal();
        
        // compute the base salary
        double baseSalary = workRatio * this.getRate();
        
        // compute adjustments based on age
        double adjustments = 1.0 + ( (double)this.getAge() / 100 );
        
        // return adjusted salary based on the age
        return baseSalary * adjustments;
    }
    
    
    public void setInformation(String n, double r) {
        // a setter method that accepts 2
        // parameters and call their corresponding
        // setter methods instead of directly setting
        //name = n;
        setName(n); 
        //rate = r;
        setRate(r);
    }
    
    // setter methods
    public void setName(String a) {
        name = a;
    }
    
    public void setRate(double rate) {
        // added data validation for rate
        if(rate < 0 )
            rate = 0;
        this.rate = rate;
    }
    
    
    // getter methods
    public String getName() {
        return name;
    }
    
    public double getRate() {
        return rate;
    }
    
    /* 
    // old implementation - removed
    public double getSalary(double h) {
        // a computed attribute
        return rate * h;
    }
    */    

}
