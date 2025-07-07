import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JScrollPane;

/**
 * The {@code CourseListUI} class represents a user interface window
 * that displays a list of courses in a table format. It can optionally
 * show the courses associated with a specific student.
 * 
 * <p>This class uses a {@link Controller} object to retrieve course and
 * student data, and displays the data in a scrollable JTable within a JFrame.</p>
 * 
 * <p>The window is centered relative to a given parent JFrame and
 * disposes itself on close.</p>
 */

public class CourseListUI {
    
    /** The controller used to fetch student and course data */
    private Controller controller;
    
    /** The main JFrame window for this UI */
    private JFrame frame;
    
    /** The JTable that displays course data */
    private JTable table;
    
    
    /**
     * Constructs a CourseListUI window displaying all courses.
     * The window is centered relative to the specified parent frame.
     * 
     * @param controller the controller instance to access data
     * @param parent the parent JFrame relative to which this window is centered
     */
    public CourseListUI(Controller controller, JFrame parent) {
        this(controller, parent, null);
    }
    
    /**
     * Constructs a CourseListUI window displaying courses associated with
     * the specified student, or all courses if the student name is null.
     * The window is centered relative to the specified parent frame.
     * 
     * @param controller the controller instance to access data
     * @param parent the parent JFrame relative to which this window is centered
     * @param studentName the name of the student whose courses to display; if null, all courses are displayed
     */
    public CourseListUI(Controller controller, JFrame parent, String studentName) {
        // set the controller for this view
        this.controller = controller;
        
        Student student = null;
        String frameTitle = "List of Courses";
        
        if(studentName != null) {
            // obtain the student object given their name
            student = this.controller.getStudent(studentName);
            frameTitle = "Student Details: " + student.getName();
        }
        
        // create a frame for this view        
        this.frame = new JFrame(frameTitle);
        
        // dispose this window only on exit
        this.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.frame.setSize(650, 200);
        
        // set column names
        String[] columnNames = {"Code", "Title", "Credit Hour"};
        
        // add the data to this table
        String[][] data;
        
        // load the data based on whether this is for a student or not
        if(student != null) {
            data = this.controller.getCourseListData( student );
        }
        else {
            data = this.controller.getCourseListData();
        }
        
        // create the table
        this.table = new JTable(data, columnNames);
        
        // make it scrollable
        JScrollPane sp = new JScrollPane(this.table);
        this.frame.add(sp);
        
        // center on the screen relative to parent
        this.frame.setLocationRelativeTo(parent);
        
        // show the frame
        this.frame.setVisible(true);
    }

}
