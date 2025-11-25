
public class Main {
    
    public static void main(String[] args) {
        Student s = new GraduateStudent("John");
        
        s.registerCourse( new Course("COP3330", "OOP", 9) );
        s.registerCourse( new Course("COP3502", "CS1", 1) );
        
        s.introduce();
    }
    
}
        
        
