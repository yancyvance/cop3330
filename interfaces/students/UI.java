
public class UI {

    private SimpleController controller;
    
    public UI(SimpleController controller) {
        this.controller = controller;
        
        // display the output
        this.displayOutput();
    }
    
    public void displayOutput() {
        this.displayHeader();
        this.displayContent();
    }
    
    public void displayHeader() {
        System.out.println("Welcome to the List Program!");
        System.out.println("============================");
    }
    
    public void displayContent() {
        // get all the list that is generic
        // assume that this list is already sorted
        BaseList list = this.controller.getList();
        
        if( list.size() == 0 ) {
            System.out.println("The list is empty!");
            return;
        }
        
        // iterate over and print
        for(int i = 0; i < list.size(); i++) {
            System.out.printf( "[%d] %s\n", i, list.get(i) );
        }
    }

}
