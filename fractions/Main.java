
public class Main {

    public static void main(String[] args) {
        String s1 = "1/2";
        String s2 = "2/3";
        
        Fraction f1 = new Fraction(1, 4);
        Fraction f2 = new Fraction(2, 4);
        Fraction f3 = f1.add(f2);
        
        System.out.println(f1);
        System.out.println(f2);
        System.out.println(f3);
                
        System.out.println(f1);
        //System.out.println(f1.toString());
        System.out.println(f3);
        System.out.println(f1);
        Fraction res = f1.add(f2);
        System.out.println(res);
        System.out.println(f1);       
        
    }

}
