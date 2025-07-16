
public class StudentListModel extends BaseListModel {
    
    private static final String[] COLUMNS = {
        "ID", "Name", "Type", "Total Credit Hours", "Tuition Due"
    };
    
    public StudentListModel(StudentList list) {
        super(list);
    }
    
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
    
    @Override
    public String[] getColumns() {
        return COLUMNS;
    }

}
