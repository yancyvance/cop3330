
public class CourseListModel extends GenericListModel {
    
    private static final String[] COLUMNS = {
        "Code", "Title", "Credit"
    };
    
    public CourseListModel(GenericList<Course> list) {
        super(list);
    }

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
    
    @Override
    public String[] getColumns() {
        return COLUMNS;
    }

}
