/**
 A class of bags whose entries are stored in a chain of linked
 nodes.
 The bag is never full.
 */
public class LinkedBag<T> implements BagInterface<T>
{
    private Node firstNode; // Reference to first node
    private int numberOfEntries;

    public LinkedBag()
    {
        firstNode = null;
        numberOfEntries = 0;
    } // end default constructor

    @Override
    /** Gets the number of entries currently in this bag
     @return The integer number of entries currently in this bag. */
    public int getCurrentSize() {
        return numberOfEntries;
    } // end getCurrentSize

    @Override
    /** Sees whether this bag is empty.
     @return True if this bag is empty, or false if not. */
    public boolean isEmpty() {
        return numberOfEntries == 0;
    } // end isEmpty

    @Override
    /** Adds a new entry to this bag.
     @param newEntry  The object to be added as a new entry
     @return  True if the addition is successful, or false if not. */

    public boolean add(T newEntry)        // OutOfMemoryError possible
    {
        // Add to beginning of chain:
        Node newNode = new Node(newEntry);
        newNode.next = firstNode; // Make new node reference rest of chain
        // (firstNode is null if chain is empty)
        firstNode = newNode;      // New node is at beginning of chain
        numberOfEntries++;
        return true;

    } // end add

    @Override
    /** Removes one unspecified entry from this bag, if possible.
     @return  Either the removed entry, if the removal was successful, or null. */
    public T remove()
    {
        T result = null;
        if (firstNode != null)
        {
            result = firstNode.getData();
            firstNode = firstNode.getNextNode(); // Remove first node from chain
            numberOfEntries--;
        } // end if
        return result;
    } // end remove

    @Override
    /** Removes one occurrence of a given entry from this bag, if possible.
     @param anEntry  The entry to be removed.
     @return  True if the removal was successful, or false otherwise. */
    public boolean remove(T anEntry)
    {
        boolean result = false;
        Node nodeN = getReferenceTo(anEntry);
        if (nodeN != null)
        {
        // Replace located entry with entry in first node
            nodeN.setData(firstNode.getData());
            // Remove first node
            firstNode = firstNode.getNextNode();
            numberOfEntries--;
            result = true;
        } // end if
        return result;
    } // end remove

    // Locates a given entry within this bag.
    // Returns a reference to the node containing the
    // entry, if located, or null otherwise.
    private Node getReferenceTo(T anEntry)
    {
        boolean found = false;
        Node currentNode = firstNode;
        while (!found && (currentNode != null))
        {
            if (anEntry.equals(currentNode.getData()))
            found = true;
            else
                    currentNode = currentNode.getNextNode();
        } // end while
        return currentNode;
    } // end getReferenceTo


    @Override
    /** Removes all entries from this bag */
    public void clear() {
        while (!isEmpty())
            remove();
    } // end clear

    @Override
    /** Counts the number of times a given entry appears in this bag.
     @param anEntry  The entry to be counted.
     @return  The number of times anEntry appears in the bag. */
    public int getFrequencyOf(T anEntry) {
        int frequency = 0;

        int counter = 0;
        Node currentNode = firstNode;
        while((counter < numberOfEntries) && (currentNode != null))
        {
            if (anEntry.equals(currentNode.getData()))
            {
                frequency++;
            } // end if

            counter++;
            currentNode = currentNode.getNextNode();
        } // end while

        return frequency;
    } // end getFrequencyOf

    @Override
    /** Tests whether this bag contains a given entry.
     @param anEntry  The entry to find.
     @return  True if the bag contains anEntry, or false if not. */
    public boolean contains(T anEntry) {
        boolean found = false;
        Node currentNode = firstNode;

        while (!found && (currentNode != null))
        {
            if (anEntry.equals(currentNode.getData()))
                found = true;
            else
                currentNode = currentNode.getNextNode();
        } // end while

        return found;
    } // end contains

    @Override
    /** Retrieves all entries that are in this bag.
     @return  A newly allocated array of all the entries in the bag. Note: If the bag is empty, the returned array is empty. */
    public T[] toArray() {
        // The cast is safe because the new array contains null entries
        @SuppressWarnings("unchecked")
        T[] result = (T[])new Object[numberOfEntries]; // Unchecked cast

        int index = 0;
        Node currentNode = firstNode;
        while ((index < numberOfEntries) && (currentNode != null))
        {
            result[index] = currentNode.getData();
            index++;
            currentNode = currentNode.getNextNode();
        } // end while

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

        T[] resultBag = (T[]) new Object[sameSize];
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

        BagInterface<T> resultBag = new LinkedBag<T>();
        BagInterface<T> duplicateEntrys = new LinkedBag<T>();

        Node currentNode = firstNode;

        // Handles case when either bag is empty
        if(isEmpty() || bag.isEmpty())
        {
            return resultBag;
        } // end if

        // Starts finding the difference of the two bags
        for (int i = 0; i < numberOfEntries; i++)
        {
            // Skips over 'null' entries
            while(currentNode.getData() == null)
            {
                if(i < numberOfEntries - 1)
                {
                    currentNode = currentNode.getNextNode();
                    i++;
                }
                else
                {
                    return resultBag; // return the bag if first entry is null
                } // end if
            } // end while

            //Checks for duplicate entries
            if(duplicateEntrys.getCurrentSize() != 0)
            {
                boolean dupeFound = false;

                do
                {
                    // Return bag if the rest of the entries up to the end are duplicates
                    if (i >= (numberOfEntries - 1))
                    {
                        return resultBag;
                    } // end if

                    dupeFound = true;
                    currentNode = currentNode.getNextNode();
                    i++;
                }
                while (currentNode.getNextNode() != null && duplicateEntrys.contains(currentNode.getData()));
                // end do while

            } // end if

            // Finds the difference of one type of entry
            T entry = currentNode.getData();
            int diff = getFrequencyOf(entry) - bag.getFrequencyOf(entry);

            if(diff > 0)
            {
                for(int k = 0; k < diff; k++)
                {
                    resultBag.add(entry);
                }
            }; // end if
            duplicateEntrys.add(entry);
            currentNode = currentNode.getNextNode();
        } // end for

        return resultBag;
    } // end difference

    private class Node
    {
        private T data; // Entry in bag
        private Node next; // Link to next node

        private Node(T dataPortion)
        {
            this(dataPortion, null);
        } // end constructor

        private Node(T dataPortion, Node nextNode)
        {
            data = dataPortion;
            next = nextNode;
        } // end constructor

        private T getData()
        {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }

        public Node getNextNode() {
            return next;
        }

        public void setNextNode(Node next) {
            this.next = next;
        }
    } // end Node
}