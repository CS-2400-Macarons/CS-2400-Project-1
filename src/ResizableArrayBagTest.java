import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ResizableArrayBagTest
{

    @Test
    void twoEmptyBags()
    {
        BagInterface<Integer> bag1 = new LinkedBag();
        BagInterface<Integer> bag2 = new LinkedBag();
        BagInterface<Integer> bag3 = new LinkedBag();


        assertArrayEquals(bag3.toArray(), bag1.intersection(bag2).toArray());
        assertArrayEquals(bag3.toArray(), bag1.difference(bag2).toArray());
    }

    @Test
    void firstBagEmptyAndSecondBagFilled()
    {

    }

    @Test
    void secondBagEmptyAndFirstBagFilled()
    {

    }

    @Test
    void twoBagsWithSameAmountOfEntries()
    {

    }

    // testing when first bag has more entries

    @Test
    void firstBagMoreEntries()
    {

    }

    @Test
    void secondBagMoreEntries()
    {

    }

    @Test
    void twoBagsWithNoSimilarEntries()
    {

    }

    @Test
    void bagsContainingNull()
    {

    }

    @Test
    public void setUp()
    {
        BagInterface<Integer> bag1 = new LinkedBag<Integer>();
        BagInterface<Integer> bag2 = new LinkedBag<Integer>();
        BagInterface<Integer> bag3 = new LinkedBag<Integer>();

        bag1.add(1);
        bag1.add(1);
        bag1.add(1);
        bag1.add(2);
        bag1.add(2);
        bag1.add(3);
        bag1.add(3);

        bag2.add(1);
        bag2.add(1);
        bag2.add(2);

    }
}