import javax.swing.SwingUtilities;
import java.io.FileNotFoundException;

/**
 * The {@code MainApp} class serves as the entry point for the
 * Student Information System application. It loads student and course
 * data from a file and launches the graphical user interface.
 */
public class MainApp {
    
    /** The name of the input data file containing student and course information */
    private static final String FILE_NAME = "input_updated.txt";

    /**
     * The main method that initializes the Controller, loads data,
     * and starts the UI on the Event Dispatch Thread.
     * 
     * @param args command-line arguments (not used)
     * @throws FileNotFoundException if the data file specified by FILE_NAME is not found
     */
    public static void main(String[] args) throws FileNotFoundException {
        Controller controller = new Controller();
        
        // load the file
        controller.loadData(FILE_NAME);
        
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new StudentListUI(controller);
            }
        });
    }

}
