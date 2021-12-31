
public class Node<T extends Comparable<T>> {
	public T elem;
	public Node<T> next;
	public Node<T> prev;

	public Node(T elem, Node<T> next, Node<T> prev) {
		this.elem = elem;
		this.next = next;
		this.prev = prev;
	}

	public Node(T elem) {
		this.elem = elem;
		this.next = null;
		this.prev = null;
	}
	///// DO NOT EDIT THE ABOVE.
	
	//You are free to add more methods below.....

}