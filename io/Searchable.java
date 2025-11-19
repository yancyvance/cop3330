
public interface Searchable {
    
    boolean matches(String key);
    
    boolean matchesExact(String key);
    
    default String getType() {
        return this.getClass().getName();
    }
    
}
