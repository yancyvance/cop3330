package cop3330.misc;

import javax.swing.table.AbstractTableModel;
import cop3330.util.GenericList;
import cop3330.util.Searchable;

/**
 * An abstract table model backed by a generic {@link GenericList} of elements
 * that implement the {@link Searchable} interface.
 * <p>
 * This class provides a base implementation of a {@link javax.swing.table.TableModel}
 * where the data is stored in a {@code GenericList} of type {@code T}.
 * It handles basic table behaviors such as row and column counts and disables cell editing.
 * Subclasses must provide the column names by implementing {@link #getColumns()}.
 *
 * @param <T> the type of elements stored in the underlying {@code GenericList};
 *            must implement {@link Searchable} with the same type parameter
 */
public abstract class GenericListModel<T extends Searchable<T>> extends AbstractTableModel {
    
    /** The underlying list of data objects displayed in the table. */
    protected GenericList<T> list;
    
    /**
     * Constructs a new {@code GenericListModel} with the specified data list.
     * 
     * @param list the {@code GenericList} backing the table model
     */
    public GenericListModel(GenericList<T> list) {
        this.list = list;
    }

    /**
     * Returns the number of rows in the table, which corresponds to
     * the size of the underlying {@code GenericList}.
     * 
     * @return the number of rows in the table
     */
    @Override
    public int getRowCount() {
        return this.list.size();
    }
    
    /**
     * Returns the number of columns in the table.
     * This corresponds to the length of the array returned by {@link #getColumns()}.
     * 
     * @return the number of columns in the table
     */
    @Override
    public int getColumnCount() {
        return this.getColumns().length;
    }
    
    /**
     * Indicates that cells in the table are not editable by default.
     * 
     * @param row the row index of the cell
     * @param col the column index of the cell
     * @return {@code false} always, meaning cells are not editable
     */
    @Override
    public boolean isCellEditable(int row, int col) { 
        return false;
    }
    
    /**
     * Returns the name of the column at the specified index.
     * 
     * @param col the index of the column
     * @return the name of the column
     */
    @Override
    public String getColumnName(int col) {
        return this.getColumns()[col];
    }
    
    /**
     * Returns an array of column names for this table model.
     * <p>
     * Subclasses must implement this method to define the column headers.
     * 
     * @return an array of {@code String} representing the column names
     */
    public abstract String[] getColumns();

}
