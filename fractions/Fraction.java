
public class Fraction implements Comparable<Fraction> {

    private int numerator;
    private int denominator;
    
    public Fraction() {
        //setNumerator(1);
        //setDenominator(1);
        this(1, 1);
    }
    
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
    private void setNumerator(int n) {
        this.numerator = n;
        //simplify();
    }
    
    public void setDenominator(int d) {
        if(d == 0)
            d = 1;
        this.denominator = d;
        //simplify();
        
        if( this.denominator < 0 ) {
            this.denominator = this.denominator * -1;
            this.setNumerator( this.getNumerator() * -1 );
        }
        
        // new Fraction(1, -4);
    }
    
    // getters
    public int getNumerator() {
        //simplify();
        return this.numerator;
    }
    
    public int getDenominator() {
        //simplify();
        
        return this.denominator;
    }
    
    public String toString() {
        if( this.getDenominator() == 1 )
            return String.format("%d", this.getNumerator());
            
        //return getNumerator() + "/" + getDenominator();
        return String.format("%d/%d", getNumerator(), getDenominator());
    }
    
    public static int findGCD(int a, int b) {
        a = Math.abs(a);
        b = Math.abs(b);
        
        if(a == 0)
            return b;
            
        return findGCD(b % a, a);
    }
    
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
    
    public void simplify() {
        int gcd = findGCD(this.numerator, this.denominator);
        
        int newNum = this.numerator / gcd;
        int newDen = this.denominator / gcd;
        
        //this.numerator = newNum;
        //this.denominator = newDen;
        setNumerator(newNum);
        setDenominator(newDen);
    }
    
    public Fraction duplicate() {
        //Fraction newFrac = new Fraction();
        //newFrac.setNumerator( this.getNumerator() );
        //newFrac.numerator = this.numerator;
        //newFrac.denominator = this.denominator;
        //return newFrac;
        //return null;
        return new Fraction(this.getNumerator(), this.getDenominator());
    }
    
    public boolean equals(Fraction other) {
        return this.numerator*other.denominator == this.denominator*other.numerator;
        
        //return false;
    }
    
    public int compareTo(Fraction other) {
        int left = this.numerator*other.denominator;
        int right = this.denominator*other.numerator;
        
        if(left < right)
            return -1;
        else if(left > right)
            return 1;        
        return 0;
    }
    
    public Fraction multiply(Fraction other) {
        int newNum = this.getNumerator() * other.getNumerator();
        int newDen = this.getDenominator() * other.getDenominator();
        
        //Fraction result = new Fraction();
        return new Fraction(newNum, newDen);
    }
    
    public Fraction getInverse() {
        return new Fraction(this.getDenominator(), this.getNumerator());
    }
    
    public Fraction divide(Fraction other) {
        return this.multiply( other.getInverse() );
    }
    
    public Fraction multiply(int num) {
        return this.multiply( new Fraction(num, 1) );
    }
    
    public Fraction subtract(Fraction other) {
        //other.setNumerator( other.getNumerator() * -1 );
        
        return this.add( other.multiply(-1) );
    }
    
    public double toDecimal() {
        return (double)this.numerator / this.denominator;
    }
    
    public boolean isNegative() {
        return this.getNumerator() < 0;
    }
    
    public void updateNumerator(int num) {
        this.setNumerator(num);
        this.simplify();
    }
    
    public void updateDenominator(int den) {
        this.setDenominator(den);
        this.simplify();
    }
    
}
