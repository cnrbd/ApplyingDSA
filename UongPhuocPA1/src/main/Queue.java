/**
* This is the implementation of the queue
* Known Bugs: None
* @author Phuoc Uong
* phuocuong@brandeis.edu
* 10/11/2024
* COSI 21A PA1
*/

package main;

import java.util.NoSuchElementException;

public class Queue<T> {

	public T[] q;
	public int head;
	public int tail;
	public int numEntries;

	@SuppressWarnings("unchecked")
	/**
	 * This is the constructor for the queue
	 *
	 * @param capacity the max size of the queue
	 */
	public Queue(int capacity) {
		this.q = (T[]) new Object[capacity];
		this.head = 0;
		this.tail = 0;
		this.numEntries = 0;
	}

	/**
	 * This method adds a new element of type T to the end of the queue and the tail
	 * is getting updated
	 * O(1) runtime
	 *
	 * @param element the element that is getting added to the end of the queue
	 */
	public void enqueue(T element) {
		// add to end. move to tail to new element
		// also have to check what if the queue is full
		if (this.numEntries == q.length) {
			System.out.println("queue is full");
			return;
		}
		// adds the new element to the queue and move the tail circularly
		// System.out.println(element);
		q[tail] = element;
		// System.out.println(Arrays.toString(q));
		tail = (tail + 1) % q.length;
		this.numEntries++;

	}

	/**
	 * This method removes the element at the front of the queue and updates the
	 * head
	 * O(1) runtime
	 */
	public void dequeue() {
		// remove from head. update head
		// dont have to check if queue is full just check the element exist

		if (this.numEntries == 0) {
			System.out.println("current amount of entries " + this.numEntries);
			throw new NoSuchElementException();
		}

		q[head] = null;
		head = (head + 1) % q.length;
		this.numEntries--;

	}

	/**
	 * This method returns the front element of the queue but does not remove it
	 * O(1) runtime
	 *
	 * @return the value of the element at the front of the queue
	 */
	public T front() {
		// return the value at the head. not dequeue
		if (this.numEntries == 0) {
			throw new NoSuchElementException();
		}
		return q[head];
	}

	/**
	 * This method returns the size of the queue which is the amount of elements
	 * that the queue contains
	 * O(1) runtime
	 *
	 * @return the amount of elements in the queue which is its size
	 */
	public int size() {
		return this.numEntries;
	}

	/**
	 * To string representation of a queue
	 * O(n) runtime
	 *
	 * @return the string representation of a queue
	 */
	@Override
	public String toString() {
		if (this.numEntries == 0) {
			return "[]";
		}
		int curr = head;
		String qString = "[";
		// loops to the value before the tail since the print of the one before should
		// have closing bracket
		while ((curr + 1) % q.length != this.tail) {
			T currentVal = q[curr];
			qString += currentVal + ", ";
			curr = (curr + 1) % q.length;
		}
		qString += q[curr] + "]";

		return qString;
	}
}
