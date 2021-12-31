import java.util.Iterator;

public class Main {
	public static void main(String args[]) {
		/*
		 * The test here is very simple one. You need to 
		 * design your tests to test each and every functionality on 
		 * its own, then in interaction with other functionalities
		 * in the class to be confident of the correctness of your work.
		 */
		
		SortedList<Item> L = new SortedList<>();
		L.add(new Item(1));
		L.add(new Item(5));
		L.add(new Item(5));
		L.add(new Item(5));
		L.add(new Item(5));
		L.add(new Item(5));
		L.add(new Item(66));
		L.add(new Item(23));
		L.add(new Item(53));
		L.add(new Item(3));
		L.add(new Item(108));
		L.add(new Item(56));
		L.add(new Item(643));
		L.add(new Item(53));
		L.add(new Item(22));
		L.add(new Item(43));
		L.printList();
        System.out.println(L.isEmpty());
		System.out.println();
        System.out.println();

		L.remove(new Item(5));
		L.remove(new Item(53));
		L.remove(new Item(643));
		L.printList();
        System.out.println();
        System.out.println();

		System.out.println(L.indexOf(new Item(56)));
        System.out.println();
        System.out.println();

		L.remove(L.search(new Item(108)));
		L.printList();
        System.out.println();
        System.out.println();

        SortedList<Item> M = new SortedList<>();
        System.out.println(M.isEmpty());
        M.add(new Item(50));
        M.add(new Item(50));
        M.add(new Item(52));
        M.add(new Item(52));
        System.out.println(M.isEmpty());
        M.remove(new Item(50));
        System.out.println(M.isEmpty());
        M.remove(new Item(52));
        System.out.println(M.isEmpty());

        SortedList<Item> O = new SortedList<>();
        O.add(new Item(1));
        SortedList<Item> N = new SortedList<>(L);
        System.out.println(N.equals(L));
        System.out.println();

        Iterator<Item> X = L.iterator();
        while(X.hasNext()) {
            System.out.println(X.next());
        }
	}
}
