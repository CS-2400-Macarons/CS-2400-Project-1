public class LinkedBagTest
{
    public static void main(String[] args)
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

        bag2.add("a");
        bag2.add("a");
        bag2.add("b");

        Object[] diffBag = bag1.difference(bag2);

        for (Object s : diffBag) {
            System.out.println(s);
        }
    }
}