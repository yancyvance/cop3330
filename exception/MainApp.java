import java.io.FileNotFoundException;

public class MainApp {
    
    public static void main(String[] args) throws FileNotFoundException {
        // create a controller
        SimpleController controller = new SimpleController();
        
        // load the user interface
        new StudentListUI(controller); 
    }

}
