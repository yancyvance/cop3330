
/**
 * The {@code MyDate} class represents a simplified calendar date with
 * basic functionality such as setting and validating dates, duplicating
 * dates, calculating the next day, and comparing dates. 
 * 
 * <p>This class uses Gregorian calendar rules and accounts for leap years.
 * The default date is January 1, 1900. The class provides both instance and
 * static methods to manipulate and retrieve information about dates.</p>
 * 
 * <p>Note: This implementation does not support dates before year 0.</p>
 * 
 * @author COP 3330 Summer 2025
 */
public class MyDate {
    
    // instance variables
    /** The month of the date (1–12) */
    private int month;

    /** The day of the month (1–31 depending on the month and year) */
    private int day;

    /** The year of the date (non-negative) */
    private int year;


    // class variables (shared); also made them constants
    /** Names of the months in order */
    private static final String[] monthNames = {
        "January", "February", "March", "April", "May", "June", 
        "July", "August", "September", "October", "November", "December"
    };

    /** Maximum number of days for each month (non-leap year) */
    private static final int[] maxDays = {
        31, 28, 31, 30, 31, 30, 
        31, 31, 30, 31, 30, 31
    };
    
    
    // constructors
    
    /**
     * Constructs a {@code MyDate} object representing the default date:
     * January 1, 1900.
     */
    public MyDate() {
        // default date as January 1, 1900
        // call the other constructor using
        // this( )
        this(1, 1, 1900);
    }
    
    /**
     * Constructs a {@code MyDate} object with the specified month, day, and year.
     * Invalid values will be corrected to default valid values.
     * 
     * @param m the month (1–12)
     * @param d the day (1–31, depending on month and year)
     * @param y the year (must be non-negative)
     */
    public MyDate(int m, int d, int y) {
        // order here matters due to the data
        // validation logic
        setYear(y);
        setMonth(m);
        setDay(d);
    }
    
    /**
     * Sets the month. Values outside 1–12 will default to 1.
     * 
     * @param m the month to set
     */
    public void setMonth(int m) {
        if(m < 1 || m > 12)
            m = 1;
        month = m;
    }
    
    /**
     * Sets the day. Values outside valid range for the current month and year
     * will default to 1.
     * 
     * @param d the day to set
     */
    public void setDay(int d) {
        int tmp = getMaxDays(year, month);
        if(d < 1 || d > tmp)
            d = 1;
        day = d;
    }
    
    /**
     * Sets the year. Negative values will default to 0.
     * 
     * @param y the year to set
     */
    public void setYear(int y) {
        if(y < 0)
            y = 0;
        year = y;
    }
    
    /**
     * Returns a deep copy of this {@code MyDate} object.
     * 
     * @return a new {@code MyDate} with the same values
     */
    public MyDate duplicate() {
        // deep copy of the current object
        MyDate tmp = new MyDate(month, day, year);
        
        return tmp;
    }
    
    /**
     * Advances this date by one day, modifying the current object.
     */
    public void goToNextDay() {
        MyDate newDate = getNextDay();
        
        month = newDate.month;
        day = newDate.day;
        year = newDate.year;
    }
    
    /**
     * Returns a new {@code MyDate} that is {@code n} days after this date.
     * 
     * @param n the number of days to advance
     * @return a new {@code MyDate} {@code n} days later
     */
    public MyDate getNextDay(int n) {
        MyDate newDate = duplicate();
        for(int i = 0; i < n; i++)
            newDate = newDate.getNextDay();
        return newDate;
    }
    
    /**
     * Returns a new {@code MyDate} that is one day after this date.
     * 
     * @return a new {@code MyDate} for the next day
     */
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
    
    /**
     * Compares this date with another for equality.
     * 
     * @param other the date to compare with
     * @return {@code true} if the dates are equal; {@code false} otherwise
     */
    public boolean equals(MyDate other) {
        return month == other.month && day == other.day && year == other.year;
    }
    
    /**
     * Calculates the difference in days between this date and another.
     * 
     * @param other the date to compare with
     * @return a positive value if this date is after {@code other},
     *         negative if before, and zero if equal
     */
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
    
    /**
     * Returns a string representation of the date in the format
     * "MonthName day, year".
     * 
     * @return the string form of the date
     */
    public String toString() {
        return getMonthName() + " " + day + ", " + year;
    }
    
    /**
     * Returns the full name of the month.
     * 
     * @return the name of the current month
     */
    public String getMonthName() {
        return monthNames[month-1];
    }
    
    /**
     * Returns the number of days in the specified month and year,
     * accounting for leap years.
     * 
     * @param y the year
     * @param m the month
     * @return the maximum number of days in that month
     */
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
    
    /**
     * Determines whether the specified year is a leap year.
     * 
     * @param y the year
     * @return {@code true} if the year is a leap year; {@code false} otherwise
     */
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
    
    /**
     * Calculates the number of days from January 1, year 0 to the given date.
     * 
     * @param date the date to compute the day count for
     * @return the total number of days since year 0
     */
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
