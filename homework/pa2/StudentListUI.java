import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;

/**
 * The {@code StudentListUI} class represents a user interface window
 * that displays a list of students in a table format with options to filter,
 * reset, and view related course information.
 * 
 * <p>This class uses a {@link Controller} object to retrieve student and
 * course data. It provides controls to filter students by search query,
 * reset the filter, view courses, and display detailed information about
 * a selected student.</p>
 * 
 * <p>The main window closes the application on exit and centers itself on
 * the screen.</p>
 */
public class StudentListUI {
    
    /** The controller used to fetch student and course data */
    private Controller controller;
    
    /** The main JFrame window for this UI */
    private JFrame frame;
    
    /** The JTable displaying student data */
    private JTable table;
    
    /** The JScrollPane containing the table */
    private JScrollPane sp;
    
    /** Button to apply a filter to the student list */
    private JButton btnFilter;
    
    /** Button to reset the applied filter */
    private JButton btnReset;
    
    
    /**
     * Constructs the StudentListUI window showing all students.
     * Sets up buttons for filtering, resetting, viewing courses,
     * and showing student details.
     * 
     * The window is centered on the screen.
     * 
     * @param controller the controller instance to access data
     */
    public StudentListUI(Controller controller) {
        // set the controller for this view
        this.controller = controller;
    
        // create a frame for this view
        this.frame = new JFrame("Student Information System");
        
        // terminate the application on exit
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 200);
        
        // populate the table with all data from the file
        populateTable(controller.getStudentListData());
        
        // button to open list of courses
        JButton btnCourses = new JButton("Show All Courses");
        btnCourses.addActionListener(this::showCourses);
        
        // button to filter entries of the table
        btnFilter = new JButton("Filter Students");
        btnFilter.addActionListener(this::filterStudents);
        
        // button to reset the filter applied to the table
        btnReset = new JButton("Reset Filter");
        btnReset.setEnabled(false);
        btnReset.addActionListener(this::resetFilter);
        
        // button to show list of courses of a student
        JButton btnRegistration = new JButton("View Student Registration");
        btnRegistration.addActionListener(this::openCourseListUI);
        
        // button to show info of a student
        JButton btnDetails = new JButton("View Student Details");
        btnDetails.addActionListener(this::displayStudentInfo);
        
        // add all buttons to a panel
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.add(btnCourses);
        bottomPanel.add(btnFilter);
        bottomPanel.add(btnReset);
        bottomPanel.add(btnRegistration);
        bottomPanel.add(btnDetails);
        this.frame.add(bottomPanel, BorderLayout.SOUTH);
        
        // center the window on the screen
        this.frame.setLocationRelativeTo(null);
        
        // show the frame
        this.frame.setVisible(true);
    }
    
    /**
     * Opens a {@link CourseListUI} window showing the courses
     * registered by the currently selected student in the table.
     * 
     * @param e the action event triggered by the user
     */
    private void openCourseListUI(ActionEvent e) {
        // get selected row
        int selectedRow = this.table.getSelectedRow();
        
        // ignore if no row is selected
        if(selectedRow == -1) {
            JOptionPane.showMessageDialog(this.frame, "Select a student first.");
            return;
        }
        
        // get the current row
        String studentName = table.getValueAt(selectedRow, 0).toString();
        
        // open the StudentUI and pass the details of the selected row
        new CourseListUI(this.controller, this.frame, studentName);
    }
    
    /**
     * Displays detailed information about the currently selected student
     * in a message dialog.
     * 
     * @param e the action event triggered by the user
     */
    private void displayStudentInfo(ActionEvent e) {
        // get selected row
        int selectedRow = this.table.getSelectedRow();
        
        // ignore if no row is selected
        if(selectedRow == -1) {
            JOptionPane.showMessageDialog(this.frame, "Select a student first.");
            return;
        }
        
        // get the current row
        String studentName = table.getValueAt(selectedRow, 0).toString();
        
        // get student object
        Student student = this.controller.getStudent(studentName);
        
        JOptionPane.showMessageDialog(this.frame, student.getInfo());
    }
    
    /**
     * Prompts the user for a search query and filters the student list table
     * accordingly. Disables the filter button and enables the reset button
     * upon successful filtering.
     * 
     * @param e the action event triggered by the user
     */
    private void filterStudents(ActionEvent e) {
        String key = JOptionPane.showInputDialog("Enter Search Query:");
        
        if(key != null && !key.isEmpty()) {
            // remove the old one first
            this.frame.remove(this.sp);
            
            populateTable( this.controller.getStudentListData( key ) );
            
            // disable the filter button and enable the reset
            btnFilter.setEnabled(false);
            btnReset.setEnabled(true);
        }
        else {
            JOptionPane.showMessageDialog(this.frame, "No search query was entered.");
        }
    }
    
    /**
     * Resets any applied filter on the student list and reloads
     * the full student data. Enables the filter button and disables
     * the reset button.
     * 
     * @param e the action event triggered by the user
     */
    private void resetFilter(ActionEvent e) {
        // remove the old one first
        this.frame.remove(this.sp);
        
        populateTable( this.controller.getStudentListData() );
        
        // enable the filter button and disable the reset
        btnFilter.setEnabled(true);
        btnReset.setEnabled(false);
    }
    
    /**
     * Opens a {@link CourseListUI} window displaying all courses.
     * 
     * @param e the action event triggered by the user
     */
    private void showCourses(ActionEvent e) {
        // open the StudentUI and pass the details of the selected row
        new CourseListUI(this.controller, this.frame);
    }
    
    /**
     * Populates the JTable with the provided student data
     * and embeds it within a JScrollPane added to the frame.
     * 
     * @param data a 2D array of student data to display in the table
     */
    private void populateTable(String[][] data) {
        // set column names
        String[] columnNames = {"Name", "Student Type", "Total Credit Hours", "Tuition Due"};
        
        // add the data to this table
        this.table = new JTable(data, columnNames);
        
        // make it scrollable
        this.sp = new JScrollPane(this.table);
        this.frame.add(this.sp, BorderLayout.CENTER);
        
        // refresh the UI
        this.frame.revalidate();
        this.frame.repaint();
    }

}
