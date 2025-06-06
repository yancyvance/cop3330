
public class Main {

    public static void main(String[] args) {
        String s1 = "1/2";
        String s2 = "2/3";
        
        Fraction f1 = new Fraction(1, 4);
        Fraction f2 = new Fraction(3, 4);
        Fraction res = f1.add(f2);
        //Fraction res = f1.multiply(f2);
        
        System.out.println(f1);
        System.out.println(f2);
        System.out.println(res);
    }

}
