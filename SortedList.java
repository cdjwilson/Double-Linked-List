/*
  SortedList class is a doubly linked list that save elements in a sorted, increasing order. 
 */
import java.util.Iterator;

public class SortedList<T extends Comparable<T>> implements Iterable<T> {

	private Node<T> head;
	private Node<T> tail;
	private int size;

	public SortedList() {
		head = null;
		tail = null;
		size = 0;
	}
	
	/*
	 * Copy constructor:
	 * It gets a another sorted list, and copies it to this newly constructed 
	 * SortedList.
	 */
	public SortedList(SortedList<T> l) {
		if(!l.isEmpty()) {
			this.size = 0;
			Node<T> temp = l.head;
			while(temp.next != null){
				this.add(temp.elem);
				temp = temp.next;
			}
			this.add(temp.elem);
		}
	}
	
	/*
	 * Add element:
	 * It adds the passed in element to the list, such that the list remains sorted.
	 * 
	 * NOTE: if there is another element with the same value, insert the new 
	 * one just "before the next larger element". We will be writing element classes
	 * such that we can test if you inserted according to this spec.
	 * 
	 * EX: if the list has the following elements: 1->2->2->6->11
	 *     and you need to insert an element with value, it must be inserted 
	 *     between the second 2 and 6. 
	 */
	public void add(T elem) {
		Node<T> x = new Node(elem);
		if (isEmpty()) { // if there is nothing in the SortedList
			this.head = x;
			this.tail = x;
			size++;
		} else { // if something is already in SortedList
			Node<T> temp = this.head;
			if (temp.elem.compareTo(elem) >= 0) { // check if the head of SortedList
				this.head = x;					 // is bigger than the element being added
				x.next = temp;
				temp.prev = x;
				size++;
			} else if(tail.elem.compareTo(elem) <= 0) { // check if the tail of SortedList
				tail.next = x;						   // is smaller than the element being added
				x.prev = tail;
				tail = x;
				size++;
			} else { // if what is being added is going into the middle of SortedList
				while(temp.next.elem.compareTo(elem) < 0) { // check that the element being added is larger than temp but smaller than temp's next element
					temp = temp.next;
				}
				x.prev = temp;
				x.next = temp.next;
				temp.next.prev = x;
				temp.next = x;
				size++;
			}
		}
	}

	/*
	 * Remove given element if exits:
	 * Look for the element, and if it exits, delete it.
	 *
	 *  
	 * NOTE: if more than one copy of the element exists, all need to be deleted.
	 * 
	 * Complexity: if there is more than one element equal to 
	 * the passed in element, you should not look up the next
	 * element starting from the head or tail. In other words, 
	 * if you have a list of n elements, and all the elements are equal, 
	 * the complexity of deleting all of them must be linear: O(n). 
	 * 
	 * EX: if the list has following elements: 1->2->2->6->11
	 *     and we would like to delete 2, the new list should
	 *     look like: 1->6->11
	 */
	public void remove(T elem) {
		if (isEmpty() || head.elem.compareTo(elem) > 0 || tail.elem.compareTo(elem) < 0) return;
		if (head.elem.compareTo(elem) == 0) { //if the start of the start of SortedList is the elem being removed
			if (tail.elem.compareTo(elem) == 0) { // if the whole SortedList is the elem being removed
				head = null;
				tail = null;
				size = 0;
			} else {
				while (head.elem.compareTo(elem) == 0) { // move the head until it is not the element being removed
					head = head.next;
					head.prev = null;
					size--;
				}
			}
		} else {
			Node<T> temp = head;
			while (temp.next.elem.compareTo(elem) <= 0) { // while the next element of temp is less than or the same as the elem being removed
				if (temp.next.elem.compareTo(elem) == 0) { // temp is the element before the one you want to remove
					if (temp.next.next != null) {// if elem being removed is in the middle of SortedList
						temp.next = temp.next.next;
						temp.next.prev = temp;
						size--;
					} else { // if elem being removed is at the end of SortedList
						temp.next = null;
						tail = temp;
						size--;
						return;
					}
				} else {
					temp = temp.next;
				}
			}
		}
	}

	/*
	 * Remove the passed in node:
	 * In this method, you do not search for the element to be deleted,
	 * rather, you are given a reference to a node that need to be deleted.
	 * 
	 * NOTE: if the reference is "null", just return.
	 */
	public void remove(Node<T> n) {
		if(n == null || isEmpty()) return;
		if(this.size == 1) { // if n is the only node in SortedList
			this.head = null;
			this.tail = null;
			size = 0;
		} else if(head == n) { // if n is the first node and there is more than one node
			head = head.next;
			head.prev = null;
			size--;
		} else if(tail == n) { // if n is the last node
			tail = tail.prev;
			tail.next = null;
			size--;
		} else { // if n is a node in the middle of SortedList
			n.prev.next = n.next;
			n.next.prev = n.prev;
			size--;
		}
	}
	
	/*
	 * Test whether the list is empty or not
	 */
	public boolean isEmpty() {
		return size == 0;
	}
	
	/*
	 * Search for the passed in element:
	 * If the given element is found, return a reference to the first 
	 * occurrence of the element in the list.
	 * 
	 */
	public Node<T> search(T elem) {
		if(isEmpty() || head.elem.compareTo(elem) > 0 || tail.elem.compareTo(elem) < 0) return null; // if elem can't be in the list
		Node<T> temp = head;
		while(temp.elem.compareTo(elem) != 0) {
			if(temp.next == null) {
				return null;
			}
			temp = temp.next;
		}
		return temp;
	}

	/* Return the index of the given element if found.
	 * return -1 if it does not exist
	 * starts at 0
	 */
	public int indexOf(T elem) {
		if(isEmpty() || head.elem.compareTo(elem) > 0 || tail.elem.compareTo(elem) < 0) return -1; // if elem can't be in the list
		int index = 0;
		Node<T> temp = head;
		while(temp.elem.compareTo(elem) != 0) {
			if(temp.next == null) {
				return -1;
			}
			temp = temp.next;
			index++;
		}
		return index;
	}

	/*
	 * Return a list iterator.
	 */
	@Override
	public Iterator<T> iterator() {

		/*Might need to change this. We provided Default constructor 
		 * to get rid of compiler errors
		 * */
		if(isEmpty()) return null;
		return new SortedListIterator<>(this.head);
	}

	/*
	 * Checks if the passed in list is equal to this list.
	 * 
	 * NOTE: we are checking if the whole list matches the 
	 * passed in list.
	 */
	@Override
	public boolean equals(Object obj) {
		if(obj.getClass() != SortedList.class) return false;
		else if(((SortedList) obj).size != this.size) {
			return false;
		}
		else {
			Node<T> temp1 = ((SortedList) obj).head;
			Node<T> temp2 = this.head;
			while(temp1.next != null) {
				if(temp1.elem.compareTo(temp2.elem) != 0) return false;
				temp1 = temp1.next;
				temp2 = temp2.next;
			}
			return true;
		}
	}

	
	public void printList() {
		if (head == null)
			return;
		Node<T> temp = head;
		while (temp != null) {
			System.out.println(temp.elem.toString());
			temp = temp.next;
		}
	}

}
