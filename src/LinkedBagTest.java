import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LinkedBagTest
{
    /* Note, in LinkedBags, the returned bags for union, intersection, and difference return entries in opposite order,
    but it should be fine since order doesn't matter in the bags.
    */

    @Test
    void twoEmptyBags()
    {
        BagInterface<Integer> bag1 = new LinkedBag<>();
        BagInterface<Integer> bag2 = new LinkedBag<>();
        BagInterface<Integer> bag3 = new LinkedBag<>();

        assertArrayEquals(bag3.toArray(), bag1.union(bag2).toArray());
        assertArrayEquals(bag3.toArray(), bag1.intersection(bag2).toArray());
        assertArrayEquals(bag3.toArray(), bag1.difference(bag2).toArray());
    }

    @Test
    void oneBagEmptyOneBagFilled()
    {
        BagInterface<Integer> bag1 = new LinkedBag<>();
        BagInterface<Integer> bag2 = new LinkedBag<>();

        //Bag result that is empty
        BagInterface<Integer> emptyBagResult = new LinkedBag<>();

        //Bag for the case when union is used or bag1.difference(bag2)
        BagInterface<Integer> filledBagResult = new LinkedBag<>();

        bag1.add(1);
        bag1.add(1);
        bag1.add(2);
        bag1.add(2);
        bag1.add(3);

        filledBagResult.add(3);
        filledBagResult.add(2);
        filledBagResult.add(2);
        filledBagResult.add(1);
        filledBagResult.add(1);

        assertArrayEquals(filledBagResult.toArray(), bag1.union(bag2).toArray());
        assertArrayEquals(emptyBagResult.toArray(), bag1.intersection(bag2).toArray());
        assertArrayEquals(filledBagResult.toArray(), bag1.difference(bag2).toArray());
        assertArrayEquals(emptyBagResult.toArray(), bag2.difference(bag1).toArray());
    }

    @Test
    void twoBagsWithSameAmountOfEntries()
    {
        BagInterface<Integer> bag1 = new LinkedBag<>();
        BagInterface<Integer> bag2 = new LinkedBag<>();

        BagInterface<Integer> unionBag = new LinkedBag<>();
        BagInterface<Integer> sameBag = new LinkedBag<>();
        BagInterface<Integer> diffBag1 = new LinkedBag<>();
        BagInterface<Integer> diffBag2 = new LinkedBag<>();

        // Bag1: 1 1 2 2 3
        bag1.add(1);
        bag1.add(1);
        bag1.add(2);
        bag1.add(2);
        bag1.add(3);

        // Bag2: 1 2 2 2 4
        bag2.add(1);
        bag2.add(2);
        bag2.add(2);
        bag2.add(2);
        bag2.add(4);

        // Union: 1 1 2 2 3 1 2 2 2 4
        unionBag.add(3);
        unionBag.add(2);
        unionBag.add(2);
        unionBag.add(1);
        unionBag.add(1);
        unionBag.add(4);
        unionBag.add(2);
        unionBag.add(2);
        unionBag.add(2);
        unionBag.add(1);

        // Intersection: 1 2 2
        sameBag.add(2);
        sameBag.add(2);
        sameBag.add(1);

        // Difference (Bag1 - Bag2): 1 3
        diffBag1.add(3);
        diffBag1.add(1);

        // Difference (Bag2 - Bag1): 2 4
        diffBag2.add(4);
        diffBag2.add(2);

        assertArrayEquals(unionBag.toArray(), bag1.union(bag2).toArray());
        assertArrayEquals(sameBag.toArray(), bag1.intersection(bag2).toArray());
        assertArrayEquals(sameBag.toArray(), bag2.intersection(bag1).toArray());
        assertArrayEquals(diffBag1.toArray(), bag1.difference(bag2).toArray());
        assertArrayEquals(diffBag2.toArray(), bag2.difference(bag1).toArray());
    }

    @Test
    void oneBagHasMoreEntries()
    {
        BagInterface<Integer> bag1 = new LinkedBag<>();
        BagInterface<Integer> bag2 = new LinkedBag<>();

        BagInterface<Integer> unionBag = new LinkedBag<>();
        BagInterface<Integer> sameBag = new LinkedBag<>();
        BagInterface<Integer> diffBag1 = new LinkedBag<>();
        BagInterface<Integer> diffBag2 = new LinkedBag<>();

        // Bag1: 1 1 2 2 3
        bag1.add(1);
        bag1.add(1);
        bag1.add(2);
        bag1.add(2);
        bag1.add(3);

        // Bag2: 1 2 4
        bag2.add(1);
        bag2.add(2);
        bag2.add(4);

        // Union: 1 1 2 2 3 1 2 4
        unionBag.add(3);
        unionBag.add(2);
        unionBag.add(2);
        unionBag.add(1);
        unionBag.add(1);
        unionBag.add(4);
        unionBag.add(2);
        unionBag.add(1);

        // Intersection: 1 2
        sameBag.add(2);
        sameBag.add(1);

        // Difference (Bag1 - Bag2): 1 2 3
        diffBag1.add(3);
        diffBag1.add(2);
        diffBag1.add(1);

        // Difference (Bag2 - Bag1): 4
        diffBag2.add(4);

        assertArrayEquals(unionBag.toArray(), bag1.union(bag2).toArray());
        assertArrayEquals(sameBag.toArray(), bag1.intersection(bag2).toArray());
        assertArrayEquals(sameBag.toArray(), bag2.intersection(bag1).toArray());
        assertArrayEquals(diffBag1.toArray(), bag1.difference(bag2).toArray());
        assertArrayEquals(diffBag2.toArray(), bag2.difference(bag1).toArray());
    }

    @Test
    void twoBagsWithNoSimilarEntries()
    {
        BagInterface<Integer> bag1 = new LinkedBag<>();
        BagInterface<Integer> bag2 = new LinkedBag<>();

        BagInterface<Integer> unionBag = new LinkedBag<>();
        BagInterface<Integer> sameBag = new LinkedBag<>();
        BagInterface<Integer> diffBag1 = new LinkedBag<>();
        BagInterface<Integer> diffBag2 = new LinkedBag<>();

        // Bag1: 1 2 3 4 5
        bag1.add(1);
        bag1.add(2);
        bag1.add(3);
        bag1.add(4);
        bag1.add(5);

        // Bag2: 6 7 8 9 10
        bag2.add(6);
        bag2.add(7);
        bag2.add(8);
        bag2.add(9);
        bag2.add(10);

        // Union: 1 2 3 4 5 6 7 8 9 10
        unionBag.add(5);
        unionBag.add(4);
        unionBag.add(3);
        unionBag.add(2);
        unionBag.add(1);
        unionBag.add(10);
        unionBag.add(9);
        unionBag.add(8);
        unionBag.add(7);
        unionBag.add(6);
        // Intersection: EMPTY

        // Difference (Bag1 - Bag2):
        diffBag1.add(5);
        diffBag1.add(4);
        diffBag1.add(3);
        diffBag1.add(2);
        diffBag1.add(1);

        // Difference (Bag2 - Bag1): 2 4
        diffBag2.add(10);
        diffBag2.add(9);
        diffBag2.add(8);
        diffBag2.add(7);
        diffBag2.add(6);

        assertArrayEquals(unionBag.toArray(), bag1.union(bag2).toArray());
        assertArrayEquals(sameBag.toArray(), bag1.intersection(bag2).toArray());
        assertArrayEquals(sameBag.toArray(), bag2.intersection(bag1).toArray());
        assertArrayEquals(diffBag1.toArray(), bag1.difference(bag2).toArray());
        assertArrayEquals(diffBag2.toArray(), bag2.difference(bag1).toArray());
    }

    @Test
    void nullAddedToBagsThrowsException()
    {
        BagInterface<Integer> nullBag = new LinkedBag<>();
        assertThrows(RuntimeException.class,
                () -> {
                    nullBag.add(null);
                });
    }
}