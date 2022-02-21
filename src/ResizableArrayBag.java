import java.util.Arrays;

public class ResizableArrayBag<T> implements BagInterface<T>
{
    private T[] bag;
    private static final int DEFAULT_CAPACITY = 25;
    private int numberOfEntries;
    private boolean integrityOK = false;
    private static final int MAX_CAPACITY = 10000;

    public ResizableArrayBag()
    {
        this(DEFAULT_CAPACITY);
    }

    /** Creates an empty bag having a given initial capacity.
     * @param desiredCapacity the integer capacity desired */
    public ResizableArrayBag(int desiredCapacity)
    {
        if (desiredCapacity <= MAX_CAPACITY)
        {
            numberOfEntries = 0;
            // the cast is safe because the new array contains null entries
            @SuppressWarnings("unchecked")
            T[] tempBag = (T[]) new Object[desiredCapacity];
            bag = tempBag;
        }
        else
        {
            throw new IllegalStateException("Attempt to create a bag whose capacity exceeds allowed maximum");
        }
    } //end constructor

    @Override
    public int getCurrentSize() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    /** Adds a new entry to this bag.
     * @param newEntry  The object to be added as a new entry.
     * @return  True if the addition is successful, or false if not. */
    public boolean add(T newEntry)
    {
        checkIntegrity();
        boolean result = true;

        if (isArrayFull())
        {
            result = false;
        }
        else
        {  // Assertion: result is true here
            bag[numberOfEntries] = newEntry;
            numberOfEntries++;
        } // end if

        return result;
    } // end add

    // Throws an exception if this object is not initialized.
    private void checkIntegrity()
    {

        if (!integrityOK)

            throw new SecurityException("ArrayBag object is corrupt.");

    } // end checkIntegrity

    private boolean isArrayFull()
    {
        return numberOfEntries == bag.length;
    } //end isArrayFull

    // Throws an exception if the client requests a capacity that is too large.
    private void checkCapacity(int capacity)
    {
        if (capacity > MAX_CAPACITY)
        throw new IllegalStateException("Attempt to create a bag whose " +
                "capacity exeeds allowed " +
                "maximum of " + MAX_CAPACITY);
    } // end checkCapacity

    // Doubles the size of the array bag.
    // Precondition: checkIntegrity has been called.
    private void doubleCapacity()
    {
        int newLength = 2 * bag.length;
        checkCapacity(newLength);
        bag = Arrays.copyOf(bag, newLength);
    } // end doubleCapacity

    @Override
    public T remove() {
        return null;
    }

    @Override
    public boolean remove(T anEntry) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public int getFrequencyOf(T anEntry) {
        return 0;
    }

    @Override
    public boolean contains(T anEntry) {
        return false;
    }

    @Override
    public T[] toArray() {
        return null;
    }

    /*
    PROJECT 1 METHODS
     */

    @Override
    public T[] union(T bag) {
        return null;
    }

    @Override
    public T[] intersection(T bag) {
        return null;
    }

    @Override
    public T[] difference(T bag) {
        return null;
    }
}