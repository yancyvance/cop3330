
public class SimpleController {
    
    private BaseList list;
    
    public SimpleController() {        
        // load the data
        this.loadCourseData();
        //this.loadStudentData();
    }
    
    public BaseList getList() {
        return this.list;
    }
    
    public void loadCourseData() {
        // create a list
        this.list = new CourseList();
        
        list.add( new Course("COP3502", "Computer Science I", 3) );
        list.add( new Course("COP3330", "Object-Oriented Programming", 3) );
        list.add( new Course("RES5000", "Research", 2) );
        list.add( new Course("RES4000", "Independent Study", 2) );
        list.add( new Course("INT1000", "Internship", 1) );
        
        // sort the data
        list.sort();
    }
    
    public void loadStudentData() {
        // create a list
        this.list = new StudentList();
        
        list.add( new GraduateStudent("John Doe") );
        list.add( new UndergraduateStudent("Super Mario") );
        list.add( new UndergraduateStudent("Jane Smith") );
        list.add( new GraduateStudent("Sam Smith") );
        
        // sort the data
        list.sort();
    }
    
}
