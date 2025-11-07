import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;

public class Main {
    
    public static void main(String[] args) throws FileNotFoundException {
        University u = University.loadFromFile("data2.txt");
        
        u.printStudentData();
    }
    
}
        
        
