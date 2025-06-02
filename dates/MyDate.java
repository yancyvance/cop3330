
public class MyDate {
    
    // fields
    private int month, day, year;
    
    // constructors
    public MyDate(int m, int d, int y) {
        setYear(y);
        setMonth(m);
        setDay(d);
    }
    
    // setters
    public void setMonth(int m) {
        if(m < 1 || m > 12)
            m = 1;
        month = m;
    }
    
    public void setDay(int m) {
        day = m;
    }
    
    public void setYear(int m) {
        if(m < 0)
            m = 0;
        year = m;
    }
    
    
}
