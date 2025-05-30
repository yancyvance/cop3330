
public class Person {
    
    private String name;
    private double rate;
    
    public Person(String n, double r) {
        // constructor of Person class that 
        // accepts 2 parameters
        setInformation(n, r);
    }
    
    public Person() {
        // default constructor of Person class
        setInformation(null, 0);
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
    
    public double getSalary(double h) {
        // a computed attribute
        return rate * h;
    }
    
}
