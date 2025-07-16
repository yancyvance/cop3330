import javax.swing.table.AbstractTableModel;

public abstract class BaseListModel extends AbstractTableModel {
    
    protected BaseList list;
    
    public BaseListModel(BaseList list) {
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
