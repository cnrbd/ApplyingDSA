
package main;

public class DoubleLinkedList<T> {
	private Node<T> head;
	private Node<T> tail;
	private int size;

	/**
	 * This is the constructor of the doubly linked list
	 */
	public DoubleLinkedList() {
		this.head = null;
		this.tail = null;
		this.size = 0;
	}

	/**
	 * This method returns the head node of the doubly linked list
	 * O(1) runtime
	 *
	 * @return the front node of the doubly linked list
	 */
	public Node<T> getFirst() {
		if (head == null) {
			return null;
		}
		return head;
	}

	/**
	 * This method inserts an element adds an element to the end of this list.
	 * O(1) runtime
	 *
	 * @param element the element to insert of type T
	 */
	public void insert(T element) {
		Node<T> newNode = new Node<T>(element);
		// add to end then replace the tail
		if (head == null) {
			head = newNode;
			tail = newNode;
		} else {
			tail.setNext(newNode);
			newNode.setPrev(tail);
			tail = newNode;
		}

		// increment size
		size++;
	}

	/**
	 * This method deletes the first element from this list that matches the
	 * provided key
	 * O(n) runtime
	 *
	 * @param key the value/key that is being deleted from the doubly linked list
	 * @return null if the key is not present in the doubly liked list or the key
	 */
	public T delete(T key) {
		// deleting the only node in the list.
		// we know that tail = head and prev = next = null
		Node<T> curr = head;
		// loop to the end of the linked list
		while (curr != null) {

			if (curr.getData().equals(key)) {
				// if the node is the head.
				if (curr.getPrev() == null) {
					head = curr.getNext();
					// check if the linked list only has 1 element
					if (head != null) {
						// if the linked list doesn't have one element then set new head prev to be null
						head.setPrev(null);
					} else {
						// if the linked list has one element then the tail and head will be null
						tail = null;
					}

				} // now don't need to check if the node is the only one in the list since we did
					// it above
					// if the node is the tail. the next is null so next.prev is an error
				else if (curr.getNext() == null) {
					curr.getPrev().setNext(curr.getNext());
					tail = curr.getPrev();
				} // if the node has prev and next. somewhere in the middle
				else {
					curr.getPrev().setNext(curr.getNext());
					curr.getNext().setPrev(curr.getPrev());
				}
				size--;
				return curr.getData();
			}
			curr = curr.getNext();
		}
		return null;
	}

	/**
	 * This method gets the first element in the list that matches the provided key
	 * O(n) runtime
	 *
	 * @param key the value that is being get
	 * @return null if the key isnt present in the linked list else it is the key
	 */
	public T get(T key) {
		Node<T> curr = head;
		while (curr != null) {
			if (curr.getData().equals(key)) {
				return curr.getData();
			}
			curr = curr.getNext();
		}
		return null;
	}

	/**
	 * This method returns the doubly linked list size
	 * O(1) runtime
	 *
	 * @return an int representing the size of the doubly linked list
	 */
	public int size() {
		return this.size;
	}

	/**
	 * /**
	 * To string representation of a doubly linked list
	 * O(n) runtime
	 *
	 * @return the string representation of a doubly linked list
	 */
	@Override
	public String toString() {
		if (head == null) {
			return "Empty List";
		}
		String dllString = "";
		Node<T> curr = head;
		while (curr.getNext() != null) {
			dllString += curr.getData() + " -> ";
			curr = curr.getNext();
		}
		dllString += curr.getData();
		return dllString;
	}

}
