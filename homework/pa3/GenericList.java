import java.util.Arrays;

/**
 * An abstract list class that stores objects implementing the Searchable interface.
 * Provides basic functionality such as adding, retrieving, and checking size.
 * Prevents duplicate entries and dynamically resizes the internal array as needed.
 */
 
public class GenericList<T extends Searchable<T>> {

    /**
     * Internal array used to store list elements.
     */
    protected Searchable[] internalList;
    
    /**
     * Current number of elements in the list.
     */
    protected int size;
    
    /**
     * Current capacity of the internal array.
     */
    protected int capacity;
    
    
    /**
     * Constructs a GenericList with a default initial capacity of 10.
     */
    public GenericList() {
        this(10);
    }

    /**
     * Constructs a GenericList with a specified initial capacity.
     *
     * @param capacity the initial capacity of the internal array
     */
    public GenericList(int capacity) {
        // this will compile but we need to be careful
        this.internalList = new Searchable[capacity];
        this.size = 0;
        this.capacity = capacity;
    }
    
    /**
     * Adds a new object to the list if it is not already present.
     * Only allows objects that implement the Searchable interface.
     * Automatically grows the internal list if capacity is exceeded.
     *
     * @param obj the object to add
     * @throws IllegalArgumentException if the object does not implement Searchable
     */
    public void add(T obj) {
        // only add searchable objects
        if(!(obj instanceof Searchable))
            throw new IllegalArgumentException("Object must implement Searchable");
            
        // only add instance of T
        if(!(obj instanceof T))
            throw new IllegalArgumentException("Object is not valid");
            
        // check if it is already in the list
        // do not add it anymore
        if(this.contains(obj)) return;
            
        if(this.size+1 == this.capacity)
            growList();
            
        this.internalList[this.size++] = obj;
    }
    
    /**
     * Retrieves the object at the specified index.
     *
     * @param index the index of the object
     * @return the object at the specified position
     * @throws ArrayIndexOutOfBoundsException if the index is invalid
     */
    public T get(int index) {
        return (T) this.internalList[index];
    }
    
    /**
     * Returns the current number of elements in the list.
     *
     * @return the size of the list
     */
    public int size() {
        return this.size;
    }
    
    /**
     * Doubles the capacity of the internal array and copies over existing elements.
     */
    private void growList() {
        // grow the internal array
        this.capacity = this.capacity * 2;
        Searchable[] newList = new Searchable[this.capacity];
        
        // copy old elements
        for(int i = 0; i < this.size; i++) {
            newList[i] = this.internalList[i];
        }
        
        this.internalList = newList;
    }

    /**
     * Finds and returns the first object in the list that matches the given key.
     * Implemented by subclasses based on specific matching logic.
     *
     * @param key the search key
     * @return the matching object, or null if no match is found
     */
    public T findMatch(String key) {
        for(Searchable c : this.internalList) {
            if(c != null && c.isMatch(key))
                return (T)c;
        }
        
        return null;
    }
    
    /**
     * Returns a new list containing all objects that match the given key.
     * Implemented by subclasses with their specific type and match logic.
     *
     * @param key the search key
     * @return a BaseList containing all matching elements
     */
    public GenericList<T> query(String key) {
        GenericList<T> result = new GenericList<>();
        
        for(Searchable s : internalList) {
            Searchable tmp = s;
            
            if(tmp != null && tmp.isMatch(key))
                result.add((T)tmp);
        }
        
        return result;
    }
    
    /**
     * Determines whether the specified object is already in the list.
     * Implemented by subclasses using their equality logic.
     *
     * @param obj the object to check for
     * @return true if the object exists in the list; false otherwise
     */
    public boolean contains(T obj) {
        T c = obj;
        
        for(Searchable s : internalList) {
            T tmp =(T)s;
            
            if(tmp != null && tmp.isEqual(c))
                return true;
        }
        
        return false;
    }
    
    /**
     * Sorts the elements in this list in ascending order according to their natural ordering.
     * <p>
     * This method uses {@link Arrays#sort(Object[], int, int)} to sort the internal array
     * from index {@code 0} (inclusive) to {@code size} (exclusive).
     * </p>
     *
     * <p>All elements in the list must implement {@link Comparable} and must be mutually comparable,
     * otherwise a {@link ClassCastException} will be thrown at runtime.</p>
     *
     * @throws ClassCastException if the list contains elements that are not mutually comparable
     */
    public void sort() {
        Arrays.sort( this.internalList, 0, this.size );
    }
    
}
