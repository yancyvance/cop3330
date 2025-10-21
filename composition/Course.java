
public class Course {
    
    private String code;
    private String title;
    private int creditHour;
    
    public Course(String code, String title, int creditHour) {
        this.code = code;
        this.title = title;
        this.creditHour = creditHour;
    }
    
    public String toString() {
        return String.format("%d - %s: %s", this.getCreditHour(), this.getCode(), this.getTitle());
    }
    
    public String getCode() {
        return this.code;
    }
    
    public String getTitle() {
        return this.title;
    }
    
    public int getCreditHour() {
        return this.creditHour;
    }
    
    public void setCode(String code) {
        this.code = code;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public void setCreditHour(int creditHour) {
        this.creditHour = creditHour;
    }
    
}
