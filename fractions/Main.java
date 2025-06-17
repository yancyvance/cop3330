import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;

public class Main {

    public static void main(String[] args) {
        String s1 = "1/2";
        String s2 = "2/3";
        
        Fraction f1 = new Fraction(4, -4);
        Fraction f2 = new Fraction(3, 4);
        Fraction f3 = f1.subtract(f2);
        
        System.out.println(f1); // -1
        System.out.println(f2); // 3/4
        System.out.println(f3); // -7/4
        
        Fraction[] fracs = new Fraction[5];
        
        fracs[0] = new Fraction(3, 6);
        fracs[1] = new Fraction(-7, 4);
        fracs[2] = new Fraction(2, 3);
        fracs[3] = new Fraction(-10, 5);
        fracs[4] = new Fraction(5, 2);
        
        System.out.println("Demo: Arrays");
        
        // print the array
        printFractions(fracs); // 1/2 -7/4 2/3 -2 5/2
        
        // call built-in sort method
        Arrays.sort(fracs);
        
        // print again
        printFractions(fracs); // -2 -7/4 1/2 2/3 5/2
        
        
        System.out.println("Demo: ArrayList");
        
        // using Collections
        ArrayList<Fraction> fracsList = new ArrayList<>();
        
        fracsList.add( new Fraction(3, 6) );
        fracsList.add( new Fraction(-7, 4) );
        fracsList.add( new Fraction(2, 3) );
        fracsList.add( new Fraction(-10, 5) );
        fracsList.add( new Fraction(5, 2) );
        
        // print list
        printFractions(fracsList);
        
        Collections.sort(fracsList);
        
        // print again
        printFractions(fracsList);
        
    }
    
    // method overload
    public static void printFractions(Fraction[] fracs) {
        for(int i = 0; i < fracs.length; i++)
            System.out.print(fracs[i] + " ");
        System.out.println();
    }
    
    // method overload
    public static void printFractions(ArrayList<Fraction> fracs) {
        for(int i = 0; i < fracs.size(); i++)
            System.out.print(fracs.get(i) + " ");
        System.out.println();
    }

}
