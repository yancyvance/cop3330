import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;

public class Main {
    
    public static void main(String[] args) throws FileNotFoundException {
        /*University u = University.loadFromFile("data2.txt");
        
        u.printStudentData();
        */
        
        /*
        ArrayList<Course> courses = new ArrayList<>();
        
        courses.add( new Course("COP3502", "CS1", 3) );
        courses.add( new Course("COP3330", "OOP", 3) );
        courses.add( new Course("COP3223", "C", 2) );
        
        for(int i = 0; i < courses.size(); i++)
            System.out.println( courses.get(i) );
            
        Collections.sort( courses );
        System.out.println();
        
        for(int i = 0; i < courses.size(); i++)
            System.out.println( courses.get(i) );
        */
        Scanner input = new Scanner(System.in);
        
        Searchable[] obs = new Searchable[5];
        
        obs[0] = new Course("COP3330", "OOP", 3);
        obs[1] = new Course("COP3223", "C", 2);
        obs[2] = new GraduateStudent("John Doe");
        obs[3] = new UndergraduateStudent("Jane Smith");
        obs[4] = new UndergraduateStudent("Bob");
        
        System.out.print("Enter Keyword: ");
        String k = input.nextLine();
        
        for(int i = 0; i < obs.length; i++) {
            if( obs[i].matches( k ) ) {
                System.out.printf("%s: %s\n", obs[i].getType(), obs[i] );
            }
        }
        
    }
    
}
        
        
