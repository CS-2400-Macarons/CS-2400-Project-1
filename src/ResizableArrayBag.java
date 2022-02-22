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
            integrityOK = true;
        }
        else
        {
            throw new IllegalStateException("Attempt to create a bag whose capacity exceeds allowed maximum");
        }
    } //end constructor

    @Override
    /** Gets the current number of entries in this bag.
     @return  The integer number of entries currently in this bag. */
    public int getCurrentSize()
    {
        return numberOfEntries;
    } // end getCurrentSize

    @Override
    /** Sees whether this bag is empty.
     @return  True if this bag is empty, or false if not. */
    public boolean isEmpty()
    {
        return numberOfEntries == 0;
    } // end isEmpty

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
    /** Removes one unspecified entry from this bag, if possible.
     @return  Either the removed entry, if the removal was successful,
     or null otherwise. */
    public T remove()
    {
        checkIntegrity();
        T result = removeEntry(numberOfEntries - 1);
        return result;
    } // end remove

    @Override
    /** Removes one occurrence of a given entry from this bag.
     @param anEntry  The entry to be removed.
     @return  True if the removal was successful, or false if not. */
    public boolean remove(T anEntry)
    {
        checkIntegrity();
        int index = getIndexOf(anEntry);
        T result = removeEntry(index);
        return anEntry.equals(result);
    } // end remove

    // Locates a given entry within the array bag.
    // Returns the index of the entry, if located, or -1 otherwise.
    // Precondition: checkIntegrity has been called.
    private int getIndexOf(T anEntry)
    {
        int where = -1;
        boolean found = false;
        int index = 0;
        while (!found && (index < numberOfEntries))
        {
            if (anEntry.equals(bag[index]))
            {
                found = true;
                where = index;
            } // end if
            index++;
        } // end while
        // Assertion: If where > -1, anEntry is in the array bag, and it
        // equals bag[where]; otherwise, anEntry is not in the array
        return where;
    } // end getIndexOf

    // Removes and returns the entry at a given index within the array bag.
    // If no such entry exists, returns null.
    // Preconditions: 0 <= givenIndex < numberOfEntries;
    //                checkIntegrity has been called.
    private T removeEntry(int givenIndex)
    {
        T result = null;
        if (!isEmpty() && (givenIndex >= 0))
        {
            result = bag[givenIndex];                   // Entry to remove
            bag[givenIndex] = bag[numberOfEntries - 1]; // Replace entry with last entry
            bag[numberOfEntries - 1] = null;            // Remove last entry
            numberOfEntries--;
        } // end if
        return result;
    } // end removeEntry

    @Override
    /** Removes all entries from this bag. */
    public void clear()
    {
        while (!isEmpty())
        remove();
    } // end clear

    @Override
    /** Counts the number of times a given entry appears in this bag.​
     @param anEntry  The entry to be counted.​
     @return  The number of times anEntry appears in this bag. */
    public int getFrequencyOf(T anEntry)
    {
        checkIntegrity();
        int counter = 0;
        for (int index = 0; index < numberOfEntries; index++)
        {
            if (anEntry.equals(bag[index]))
            {
                counter++;
            } // end if
        } // end for
        return counter;
    } // end getFrequencyOf

    @Override
    /** Tests whether this bag contains a given entry.
     @param anEntry  The entry to locate.
     @return  True if this bag contains anEntry, or false otherwise. */
    public boolean contains(T anEntry)
    {
        checkIntegrity();
        return getIndexOf(anEntry) > -1; // or >= 0
    } // end contains

    @Override
    /** Retrieves all entries that are in this bag.
     @return  A newly allocated array of all the entries in the bag. Note: If the bag is empty, the returned array is empty. */
    public T[] toArray() {
        // the cast is safe because the new array contains null entries
        @SuppressWarnings("unchecked")
        T[] result = (T[])new Object[numberOfEntries]; //unchecked cast
        for (int index = 0; index < numberOfEntries; index++)
        {
            result[index] = bag[index];
        } // end for
        return result;
    } // end toArray

    /*
    PROJECT 1 METHODS
     */

    @Override
    public T[] union(T[] bag) {
        return null;
    }

    @Override
    public T[] intersection(T[] bag) {
        return null;
    }

    @Override
    /** Finds the difference between the first bag to the second bag.
     * @param bag The bag to use to find the difference of the first bag.
     * @return A newly allocated array of the difference of the two bags.
     */
    public T[] difference(BagInterface bag) {

        T[] diffBag = toArray();
        T[] tempBag = (T[]) bag.toArray();

        int diffSize = getCurrentSize();
        int tempSize = bag.getCurrentSize();

        for(int i = 0; i < diffSize; i++){
            for(int k = 0; k < tempSize; k++)
            {
                if(diffBag[i] != null && tempBag[k] != null && diffBag[i].equals(tempBag[k]))
                {
                    diffBag[i] = diffBag[diffSize - 1]; // Replace entry with last entry
                    diffBag[diffSize - 1] = null;            // Remove last entry
                    diffSize--;

                    tempBag[k] = tempBag[tempSize -1];
                    tempBag[tempSize - 1] = null;
                    tempSize--;

                    i--;
                    k = tempSize;
                }
            } // end for
        } // end for

        @SuppressWarnings("unchecked")
        T[] result = (T[]) new Object[diffSize];
        for(int i = 0; i < diffSize; i++)
        {
            result[i] = diffBag[i];
        }

        return result;
    }
}