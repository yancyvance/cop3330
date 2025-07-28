package cop3330.util;

/**
 * A generic interface that defines search and comparison behaviors
 * for objects based on a key or another instance of the same type.
 * <p>
 * Classes implementing this interface must define logic to determine:
 * <ul>
 *   <li>whether the object matches a given search key (e.g., name, ID)</li>
 *   <li>whether the object is considered equal to another object of the same type</li>
 * </ul>
 *
 * @param <T> the type of object this interface can compare with
 */
public interface Searchable<T> {

    /**
     * Determines whether this object matches the given search key.
     * <p>
     * This is typically used to implement keyword-based or identifier-based
     * searches (e.g., by name, code, or ID).
     *
     * @param key the search key to match against (e.g., a name or code)
     * @return {@code true} if the object matches the key; {@code false} otherwise
     */
    public boolean isMatch(String key);
    
    /**
     * Compares this object with another object of the same type
     * to determine logical equality based on the implementing class's criteria.
     *
     * @param obj the object to compare with
     * @return {@code true} if both objects are considered equal; {@code false} otherwise
     */
    public boolean isEqual(T obj);

}
