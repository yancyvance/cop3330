
/**
 * The {@code Fraction} class represents a rational number using a numerator and a denominator.
 * <p>
 * It supports arithmetic operations (addition, subtraction, multiplication, division),
 * comparison, simplification, and conversion to both string and decimal representations.
 * <p>
 * The class implements {@link Comparable} to allow for sorting and ordering of fractions.
 * <p>
 * Note: This class does not handle exceptions for invalid input (e.g., zero denominator explicitly),
 * but defaults the denominator to 1 if zero is provided.
 *
 * Example usage:
 * <pre>
 *     Fraction a = new Fraction(1, 2);
 *     Fraction b = new Fraction(3, 4);
 *     Fraction c = a.add(b);  // c is 5/4
 *     System.out.println(c); // prints "5/4"
 * </pre>
 *
 * @author COP 3330 Summer 2025
 */
public class Fraction implements Comparable<Fraction> {

    /**
     * The numerator of the fraction. Can be negative or zero.
     */
    private int numerator;

    /**
     * The denominator of the fraction. Always kept positive and never zero.
     */
    private int denominator;
    
    
    // constructors
    /**
     * Constructs a default Fraction with value 1/1.
     */
    public Fraction() {
        //setNumerator(1);
        //setDenominator(1);
        this(1, 1);
    }
    
    /**
     * Constructs a Fraction with the given numerator and denominator.
     * Simplifies the fraction automatically.
     *
     * @param num the numerator
     * @param den the denominator (if zero, will be reset to 1)
     */
    public Fraction(int num, int den) {
        //System.out.println("Accepted: " + num + " " + den);
        setNumerator(num);
        setDenominator(den);
        //System.out.println("Values Set: " + this.numerator + " " + this.denominator);
        simplify();
        
        /*
        if(this.getDenominator() < 0) {
            this.setDenominator(-1 * this.getDenominator());
            this.setNumerator(this.getNumerator() * -1);
        }
        */
    }
    
    // setters
    /**
     * Sets the numerator of this fraction.
     *
     * @param n the new numerator
     */
    private void setNumerator(int n) {
        this.numerator = n;
        //simplify();
    }
    
    /**
     * Sets the denominator of this fraction.
     * Ensures the denominator is always positive.
     * If the denominator is zero, it is set to 1.
     *
     * @param d the new denominator
     */
    public void setDenominator(int d) {
        if(d == 0)
            d = 1;
        this.denominator = d;
        //simplify();
        
        if( this.denominator < 0 ) {
            this.denominator = this.denominator * -1;
            this.setNumerator( this.getNumerator() * -1 );
        }
    }
    
    // getters
    
    /**
     * Returns the numerator of this fraction.
     *
     * @return the numerator
     */
    public int getNumerator() {
        //simplify();
        return this.numerator;
    }
    
    /**
     * Returns the denominator of this fraction.
     *
     * @return the denominator
     */
    public int getDenominator() {
        //simplify();
        return this.denominator;
    }
    
    /**
     * Returns a string representation of the fraction.
     * If the denominator is 1, returns just the numerator (as an integer).
     * Otherwise, returns the fraction in the form "numerator/denominator".
     *
     * @return a string representation of the fraction
     */
    public String toString() {
        if( this.getDenominator() == 1 )
            return String.format("%d", this.getNumerator());
            
        //return getNumerator() + "/" + getDenominator();
        return String.format("%d/%d", getNumerator(), getDenominator());
    }
    
    /**
     * Computes the greatest common divisor (GCD) of two integers using the
     * Euclidean algorithm. This method always returns a non-negative result.
     * <p>
     * If either or both input integers are negative, their absolute values
     * are used in the computation. The GCD of 0 and any non-zero integer is
     * the absolute value of the non-zero integer. The GCD of 0 and 0 is defined
     * here as 0.
     *
     * @param a the first integer (can be negative)
     * @param b the second integer (can be negative)
     * @return the greatest common divisor of {@code a} and {@code b}, always non-negative
     */
    public static int findGCD(int a, int b) {
        a = Math.abs(a);
        b = Math.abs(b);
        
        if(a == 0)
            return b;
            
        return findGCD(b % a, a);
    }
    
    /**
     * Simplifies this fraction to its lowest terms.
     */
    public void simplify() {
        int gcd = findGCD(this.numerator, this.denominator);
        
        int newNum = this.numerator / gcd;
        int newDen = this.denominator / gcd;
        
        //this.numerator = newNum;
        //this.denominator = newDen;
        setNumerator(newNum);
        setDenominator(newDen);
    }
    
    /**
     * Returns a new {@code Fraction} that is a copy of this one.
     *
     * @return a duplicate of this fraction
     */
    public Fraction duplicate() {
        //Fraction newFrac = new Fraction();
        //newFrac.setNumerator( this.getNumerator() );
        //newFrac.numerator = this.numerator;
        //newFrac.denominator = this.denominator;
        //return newFrac;
        //return null;
        return new Fraction(this.getNumerator(), this.getDenominator());
    }
    
    /**
     * Compares this fraction with another for equality (same reduced form).
     *
     * @param other the fraction to compare with
     * @return true if the fractions are equal, false otherwise
     */
    public boolean equals(Fraction other) {
        return this.numerator*other.denominator == this.denominator*other.numerator;
        
        //return false;
    }
    
    /**
     * Compares two fractions. The result is zero if the fractions are equal.
     * It returns a value less than 0 if this fraction is less than the fraction argument.
     * It returns a value greater than 0 if this fraction is greater than the fraction argument.
     *
     * @param other the fraction to compare to
     * @return the value 0 if the argument fraction is equal to this fraction; 
     * a value less than 0 if this fraction is less than the fraction argument; 
     * and a value greater than 0 if this fraction is greater than the fraction argument
     */
    public int compareTo(Fraction other) {
        int left = this.numerator*other.denominator;
        int right = this.denominator*other.numerator;
        
        if(left < right)
            return -1;
        else if(left > right)
            return 1;        
        return 0;
    }
    
    /**
     * Adds this fraction to another and returns the result.
     *
     * @param other the fraction to add
     * @return a new {@code Fraction} representing the sum
     */
    public Fraction add(Fraction other) {
        //return null;
        //Fraction result = new Fraction();
        
        int numThis = this.numerator;
        int denThis = this.denominator;
        
        int numOther = other.numerator;
        int denOther = other.denominator;
        
        int newDen = denThis * denOther;
        //int newNum = (newDen/denThis*numThis) + (newDen/denOther*numOther) ;
        int newNum = (denOther*numThis) + (denThis*numOther) ;
        
        return new Fraction(newNum, newDen);
    }
    
    /**
     * Multiplies this fraction by another and returns the result.
     *
     * @param other the fraction to multiply with
     * @return a new {@code Fraction} representing the product
     */
    public Fraction multiply(Fraction other) {
        int newNum = this.getNumerator() * other.getNumerator();
        int newDen = this.getDenominator() * other.getDenominator();
        
        //Fraction result = new Fraction();
        return new Fraction(newNum, newDen);
    }
    
    /**
     * Returns the multiplicative inverse of this fraction.
     *
     * @return a new {@code Fraction} that is the inverse
     */
    public Fraction getInverse() {
        return new Fraction(this.getDenominator(), this.getNumerator());
    }
    
    /**
     * Divides this fraction by another and returns the result.
     *
     * @param other the divisor fraction
     * @return a new {@code Fraction} representing the quotient
     */
    public Fraction divide(Fraction other) {
        return this.multiply( other.getInverse() );
    }
    
    /**
     * Multiplies this fraction by an integer and returns the result.
     *
     * @param num the integer to multiply with
     * @return a new {@code Fraction} representing the product
     */
    public Fraction multiply(int num) {
        return this.multiply( new Fraction(num, 1) );
    }
    
    /**
     * Subtracts another fraction from this fraction and returns the result.
     *
     * @param other the fraction to subtract
     * @return a new {@code Fraction} representing the difference
     */
    public Fraction subtract(Fraction other) {
        //other.setNumerator( other.getNumerator() * -1 );
        
        return this.add( other.multiply(-1) );
    }
    
    /**
     * Returns the decimal (floating-point) representation of the fraction.
     *
     * @return the decimal value
     */
    public double toDecimal() {
        return (double)this.numerator / this.denominator;
    }
    
    /**
     * Determines whether the fraction is negative.
     *
     * @return {@code true} if the fraction is negative, {@code false} otherwise
     */
    public boolean isNegative() {
        return this.getNumerator() < 0;
    }
    
    /**
     * Updates the numerator and simplifies the fraction.
     *
     * @param num the new numerator
     */
    public void updateNumerator(int num) {
        this.setNumerator(num);
        this.simplify();
    }
    
    /**
     * Updates the denominator and simplifies the fraction.
     *
     * @param den the new denominator
     */
    public void updateDenominator(int den) {
        this.setDenominator(den);
        this.simplify();
    }
    
}
