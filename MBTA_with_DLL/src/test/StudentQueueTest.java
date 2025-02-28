package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.Queue;

class StudentQueueTest {

	@Test
	void testFront() {
		Queue<Integer> q = new Queue<Integer>(5);
		q.enqueue(6);
		q.enqueue(12);
		q.enqueue(8);
		q.enqueue(3);
		q.enqueue(-100);
		assertEquals(6, q.front());
		q.dequeue();
		assertEquals(12, q.front());
		q.dequeue();
		assertEquals(8, q.front());
		q.dequeue();
		assertEquals(3, q.front());
		q.dequeue();
		assertEquals(-100, q.front());
		q.dequeue();
		assertEquals(0, q.size());
	}

	@Test
	void testToString() {
		Queue<Integer> q = new Queue<Integer>(5);
		assertEquals("[]", q.toString());
		q.enqueue(6);
		q.enqueue(1000);
		q.enqueue(6000);
		q.dequeue();
		assertEquals("[1000, 6000]", q.toString());
	}

}
