package cop3330.misc;

import cop3330.util.GenericList;
import cop3330.models.Course;

/**
 * A table model for displaying a list of {@link Course} objects.
 * <p>
 * This class extends {@link GenericListModel} specialized with {@code Course} elements,
 * providing the column headers and the data retrieval logic for each cell.
 * The columns displayed are "Code", "Title", and "Credit".
 */
public class CourseListModel extends GenericListModel<Course> {
    
    /** The column headers for the course table. */
    private static final String[] COLUMNS = {
        "Code", "Title", "Credit"
    };
    
    /**
     * Constructs a new {@code CourseListModel} backed by the specified list of courses.
     * 
     * @param list the {@link GenericList} of {@code Course} objects to display
     */
    public CourseListModel(GenericList<Course> list) {
        super(list);
    }

    /**
     * Returns the value to be displayed at the specified row and column in the table.
     * <p>
     * For the course table:
     * <ul>
     *   <li>Column 0 returns the course code</li>
     *   <li>Column 1 returns the course title</li>
     *   <li>Column 2 returns the credit hours</li>
     * </ul>
     *
     * @param row the row index of the cell
     * @param column the column index of the cell
     * @return the value for the specified cell, or {@code null} if the column is invalid
     */
    @Override
    public Object getValueAt(int row, int column) {
        Course c = (Course)(list.get(row));
        
        switch(column) {
            case 0: return c.getCode();
            case 1: return c.getTitle();
            case 2: return c.getCredit();
        }
        
        return null;
    }
    
    /**
     * Returns the names of the columns in the table.
     *
     * @return an array of column names: "Code", "Title", and "Credit"
     */
    @Override
    public String[] getColumns() {
        return COLUMNS;
    }

}
