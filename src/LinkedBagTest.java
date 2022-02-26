import org.junit.jupiter.api.Test;

public class LinkedBagTest
{
    @Test
    public void setUp()
    {

        //Testing the Difference Method (Just using this to test if my method works, will use Java unit tests later)
        LinkedBag bag1 = new LinkedBag();
        LinkedBag bag2 = new LinkedBag();

        bag1.add("a");
        bag1.add("a");
        bag1.add("a");
        bag1.add("b");
        bag1.add("b");
        bag1.add("c");
        bag1.add("c");

        bag2.add("a");
        bag2.add("a");
        bag2.add("b");

        BagInterface<String> sameBag = bag1.intersection(bag2);
        Object[] arr1 = sameBag.toArray();

        for (Object s : arr1) {
            System.out.println(s);
            }

        BagInterface<String> diffBag = bag1.difference(bag2);
        Object[] arr = diffBag.toArray();

        for (Object s : arr) {
            System.out.println(s);
        }

        
    }
}