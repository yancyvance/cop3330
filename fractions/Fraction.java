
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
    }
    
    public void setDenominator(int d) {
        if(d == 0)
            d = 1;
        this.denominator = d;
    }
    
    // getters
    public int getNumerator() {
        
        return 0;
    }
    
    public int getDenominator() {
        return 0;
    }
    
    public String toString() {
        //return this.numerator + "/" + this.denominator;
        return String.format("%d/%d", this.numerator, this.denominator);
    }
    
    public static int findGCD(int a, int b) {
        if(a == 0)
            return b;
        
        return findGCD(b % a, a);
    }
    
    public Fraction add(Fraction other) {
        //Fraction result = new Fraction();
        
        int resNum = this.numerator*other.denominator + this.denominator*other.numerator;
        int resDen = this.denominator*other.denominator;
        
        Fraction result = new Fraction(resNum, resDen);
        //result.simplify();
        
        return result;        
    }
    
    public Fraction multiply(Fraction other) {
        int resNum = this.numerator * other.numerator;
        int resDen = this.denominator * other.denominator;
        
        return new Fraction(resNum, resDen);
    }
    
    public void simplify() {
        int gcd = findGCD(this.numerator, this.denominator);
        
        int newNum = this.numerator / gcd;
        int newDen = this.denominator / gcd;
        
        this.numerator = newNum;
        this.denominator = newDen;
        
    }
    
    public Fraction duplicate() {
        return new Fraction(this.numerator, this.denominator);
    }
    
    public boolean equals(Fraction other) {
        return this.numerator*other.denominator == this.denominator*other.numerator;
    }
    
    public int compareTo(Fraction other) {
        return 0;
    }
    
    public Fraction getInverse() {
        // potential issue: 0/1 becomes 0/1
        // so, if that happens, we correct it to become 1/1
        //Fraction invFr = new Fraction(this.denominator, this.numerator);
        return new Fraction(this.denominator, this.numerator);
        
        //return invFr;
    }
    
    public double toDecimal() {
        return (double) this.numerator / this.denominator;
    }
}
