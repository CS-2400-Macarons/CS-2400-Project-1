import org.junit.jupiter.api.Test;

public class ResizableArrayBagTest
{
    @Test
    public void setUp()
    {

        //Testing the Difference Method (Just using this to test if my method works, will use Java unit tests later)
        ResizableArrayBag bag1 = new ResizableArrayBag(6);
        ResizableArrayBag bag2 = new ResizableArrayBag(3);

        bag1.add("a");
        bag1.add("a");
        bag1.add("a");
        bag1.add("b");
        bag1.add("b");
        bag1.add("c");
        bag1.add(null);

        bag2.add("a");
        bag2.add("a");
        bag2.add("b");

        Object[] sameBag = bag1.intersection(bag2);

        for (int i = 0; i < sameBag.length; i++ ) {
            if(sameBag[i] != null){
            System.out.println(sameBag[i]);
            }
        }

        BagInterface<String> diffBag = bag1.difference(bag2);
        Object[] arr = diffBag.toArray();

        for (Object s : arr) {
            System.out.println(s);
        }

    }
}