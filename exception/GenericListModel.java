import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public abstract class GenericListModel extends AbstractTableModel {
    
    protected ArrayList list;
    
    public GenericListModel(ArrayList list) {
        this.list = list;
    }

    @Override
    public int getRowCount() {
        return this.list.size();
    }
    
    @Override
    public int getColumnCount() {
        return this.getColumns().length;
    }
    
    @Override
    public boolean isCellEditable(int row, int col) { 
        return false;
    }
    
    @Override
    public String getColumnName(int col) {
        return this.getColumns()[col];
    }
    
    public abstract String[] getColumns();

}
