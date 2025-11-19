import java.util.ArrayList;

public class StudentListModel extends GenericListModel {
    
    private static final String[] COLUMNS = {
        "ID", "Name", "Type", "Total Credit Hours", "Tuition Due"
    };
    
    public StudentListModel(ArrayList<Student> list) {
        super(list);
    }
    
    @Override
    public Object getValueAt(int row, int column) {
        Student s = (Student)(list.get(row));
        
        switch(column) {
            case 0: return s.getID();
            case 1: return s.getName();
            case 2: return s.getType();
            case 3: return s.getCreditHours();
            case 4: return String.format("%,.2f", s.getTuitionDue());
        }
        
        return null;
    }
    
    @Override
    public String[] getColumns() {
        return COLUMNS;
    }

}
