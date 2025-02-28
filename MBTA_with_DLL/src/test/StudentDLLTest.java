package test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.DoubleLinkedList;

class StudentDLLTest {

	@Test
	void testInsertAndDelete() {
		DoubleLinkedList<Integer> dll = new DoubleLinkedList<Integer>();
		dll.insert(3);
		dll.insert(4);
		assertEquals(dll.size(), 2);
		assertEquals(dll.getFirst().getData(), 3);
		// System.out.println(dll.delete(3));
		assertEquals(3, dll.delete(3));
		assertEquals(dll.delete(100), null);
		assertEquals(dll.size(), 1);
	}

	@Test
	void testToString() {
		DoubleLinkedList<Integer> dll = new DoubleLinkedList<Integer>();
		assertEquals("Empty List", dll.toString());
		dll.insert(40);
		assertEquals("40", dll.toString());
		dll.insert(40);
		assertEquals("40 -> 40", dll.toString());
	}

	@Test
	void testGet() {
		DoubleLinkedList<Integer> dll = new DoubleLinkedList<Integer>();
		assertEquals(null, dll.get(100));
		dll.insert(40);
		assertEquals(40, dll.get(40));
	}
}
