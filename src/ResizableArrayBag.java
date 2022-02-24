import javax.management.BadAttributeValueExpException;
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

    /** Adds a new entry to this bag.
     @param newEntry  The object to be added as a new entry.
     @return  True.  */
    public boolean add(T newEntry)
    {
        checkIntegrity();
        boolean result = true;

        if (isArrayFull())
        {
            doubleCapacity();
        } // end if

        bag[numberOfEntries] = newEntry;
        numberOfEntries++;

        return true;
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
    /** Counts the number of times a given entry appears in this bag.
     @param anEntry  The entry to be counted.
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
    /** Finds the similarity elements appearing in the first bag and the second bag.
       @param bag the bag to compare with the first bag.
       @return a new allocated array of all the similarity of the two bags
    */
    
    public T[] intersection(BagInterface bag) {
        T[] sameBag = toArray();
        T[] compareBag = (T[]) bag.toArray();
        int sameSize = getCurrentSize();
        int compareSize = bag.getCurrentSize();
        T[] resultBag = (T[]) new Object[MAX_CAPACITY];
        int j = 0;

        for(int i = 0; i < sameSize; i++){
            for(int k = 0; k < compareSize; k++)
            {
                if(sameBag[i] != null && compareBag[k] != null && sameBag[i].equals(compareBag[k]))
                {
                    resultBag[j] = sameBag[i]; //assign the same element into the result bag
                    j++;
                    
                    sameBag[i] = null;     // remove the element             
                    
                    compareBag[k] = null;  // remove the element
                
                   
                }
            } // end for
        } // end for

        return resultBag;

    }

    @Override
    /** Finds the difference between the first bag to the second bag.
     * @param bag The bag to use to find the difference of the first bag.
     * @return A newly allocated array of the difference of the two bags.
     */
    public BagInterface<T> difference(BagInterface<T> bag) {

        BagInterface<T> resultBag = new ResizableArrayBag<T>(MAX_CAPACITY);
        BagInterface<T> duplicateEntrys = new ResizableArrayBag<T>(MAX_CAPACITY);

        // Handles case when either bag is empty
        if(isEmpty() || bag.isEmpty())
        {
            return resultBag;
        } // end if

        // Starts finding the difference of the two bags
        for (int i = 0; i < numberOfEntries; i++)
        {

            // Skips over 'null' entries
            while(this.bag[i] == null)
            {
                if(i < numberOfEntries - 1)
                {
                    i++;
                }
                else
                {
                    return resultBag; // return the bag if last entry is null
                }
            } // end while

            //Checks for duplicate entries
            if(duplicateEntrys.getCurrentSize() != 0)
            {
                boolean dupeFound = false;

                do
                {
                    // Return bag if the rest of the entries up to the end are duplicates
                    if(i >= (numberOfEntries - 1))
                    {
                        return resultBag;
                    }
                    dupeFound = true;
                    i++;
                }
                while (duplicateEntrys.contains(this.bag[i])); // end do while

            } // end if

            // Finds the difference of one type of entry
            T entry = this.bag[i];
            int diff = getFrequencyOf(entry) - bag.getFrequencyOf(entry);

            if(diff > 0)
            {
                for(int k = 0; k < diff; k++)
                {
                    resultBag.add(entry);
                }
            }; // end if
            duplicateEntrys.add(entry);
        } // end for

        return resultBag;
    } // end difference
}