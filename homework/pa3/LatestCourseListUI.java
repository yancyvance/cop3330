import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;

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

public class LatestCourseListUI {
    
    /** The controller used to fetch student and course data */
    private SimpleController controller;
    
    private Student student;
    
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
    public LatestCourseListUI(SimpleController controller, JFrame parent) {
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
    public LatestCourseListUI(SimpleController controller, JFrame parent, String studentID) {
        // set the controller for this view
        this.controller = controller;
        
        String frameTitle = "List of Courses";
        
        if(studentID != null) {
            // obtain the student object given their name
            student = this.controller.lookupStudent(studentID);
            frameTitle = "Student Details: " + student.getName();
        }
        
        // create a frame for this view        
        this.frame = new JFrame(frameTitle);
        
        // dispose this window only on exit
        this.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.frame.setSize(650, 200);
        
        // add the data to this table
        CourseListModel model;
        
        // load the data based on whether this is for a student or not
        if(student != null) {
            //data = this.controller.getCourseListData( student );
            model = this.controller.getCourseListModel(student);
        }
        else {
            //data = this.controller.getCourseListData();
            model = this.controller.getCourseListModel();
        }
        
        // create the table
        //this.table = new JTable(data, columnNames);
        this.table = new JTable(model);
        
        // make it scrollable
        JScrollPane sp = new JScrollPane(this.table);
        this.frame.add(sp);
        
        // button to add a new course
        JButton btnAddCourse = new JButton("Add New Course");
        btnAddCourse.addActionListener(this::addNewCourse);
        
        // button to register for a course
        JButton btnRegister = new JButton("Register a Course");
        btnRegister.addActionListener(this::registerCourse);
        
        // add all buttons to a panel
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        
        if(student != null)
            bottomPanel.add(btnRegister);
        else
            bottomPanel.add(btnAddCourse);
        
        // add panel to a ScrollPane to prevent from being hidden
        JScrollPane bsp = new JScrollPane(bottomPanel,
            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            
        this.frame.add(bsp, BorderLayout.SOUTH);
        
        // center on the screen relative to parent
        this.frame.setLocationRelativeTo(parent);
        
        // show the frame
        this.frame.setVisible(true);
    }
    
    
    private void addNewCourse(ActionEvent e) {
        String code = JOptionPane.showInputDialog(this.frame, "Enter Course Code:");
        String title = JOptionPane.showInputDialog(this.frame, "Enter Course Title:");
        String credit = JOptionPane.showInputDialog(this.frame, "Enter Credit Hour:");
        
        if(code != null && title != null && credit != null && !code.isEmpty() && !title.isEmpty() && !credit.isEmpty()) {
            this.controller.addNewCourse(code, title, credit);
            
            this.table.revalidate();
            this.table.repaint();
        }
        else {
            JOptionPane.showMessageDialog(this.frame, "Course was not added due to incomplete information.");
        }
    }
    
    private void registerCourse(ActionEvent e) {
        String code = JOptionPane.showInputDialog(this.frame, "Enter Course Code:");
        
        if(code != null && !code.isEmpty()) {
            this.controller.registerStudentToCourse(student.getID(), code);
            
            this.table.revalidate();
            this.table.repaint();
        }
        else {
            JOptionPane.showMessageDialog(this.frame, "Course was not added due to incomplete information.");
        }
    }

}
