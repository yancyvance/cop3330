
public class Course {
    
    private String code;
    private String title;
    private int creditHour;
    
    public Course(String code, String title, int h) {
        this.code = code;
        this.title = title;
        this.creditHour = h;
    }
    
    public String toString() {
        return String.format("%d - %s: %s", this.creditHour, this.code, this.title);
    }
    
    public int getCreditHour() {
        return creditHour;
    }
}
