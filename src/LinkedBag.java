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
    public int getCurrentSize() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

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

    } // end Node
}