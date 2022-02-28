public class BagDriver
{
    public static void main(String[] args) {

        // Initializing Bags
        BagInterface<String> bag1 = new ResizableArrayBag<>(4);
        BagInterface<String> bag2 = new LinkedBag<>();

        bag1.add("Small Figurine");
        bag1.add("Movie Poster");
        bag1.add("Blu-Ray DVD");
        bag1.add("Vinyl Records");

        bag2.add("Plush");
        bag2.add("Movie Poster");
        bag2.add("Small Figurine");
        bag2.add("Movie Poster");

        /*
        *
        How You Would Use Union Method
        *
        Use: Use the union method if you want all the entries from both bags in one bag
         */

        BagInterface<String> unionBag = bag1.union(bag2);
        Object[] unionArr1 = unionBag.toArray();

        System.out.print("Union Bag: ");
        for(int index = 0; index < unionArr1.length; index++)
        {
            if(index > 0)
            {
                System.out.print(", ");
            }
            System.out.print(unionArr1[index]);
        }
        System.out.println();
        /*
        Union Bag: Small Figurine, Movie Poster, Blu-Ray DVD, Vinyl Records,
        Movie Poster, Small Figurine, Movie Poster, Plush
        */


        /*
        *
        How You Would Use Intersection Method
        *
        Use: Use the intersection method if you want entries that appear in both bags
         */

        BagInterface<String> intersectionBag = bag1.intersection(bag2);
        Object[] intersectionArr = intersectionBag.toArray();

        System.out.print("Intersection Bag: ");
        for(int index = 0; index < intersectionArr.length; index++)
        {
            if(index > 0)
            {
                System.out.print(", ");
            }
            System.out.print(intersectionArr[index]);
        }
        System.out.println();
        /*
        Intersection Bag: Small Figurine, Movie Poster
         */


        /*
        *
        How You Would Use Difference Bag
        *
        Use: Use the difference method if you want a bag that has entries that doesn't appear in another bag
         */

        BagInterface<String> diffBag1 = bag1.difference(bag2);
        Object[] diffArr1 = diffBag1.toArray();

        System.out.print("Difference Bag (Bag 1 - Bag 2): ");
        for(int index = 0; index < diffArr1.length; index++)
        {
            if(index > 0)
            {
                System.out.print(", ");
            }
            System.out.print(diffArr1[index]);
        }
        System.out.println();
        /*
        Difference Bag (Bag 1 - Bag 2): Blu-Ray DVD, Vinyl Records
         */

        BagInterface<String> diffBag2 = bag2.difference(bag1);
        Object[] diffArr2 = diffBag2.toArray();

        System.out.print("Difference Bag (Bag 2 - Bag 1): ");
        for(int index = 0; index < diffArr2.length; index++)
        {
            if(index > 0)
            {
                System.out.print(", ");
            }
            System.out.print(diffArr2[index]);
        }
        System.out.println();
        /*
        Difference Bag (Bag 2 - Bag 1): Plush, Movie Poster
         */
    }
}
