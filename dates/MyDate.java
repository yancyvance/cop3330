
public class MyDate {
    
    // instance variables
    private int month, day, year;
    
    // class variables (shared) and constants
    private static final String[] names = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    private static final int[] maxDays = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    
    // constructors
    public MyDate() {
        // default date as January 1, 1900
        // call the other constructor using
        // this( )
        this(1, 1, 1900);
    }
    
    public MyDate(int m, int d, int y) {
        // order here matters due to the data
        // validation logic
        setYear(y);
        setMonth(m);
        setDay(d);
    }
    
    public void setMonth(int m) {
        if(m < 1 || m > 12)
            m = 1;
        month = m;
    }
    
    public void setDay(int d) {
        int tmp = getMaxDays(year, month);
        if(d < 1 || d > tmp)
            d = 1;
        day = d;
    }
    
    public void setYear(int y) {
        if(y < 0)
            y = 0;
        year = y;
    }
    
    public MyDate duplicate() {
        // deep copy of the current object
        MyDate tmp = new MyDate(month, day, year);
        
        return tmp;
    }
    
    public void goToNextDay() {
        MyDate newDate = getNextDay();
        
        month = newDate.month;
        day = newDate.day;
        year = newDate.year;
    }
    
    public MyDate getNextDay(int n) {
        MyDate newDate = duplicate();
        for(int i = 0; i < n; i++)
            newDate = newDate.getNextDay();
        return newDate;
    }
    
    public MyDate getNextDay() {
        MyDate newDate = duplicate();
        
        newDate.day = newDate.day + 1;
        
        // check if beyond
        if(newDate.day > getMaxDays(newDate.year, newDate.month)) {
            // reset day to 1
            newDate.day = 1;
            // then increment the month
            newDate.month = newDate.month + 1;
            
            if(newDate.month > 12) {
                // reset month to 1
                newDate.month = 1;
                // then increment year
                newDate.year = newDate.year + 1;
            }
        }
        
        return newDate;
    }
    
    public boolean equals(MyDate other) {
        return month == other.month && day == other.day && year == other.year;
    }
    
    public int getDifference(MyDate other) {
        int daysCurrent = getDaysSinceStart(this);
        int daysOther = getDaysSinceStart(other);
        
        int diff = daysCurrent - daysOther;
        // we want to retain the sign because it gives us more information
        // if diff == 0, then the two dates are the same
        // if diff < 0, then the current date comes before the other date
        // if diff > 0, then the current date comes after the other date
        return diff;
    }    
    
    public String toString() {
        return getMonthName() + " " + day + ", " + year;
    }
    
    public String getMonthName() {
        return names[month-1];
    }
    
    public static int getMaxDays(int y, int m) {
        // helper method, so made it static
        // 28 or 29 - 2
        // 30 - 4; 6; 9; 11
        // 31 - otherwise
        if(m == 2)
            // check if it is a leap year
            if( isLeapYear(y) )
                return maxDays[m-1] + 1;
                
        return maxDays[m-1];
    }
    
    public static boolean isLeapYear(int y) {
        // helper method, so made it static
        return ( (y % 400 == 0) || (y % 4 == 0 && y % 100 != 0) );
        /*
        if(y % 400 == 0)
            return true;
        
        if(y % 4 == 0 && y % 100 != 0)
            return true;
            
        return false;
        */
    }
    
    public static int getDaysSinceStart(MyDate date) {
        // helper method, so made it static
        int numDays = 0;
        
        for(int i = 1; i < date.year; i++) {
            // check if leap year
            if(isLeapYear(i))
                numDays = numDays + 366;
            else
                numDays = numDays + 365;
        }
        
        for(int i = 1; i < date.month; i++) {
            numDays = numDays + getMaxDays(date.year, i);
        }
        
        numDays = numDays + date.day;
        
        return numDays;
    }
    
}
