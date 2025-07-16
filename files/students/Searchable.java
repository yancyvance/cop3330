
/**
 * An interface that defines a searchable behavior for objects.
 * Classes implementing this interface should provide logic to
 * determine whether they match a given search key or another object.
 */
 
public interface Searchable {

    /**
     * Checks if this object matches the given search key.
     *
     * @param key the search key, such as a name or code
     * @return true if the object matches the key, false otherwise
     */
    public boolean isMatch(String key);
    
    /**
     * Checks if this object is considered equal to another object,
     * based on the implementing class's criteria.
     *
     * @param obj the object to compare to
     * @return true if equal based on custom logic, false otherwise
     */
    public boolean isEqual(Object obj);

}
