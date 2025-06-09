import java.util.Collections;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        String s1 = "1/2";
        String s2 = "2/3";
        
        Fraction f1 = new Fraction(4, -4);
        Fraction f2 = new Fraction(3, 4);
        Fraction f3 = new Fraction(2, 3);
        Fraction res = f1.add(f2);
        
        System.out.println(f1);
        System.out.println(f2);
        System.out.println(res);
        
        ArrayList<Fraction> list = new ArrayList<Fraction>();
        
        list.add(f1);
        list.add(f2);
        list.add(f3);
        
        System.out.println(list);
        Collections.sort(list);
        System.out.println(list);
    }

}
