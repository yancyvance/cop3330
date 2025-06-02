
public class MyDate {
    
    // fields
    private int month, day, year;
    
    // constructors
    public MyDate(int m, int d, int y) {
        // call the corresponding setters
        // in this order due to the validation
        // logic (i.e., leap year)
        setYear(y);
        setMonth(m);
        setDay(d);
    }
    
    // setters
    public void setMonth(int m) {
        // valid months: 1-12, inclusive
        if(m < 1 || m > 12)
            m = 1;
        month = m;
    }
    
    public void setDay(int d) {
        // valid days: depends on month & year
        day = d;
    }
    
    public void setYear(int y) {
        // valid years: 0+
        if(y < 0)
            y = 0;
        year = y;
    }
    
}
