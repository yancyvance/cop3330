
public class Fraction {

    private int numerator;
    private int denominator;
    
    public Fraction() {
        //setNumerator(1);
        //setDenominator(1);
        this(1, 1);
    }
    
    public Fraction(int num, int den) {
        setNumerator(num);
        setDenominator(den);
        simplify();
    }
    
    // setters
    public void setNumerator(int n) {
        this.numerator = n;
        //simplify();
    }
    
    public void setDenominator(int d) {
        if(d == 0)
            d = 1;
        this.denominator = d;
        //simplify();
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
        //return getNumerator() + "/" + getDenominator();
        return String.format("%d/%d", getNumerator(), getDenominator());
    }
    
    public static int findGCD(int a, int b) {
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
        
        this.numerator = newNum;
        this.denominator = newDen;
        //setNumerator(newNum);
        //setDenominator(newDen);
    }
    
    public Fraction duplicate() {
        return null;
    }
    
    public boolean equals(Fraction other) {
        return false;
    }
    
    public int compareTo(Fraction other) {
        
        return 0;
    }
    
}
