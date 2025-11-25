
public class Course implements Comparable<Course>, Searchable {
    
    private String code;
    private String title;
    private int creditHour;
    
    public Course(String code, String title, int creditHour) {
        this.code = code;
        this.title = title;
        this.creditHour = creditHour;
    }
    
    public Course(Course ob) {
        this.code = ob.code;
        this.title = ob.title;
        this.creditHour = ob.creditHour;
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
    
    @Override
    public int compareTo(Course ob) {
        int myCredit = this.getCreditHour();
        int obCredit = ob.getCreditHour();
        
        if( myCredit == obCredit ) {
            return this.getCode().compareTo( ob.getCode() );
        }
        
        return myCredit-obCredit;
    }
    
    @Override
    public boolean matches(String key) {
        
        return this.getCode().toLowerCase().contains( key.toLowerCase() ) ||
            this.getTitle().toLowerCase().contains( key.toLowerCase() );    // added after the lecture
    }
    
    @Override
    public boolean matchesExact(String key) {
        
        return this.getCode().toLowerCase().equals( key.toLowerCase() ) ||
            this.getTitle().toLowerCase().equals( key.toLowerCase() );      // added after the lecture
    }
}
