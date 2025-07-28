package cop3330.ui;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;

import cop3330.exceptions.DuplicateCourseEnrollmentException;
import cop3330.exceptions.CreditHourLimitExceededException;
import cop3330.exceptions.InvalidStudentStatusException;

import cop3330.controller.SimpleController;
import cop3330.misc.CourseListModel;
import cop3330.models.Student;

/**
 * The {@code LatestCourseListUI} class provides a graphical user interface window
 * that displays a list of courses in a table format.
 * 
 * <p>This window can either show all available courses or the courses
 * registered by a specific student.</p>
 * 
 * <p>Data is retrieved through a {@link SimpleController} instance,
 * and the course data is displayed using a {@link JTable} within a {@link JFrame}.</p>
 * 
 * <p>The window is centered relative to a specified parent frame and
 * disposes itself when closed.</p>
 */
public class LatestCourseListUI {
    
    /** The controller used to fetch student and course data */
    private SimpleController controller;
    
    /** The student whose courses are displayed (if any) */
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
     * the specified student, or all courses if the student ID is null.
     * The window is centered relative to the specified parent frame.
     * 
     * @param controller the controller instance to access data
     * @param parent the parent JFrame relative to which this window is centered
     * @param studentID the ID of the student whose courses to display; if null, all courses are displayed
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
    
    /**
     * Prompts the user to enter course details and attempts to add
     * the new course via the controller.
     * 
     * If any required information is missing, a message dialog is shown and
     * the course is not added.
     * 
     * @param e the event triggered by the "Add New Course" button
     */
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
    
    /**
     * Prompts the user for a course code and attempts to register
     * the current student for that course.
     * 
     * If the input is invalid or blank, registration is canceled and
     * an informational message is displayed.
     * 
     * If the student exceeds credit limits or is already enrolled,
     * an error dialog is shown.
     * 
     * @param e the event triggered by the "Register a Course" button
     */
    private void registerCourse(ActionEvent e) {
        String code = JOptionPane.showInputDialog(this.frame, "Enter Course Code:");
        
        if(code != null && !code.isEmpty()) {
            try {
                this.controller.registerStudentToCourse(student.getID(), code);
                
                this.table.revalidate();
                this.table.repaint();
            }
            catch (CreditHourLimitExceededException ex) {
                JOptionPane.showMessageDialog(this.frame, "Student has already reached the credit hour limit", "Error", JOptionPane.ERROR_MESSAGE);
            }
            catch (DuplicateCourseEnrollmentException ex) {
                JOptionPane.showMessageDialog(this.frame, "Course is already registered", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        else {
            JOptionPane.showMessageDialog(this.frame, "Course was not added due to incomplete information.");
        }
    }

}
