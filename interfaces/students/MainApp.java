
public class MainApp {

    public static void main(String[] args) {
        // create a controller
        SimpleController controller = new SimpleController();
        
        // load the user interface
        new UI(controller); 
    }

}
