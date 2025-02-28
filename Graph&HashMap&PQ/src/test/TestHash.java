
package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.HashMap;
import main.GraphNode;

class TestHash {
    HashMap dict;
    GraphNode node1, node2, node3, node4, node5;

    @BeforeEach
    void setUp() {
        dict = new HashMap();
        node1 = new GraphNode("1fd76b04-1df7-4838-b854-111111111112", false); // ascii val of 12 * 49 = 588
        node2 = new GraphNode("2fd76b04-1df7-4838-b854-111111111113", false);
        node3 = new GraphNode("3fd76b04-1df7-4838-b854-111111111121", false); // 588+1*reverse(294), land at 80
        node4 = new GraphNode("4fd76b04-1df7-4838-b854-111111111211", false); // 588+2*reverse(294), land at 72
        node5 = new GraphNode("5fd76b04-1df7-4838-b854-111111112111", false); // dummy node, shouldn't exist in list
        dict.set(node1, 1);
        dict.set(node2, 2);
        dict.set(node3, 3);
        dict.set(node4, 4);
        dict.set(node5, -1);
        System.out.println(dict);
    }

    @Test
    void testToString() {
        assertEquals("[null, 1, 3, 4, -1, null, null, null, 2, null]", dict.toString());
    }

    @Test
    void testGetValues() {
        assertEquals(1, dict.getValue(node1));
        assertEquals(2, dict.getValue(node2));
        assertEquals(3, dict.getValue(node3));
        assertEquals(4, dict.getValue(node4));
        assertEquals(-1, dict.getValue(node5));
    }

    @Test
    void testHasKey() {
        assertTrue(dict.hasKey(node1));
        assertTrue(dict.hasKey(node2));
        assertTrue(dict.hasKey(node3));
        assertTrue(dict.hasKey(node4));
        assertFalse(dict.hasKey(node5));
    }

    @Test
    void testOverride() {
        GraphNode node6 = new GraphNode("1fd76b04-1df7-4838-b854-111111111112", false);
        dict.set(node6, -1);
        assertFalse(dict.hasKey(node6));
        assertFalse(dict.hasKey(node1));
        System.out.println(dict);
    }
}