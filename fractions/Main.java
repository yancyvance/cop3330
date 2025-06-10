import java.util.Arrays;

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
        
        Fraction[] fracs = new Fraction[5];
        
        fracs[0] = new Fraction(3, 6);
        fracs[1] = new Fraction(-7, 4);
        fracs[2] = new Fraction(2, 3);
        fracs[3] = new Fraction(-10, 5);
        fracs[4] = new Fraction(5, 2);
        
        // print the array
        printFractions(fracs);
        
        // call built-in sort method
        Arrays.sort(fracs);
        
        // print again
        printFractions(fracs); 
    }
    
    public static void printFractions(Fraction[] fracs) {
        for(int i = 0; i < fracs.length; i++)
            System.out.print(fracs[i] + " ");
        System.out.println();
    }

}
