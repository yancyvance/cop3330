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
 * The {@code LatestStudentListUI} class represents a user interface window
 * for viewing, filtering, and managing student data.
 * 
 * <p>This UI allows users to:</p>
 * <ul>
 *   <li>View a list of students in a table</li>
 *   <li>Filter students by search query</li>
 *   <li>Reset the filtered list</li>
 *   <li>View all courses</li>
 *   <li>Display details and registered courses of a selected student</li>
 *   <li>Add a new student</li>
 *   <li>Save all changes to persistent storage</li>
 * </ul>
 * 
 * <p>The interface uses a {@link SimpleController} to interact with student
 * and course data. It is built using Swing components, with the table
 * displayed in the center and controls arranged along the bottom panel.</p>
 * 
 * <p>The window is centered on the screen and exits the application
 * when closed.</p>
 */
public class StudentListUI {
    
    /** The application title */
    private static final String WINDOW_TITLE = "Student Information System";
    
    
    /** The controller used to fetch student and course data */
    private SimpleController controller;
    
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
    
    private JButton btnAddStudent;
    
    
    /**
     * Constructs the StudentListUI window showing all students.
     * Sets up buttons for filtering, resetting, viewing courses,
     * and showing student details.
     * 
     * The window is centered on the screen.
     * 
     * @param controller the controller instance to access data
     */
    public StudentListUI(SimpleController controller) {
        // set the controller for this view
        this.controller = controller;
    
        // create a frame for this view
        this.frame = new JFrame(WINDOW_TITLE);
        
        // terminate the application on exit
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1050, 200);
        
        // populate the table with all data from the file
        populateTable(controller.getStudentListModel());
        
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
        
        // button to add new student
        btnAddStudent = new JButton("Add New Student");
        btnAddStudent.addActionListener(this::addStudent);
        
        // button to save changes
        JButton btnSave = new JButton("Save Data");
        btnSave.addActionListener(this::saveData);
        
        // add all buttons to a panel
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.add(btnCourses);
        bottomPanel.add(btnFilter);
        bottomPanel.add(btnReset);
        bottomPanel.add(btnRegistration);
        bottomPanel.add(btnDetails);
        bottomPanel.add(btnAddStudent);
        bottomPanel.add(btnSave);
        
        // add panel to a ScrollPane to prevent from being hidden
        JScrollPane bsp = new JScrollPane(bottomPanel,
            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            
        this.frame.add(bsp, BorderLayout.SOUTH);
        
        // center the window on the screen
        this.frame.setLocationRelativeTo(null);
        
        // show the frame
        this.frame.setVisible(true);
    }
    
    /**
     * Opens a {@link LatestCourseListUI} window showing the courses
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
        String studentID = table.getValueAt(selectedRow, 0).toString();
        
        // open the StudentUI and pass the details of the selected row
        new CourseListUI(this.controller, this.frame, studentID);
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
        String studentID = table.getValueAt(selectedRow, 0).toString();
        
        // get student object
        Student student = this.controller.lookupStudent(studentID);
        
        JOptionPane.showMessageDialog(this.frame, student.toString() + "\nTODO: Add abstract method getInfo() to Student class!");
    }
    
    /**
     * Prompts the user for a search query and filters the student list table
     * accordingly. Disables the filter button and enables the reset button
     * upon successful filtering.
     * 
     * @param e the action event triggered by the user
     */
    private void filterStudents(ActionEvent e) {
        String key = JOptionPane.showInputDialog(this.frame, "Enter Search Query:");
        
        if(key != null && !key.isEmpty()) {
            // remove the old one first
            this.frame.remove(this.sp);
            
            populateTable( this.controller.getStudentListModel(key) );
            
            // disable the filter button and enable the reset
            btnFilter.setEnabled(false);
            btnReset.setEnabled(true);
            btnAddStudent.setEnabled(false);
            
            // update the window title
            this.frame.setTitle( String.format("%s | Filtered: %s", WINDOW_TITLE, key) );
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
        
        populateTable( this.controller.getStudentListModel() );
        
        // enable the filter button and disable the reset
        btnFilter.setEnabled(true);
        btnReset.setEnabled(false);
        btnAddStudent.setEnabled(true);
        
        // update the window title
        this.frame.setTitle( WINDOW_TITLE );
    }
    
    /**
     * Opens a {@link LatestCourseListUI} window displaying all courses.
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
    private void populateTable(StudentListModel model) {       
        // add the data to this table
        this.table = new JTable(model);
        
        // make it scrollable
        this.sp = new JScrollPane(this.table);
        this.frame.add(this.sp, BorderLayout.CENTER);
        
        // refresh the UI
        this.frame.revalidate();
        this.frame.repaint();
    }
    
    private void addStudent(ActionEvent e) {
        String id = JOptionPane.showInputDialog(this.frame, "Enter Student ID:");
        String name = JOptionPane.showInputDialog(this.frame, "Enter Student Name:");
        String status = JOptionPane.showInputDialog(this.frame, "Enter Student Status:");
        String info = null;
        
        if( id != null && !id.isEmpty() && name != null && !name.isEmpty() && status != null && !status.isEmpty() ) {
            if( status.equals("Graduate") )
                info = JOptionPane.showInputDialog(this.frame, "Enter Thesis Topic:");
            else if( status.equals("Undergraduate") )
                info = JOptionPane.showInputDialog(this.frame, "Enter Year Level:");
            
            // does not handle invalid status
            this.controller.addNewStudent(id, name, status, info);
            
            this.table.revalidate();
            this.table.repaint();
            
            return;
        }
        
        JOptionPane.showMessageDialog(this.frame, "Student was not added due to incomplete information.");
    }
    
    private void saveData(ActionEvent e) {
        try {
            if( this.controller.saveData() )
                JOptionPane.showMessageDialog(this.frame, "All changes have been saved.");
            else
                JOptionPane.showMessageDialog(this.frame, "There is an error saving the data.");
        }
        catch( Exception ex ) {
            
        }
    }

}
