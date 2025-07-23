import java.io.FileNotFoundException;

/**
 * The {@code MainApp} class serves as the entry point to the application.
 * It creates the controller and initializes the user interface.
 */
public class MainApp {
    
    /**
     * @hidden
     * Private constructor to prevent instantiation.
     */
    public MainApp() {
        
    }

    /**
     * The main method of the application.
     * <p>
     * It creates an instance of {@link SimpleController} and launches the 
     * {@link LatestStudentListUI} interface.
     * </p>
     *
     * @param args command-line arguments (not used)
     * @throws FileNotFoundException if an error occurs during file operations
     */
    public static void main(String[] args) {
        // create a controller
        SimpleController controller = new SimpleController();
        
        // load the user interface
        new LatestStudentListUI(controller); 
    }

}
