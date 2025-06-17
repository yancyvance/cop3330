import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;

// Make sure Person.java, Fraction.java and MyDate.java are located
// under the same directory where this Main.java is located.
// As an alternative, you can simply just ensure that there is
// a Fraction.class and MyDate.class instead of their .java counterpart.
// In this case, you only need to compile Person.java then Main.java.

public class Main {

    public static void main(String[] args) {
        // create a person object
        Person p1 = new Person("John Doe", 120);
        
        // prepare the birthday information and set
        p1.setBirthday( new MyDate(6, 12, 2000) );
        
        // add the log information
        //System.out.println(p1.dailyLogs);
        p1.addLog( new Fraction(7, 8) );
        p1.addLog( new Fraction(4, 8) );
        p1.addLog( new Fraction(8, 8) );
        //System.out.println(p1.dailyLogs);
        
        // compute the salary
        System.out.printf("%s\n%.2f\n", p1.getName(), p1.computeSalary());
        
        
        // create a person object
        Person p2 = new Person("Jane Smith", 120);
        
        // prepare the birthday information and set
        p2.setBirthday( new MyDate(6, 12, 2001) );
        
        // add the log information
        //System.out.println(p2.dailyLogs);
        p2.addLog( new Fraction(7, 8) );
        p2.addLog( new Fraction(4, 8) );
        p2.addLog( new Fraction(8, 8) );
        //System.out.println(p2.dailyLogs);
        
        // compute the salary
        System.out.printf("%s\n%.2f\n", p2.getName(), p2.computeSalary());
    }

}
