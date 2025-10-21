import java.io.FileNotFoundException;

public class Main {
    
    public static void main(String[] args) throws FileNotFoundException {
        University u = University.loadFromFile("data.txt");
        
        u.printStudentData();
    }
    
}
        
        
