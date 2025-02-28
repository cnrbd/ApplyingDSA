package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.PriorityQueue;
import main.GraphNode;

class TestPQ {
    PriorityQueue pq;
    GraphNode node1, node2, node3, node4, node5;

    @BeforeEach
    void set() {
        pq = new PriorityQueue();
        node1 = new GraphNode("1fd76b04-1df7-4838-b854-111111111112", false);
        node1.priority = 5;
        node2 = new GraphNode("2fd76b04-1df7-4838-b854-111111111121", false);
        node2.priority = 10;
        node3 = new GraphNode("3fd76b04-1df7-4838-b854-111111111211", false);
        node3.priority = 20;
        node4 = new GraphNode("4fd76b04-1df7-4838-b854-111111112111", false);
        node4.priority = 15;
        node5 = new GraphNode("5fd76b04-1df7-4838-b854-111111121111", false);
        node5.priority = 8;
        pq.insert(node1);
        pq.insert(node2);
        pq.insert(node3);
        pq.insert(node4);
        pq.insert(node5);
        // 1,5,3,4,2
    }

    @Test
    void testToString() {
        assertEquals("5\n" + //
                "8\n" + //
                "20\n" + //
                "15\n" + //
                "10\n", pq.toString());
    }

    @Test
    void testExtract() {
        assertEquals(node1, pq.pullHighestPriorityElement());
        assertFalse(pq.isEmpty());
        assertEquals(node5, pq.pullHighestPriorityElement());
        assertEquals(node2, pq.pullHighestPriorityElement());
        assertEquals(node4, pq.pullHighestPriorityElement());
        assertEquals(node3, pq.pullHighestPriorityElement());
        assertTrue(pq.isEmpty());
    }

    @Test
    void testRebalance() {
        node4.priority = 1;
        pq.rebalance(node4);
        node2.priority = 2;
        pq.rebalance(node2);
        System.out.println(pq);
        assertEquals(node4, pq.pullHighestPriorityElement());
        assertEquals(node2, pq.pullHighestPriorityElement());
        assertEquals(node1, pq.pullHighestPriorityElement());
        assertEquals(node5, pq.pullHighestPriorityElement());
        assertEquals(node3, pq.pullHighestPriorityElement());
    }
}