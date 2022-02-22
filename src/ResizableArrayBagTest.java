public class ResizableArrayBagTest
{
    public static void main(String[] args)
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

        bag2.add("a");
        bag2.add("a");
        bag2.add("b");

        Object[] diffBag = bag1.difference(bag2);

        for (Object s : diffBag) {
            System.out.println(s);
        }

    }
}