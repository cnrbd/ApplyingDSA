

package main;

public class Node<T> {

	private T data;
	private Node<T> next;
	private Node<T> prev;

	/**
	 * This is the constructor of the Node
	 * 
	 * @param data the data field
	 */
	public Node(T data) {
		this.data = data;
		this.next = null;
		this.prev = null;
	}

	/**
	 * This setter sets the data field of the node
	 * O(1) runtime
	 *
	 * @param data: a generic which is the data field that the node data field will
	 *              be set to
	 *
	 */
	public void setData(T data) {
		this.data = data;
	}

	/**
	 * This setter sets the next field of the node
	 * O(1) runtime
	 *
	 * @param data: a generic which is the next field that the node next field will
	 *              be set to
	 *
	 */
	public void setNext(Node<T> next) {
		this.next = next;
	}

	/**
	 * This setter sets the prev field of the node
	 * O(1) runtime
	 *
	 * @param data: a generic which is the prev field that the node prev field will
	 *              be set to
	 *
	 */
	public void setPrev(Node<T> prev) {
		this.prev = prev;
	}

	/**
	 * This getter gets the next field of the node
	 * O(1) runtime
	 *
	 * @return the next node of the current node
	 */
	public Node<T> getNext() {
		return this.next;
	}

	/**
	 * This getter gets the prev field of the node
	 * O(1) runtime
	 *
	 * @return the prev node of the current node
	 */
	public Node<T> getPrev() {
		return this.prev;
	}

	/**
	 * This getter gets the data field of the node
	 * O(1) runtime
	 *
	 * @return the data field of the current node
	 */
	public T getData() {
		return this.data;
	}

	/**
	 * To string representation of a node
	 * O(1) runtime
	 *
	 * @return the string representation of a node
	 */
	@Override
	public String toString() {
		return "Node: " + this.data;
	}
}
