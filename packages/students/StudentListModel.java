package cop3330.misc;

import cop3330.util.GenericList;
import cop3330.models.Student;

/**
 * A table model for displaying a list of {@link Student} objects.
 * <p>
 * This class extends {@link GenericListModel} specialized with {@code Student} elements,
 * providing the column headers and the data retrieval logic for each cell.
 * The columns displayed are "ID", "Name", "Type", "Total Credit Hours", and "Tuition Due".
 */
public class StudentListModel extends GenericListModel<Student> {
    
    /** The column headers for the student table. */
    private static final String[] COLUMNS = {
        "ID", "Name", "Type", "Total Credit Hours", "Tuition Due"
    };
    
    /**
     * Constructs a new {@code StudentListModel} backed by the specified list of students.
     * 
     * @param list the {@link GenericList} of {@code Student} objects to display
     */
    public StudentListModel(GenericList<Student> list) {
        super(list);
    }
    
    /**
     * Returns the value to be displayed at the specified row and column in the table.
     * <p>
     * For the student table:
     * <ul>
     *   <li>Column 0 returns the student ID</li>
     *   <li>Column 1 returns the student name</li>
     *   <li>Column 2 returns the student type/status</li>
     *   <li>Column 3 returns the total credit hours</li>
     *   <li>Column 4 returns the tuition due formatted as a decimal currency string</li>
     * </ul>
     *
     * @param row the row index of the cell
     * @param column the column index of the cell
     * @return the value for the specified cell, or {@code null} if the column is invalid
     */
    @Override
    public Object getValueAt(int row, int column) {
        Student s = (Student)(list.get(row));
        
        switch(column) {
            case 0: return s.getID();
            case 1: return s.getName();
            case 2: return s.getStatus();
            case 3: return s.getTotalCredit();
            case 4: return String.format("%,.2f", s.getTuitionDue());
        }
        
        return null;
    }
    
    /**
     * Returns the names of the columns in the table.
     *
     * @return an array of column names: "ID", "Name", "Type", "Total Credit Hours", and "Tuition Due"
     */
    @Override
    public String[] getColumns() {
        return COLUMNS;
    }

}
