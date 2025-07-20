import java.util.Arrays;

/**
 * A generic list class that stores objects implementing the {@link Searchable} interface.
 * <p>
 * This class provides basic list functionalities such as adding elements, retrieving by index,
 * checking the size, and sorting. It prevents duplicate entries by checking for existing elements
 * before adding and dynamically resizes the internal storage array as needed.
 * </p>
 *
 * <p>
 * Internally, elements are stored in an {@code Object[]} array for generic compatibility.
 * Elements are cast to type {@code T} upon retrieval or processing.
 * </p>
 *
 * @param <T> the type of elements stored in the list, which must implement {@link Searchable} for type {@code T}
 */
 
public class GenericList<T extends Searchable<T>> {

    /**
     * Internal array used to store list elements as {@code Object[]} due to Java generics limitations.
     */
    private Object[] internalList;
    
    /**
     * Current number of elements in the list.
     */
    private int size;
    
    /**
     * Current capacity of the internal array.
     */
    private int capacity;
    
    
    /**
     * Constructs a {@code GenericList} with a default initial capacity of 10.
     */
    public GenericList() {
        this(10);
    }

    /**
     * Constructs a {@code GenericList} with a specified initial capacity.
     *
     * @param capacity the initial capacity of the internal array
     */
    public GenericList(int capacity) {
        this.internalList = new Object[capacity];
        this.size = 0;
        this.capacity = capacity;
    }
    
    /**
     * Adds a new element to the list if it is not already present.
     * <p>
     * This method throws {@link IllegalArgumentException} if the element does not implement {@link Searchable}.
     * If the internal array capacity is exceeded, the list automatically grows.
     * Duplicate elements (determined by {@link Searchable#isEqual(Object)}) are not added.
     * </p>
     *
     * @param obj the element to add
     * @throws IllegalArgumentException if {@code obj} does not implement {@link Searchable}
     */
    @SuppressWarnings("unchecked")
    public void add(T obj) {
        // only add searchable objects
        if(!(obj instanceof Searchable))
            throw new IllegalArgumentException("Object must implement Searchable");
            
        // check if it is already in the list
        // do not add it anymore
        if(this.contains(obj)) return;
            
        if(this.size+1 == this.capacity)
            growList();
            
        this.internalList[this.size++] = obj;
    }
    
    /**
     * Retrieves the element at the specified index.
     *
     * @param index the zero-based index of the element to retrieve
     * @return the element at the specified index
     * @throws ArrayIndexOutOfBoundsException if the index is out of range (index &lt; 0 || index &gt;= size)
     */
    @SuppressWarnings("unchecked")
    public T get(int index) {
        return (T) this.internalList[index];
    }
    
    /**
     * Returns the number of elements currently stored in the list.
     *
     * @return the current size of the list
     */
    public int size() {
        return this.size;
    }
    
    /**
     * Doubles the capacity of the internal array and copies over existing elements.
     * This method is called automatically when the list capacity is exceeded.
     */
    private void growList() {
        // grow the internal array
        this.capacity = this.capacity * 2;
        Object[] newList = new Object[this.capacity];
        
        // copy old elements
        for(int i = 0; i < this.size; i++) {
            newList[i] = this.internalList[i];
        }
        
        this.internalList = newList;
    }

    /**
     * Finds and returns the first element in the list that matches the given key.
     * <p>
     * Matching is determined by the {@link Searchable#isMatch(String)} method.
     * </p>
     *
     * @param key the search key used to find a matching element
     * @return the first matching element, or {@code null} if no match is found
     */
    @SuppressWarnings("unchecked")
    public T findMatch(String key) {
        for(Object ob : this.internalList) {
            T c = (T)ob;
            if(c != null && c.isMatch(key))
                return c;
        }
        
        return null;
    }
    
    /**
     * Returns a new {@code GenericList} containing all elements that match the given key.
     * <p>
     * Matching is determined by the {@link Searchable#isMatch(String)} method.
     * </p>
     *
     * @param key the search key used to find matching elements
     * @return a {@code GenericList} containing all matching elements; may be empty if no matches found
     */
    @SuppressWarnings("unchecked")
    public GenericList<T> query(String key) {
        GenericList<T> result = new GenericList<>();
        
        for(Object s : internalList) {
            T tmp = (T)s;
            
            if(tmp != null && tmp.isMatch(key))
                result.add(tmp);
        }
        
        return result;
    }
    
    /**
     * Determines whether the specified element exists in the list.
     * <p>
     * Equality is determined by the {@link Searchable#isEqual(Object)} method.
     * </p>
     *
     * @param obj the element to check for containment
     * @return {@code true} if the element exists in the list; {@code false} otherwise
     */
    @SuppressWarnings("unchecked")
    public boolean contains(T obj) {
        T c = obj;
        
        for(Object s : internalList) {
            T tmp =(T)s;
            
            if(tmp != null && tmp.isEqual(c))
                return true;
        }
        
        return false;
    }
    
    /**
     * Sorts the elements in the list in ascending order according to their natural ordering.
     * <p>
     * This method uses {@link Arrays#sort(Object[], int, int)} to sort the internal array
     * from index {@code 0} (inclusive) to {@code size} (exclusive).
     * </p>
     *
     * <p>All elements must implement {@link Comparable} and be mutually comparable,
     * otherwise a {@link ClassCastException} will be thrown at runtime.</p>
     *
     * @throws ClassCastException if elements are not mutually comparable
     */
    public void sort() {
        Arrays.sort( this.internalList, 0, this.size );
    }
    
}
