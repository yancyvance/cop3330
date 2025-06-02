
/**
 * This is a sample {@code Main} class that will be used to
 * test the {@code IntegerList} class. You are free to modify
 * this source code to extensively test your implementation.
 * No need to submit this file.
 *
 * @author Your Full Name Goes Here
 * @version 1.0
 */
public class Main {

    public static void main(String[] args) {
        // declare a reference variable
        IntegerList myList;
        
        // instantiate a list with capacity of 5
        myList = new IntegerList(5);
        
        // print the capacity
        System.out.println( myList.getCapacity() );
        
        // print the list
        myList.display();
        
        // print the size
        System.out.println( myList.getSize() );
        
        // insert 5 integers
        myList.addElement(10);
        myList.addElement(20);
        myList.addElement(30);
        myList.addElement(40);
        myList.addElement(50);
        
        // print the list
        myList.display();
        
        // add another element
        myList.addElement(60);
        
        // print the list
        myList.display();
        
        // print the size
        System.out.println( myList.getSize() );
        
        // print the capacity
        System.out.println( myList.getCapacity() );
    }

}
