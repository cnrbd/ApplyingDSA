package test;

import main.AVLPlayerNode;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import main.Player;

public class AVLPlayerNodeTest {

    Player player = new Player("name", 1, 1);

    @Test
    public void testRightRotate() {
        // create left heavy tree

        Player pp1 = new Player("five", 5, 100);
        Player pp2 = new Player("three", 3, 100);
        Player pp3 = new Player("two", 2, 100);

        AVLPlayerNode tree = new AVLPlayerNode(pp1, pp1.getID());
        tree = tree.insert(pp2, pp2.getID());

        tree = tree.insert(pp3, pp3.getID());

        assertEquals(0, tree.getBalanceFactor());
        assertEquals("((two)three(five))", tree.treeString());
    }

    @Test
    public void testRotateLeft() {
        Player ppp1 = new Player("five", 5, 100);
        Player ppp2 = new Player("seven", 7, 100);
        Player ppp3 = new Player("ten", 10, 100);

        AVLPlayerNode tree = new AVLPlayerNode(ppp1, ppp1.getID());
        tree = tree.insert(ppp2, ppp2.getID());

        tree = tree.insert(ppp3, ppp3.getID());

        assertEquals(0, tree.getBalanceFactor());
        assertEquals("((five)seven(ten))", tree.treeString());
    }

    @Test
    public void testHardCase() {
        // left right imbalance
        Player p1 = new Player("twenty", 20, 100);
        Player p2 = new Player("forty", 40, 100);
        Player p3 = new Player("ten", 10, 100);
        Player p4 = new Player("twenty5", 25, 100);
        Player p5 = new Player("thrity", 30, 100);
        AVLPlayerNode tree = new AVLPlayerNode(p1, p1.getID());
        tree = tree.insert(p2, p2.getID());

        tree = tree.insert(p3, p3.getID());

        tree = tree.insert(p4, p4.getID());

        tree = tree.insert(p5, p5.getID());

        assertEquals(-1, tree.getBalanceFactor());
        assertEquals("((ten)twenty((twenty5)thrity(forty)))", tree.treeString());

        assertEquals(1, tree.getRank(40));
        assertEquals(3, tree.getRank(25));
    }

    @Test
    public void testGetRankOnRight() {
        Player p1 = new Player("twenty", 20, 100);
        Player p2 = new Player("forty", 40, 100);
        Player p3 = new Player("ten", 10, 100);
        Player p4 = new Player("twenty5", 25, 100);
        Player p5 = new Player("thrity", 30, 100);
        AVLPlayerNode tree = new AVLPlayerNode(p1, p1.getID());
        tree = tree.insert(p2, p2.getID());
        assertEquals(1, tree.getRank(40));
        assertEquals(2, tree.getRank(20));
        tree = tree.insert(p3, p3.getID());
        assertEquals(3, tree.getRank(10));

        tree = tree.insert(p4, p4.getID());
        tree = tree.insert(p5, p5.getID());
        assertEquals(1, tree.getRank(40));
        assertEquals(2, tree.getRank(30));
        assertEquals(3, tree.getRank(25));
        assertEquals(5, tree.getRank(10));
        assertEquals(-1, tree.getRank(1));
        assertEquals(-1, tree.getRank(100));
    }

    @Test
    public void testGetRankOnLeft() {
        Player p1 = new Player("forty", 40, 100);
        Player p2 = new Player("thirty", 30, 100);
        Player p3 = new Player("twenty5", 25, 100);
        Player p4 = new Player("twenty", 20, 100);
        Player p5 = new Player("ten", 10, 100);

        AVLPlayerNode tree = new AVLPlayerNode(p1, p1.getID());
        tree = tree.insert(p2, p2.getID());
        tree = tree.insert(p3, p3.getID());
        tree = tree.insert(p4, p4.getID());
        tree = tree.insert(p5, p5.getID());

        assertEquals(1, tree.getRank(40));
        assertEquals(2, tree.getRank(30));
        assertEquals(3, tree.getRank(25));
        assertEquals(4, tree.getRank(20));
        assertEquals(5, tree.getRank(10));
        assertEquals(-1, tree.getRank(50));
        assertEquals(-1, tree.getRank(5));
    }

    @Test
    public void testRightLeftImbalance() {
        Player ppp1 = new Player("five", 5, 100);
        Player ppp2 = new Player("ten", 10, 100);
        Player ppp3 = new Player("seven", 7, 100);
        AVLPlayerNode tree = new AVLPlayerNode(ppp1, ppp1.getID());

        tree = tree.insert(ppp2, ppp2.getID());
        tree = tree.insert(ppp3, ppp3.getID());

        assertEquals(0, tree.getBalanceFactor());
        assertEquals("((five)seven(ten))", tree.treeString());
    }

    @Test
    public void testDelete() {

        Player p1 = new Player("twenty", 20, 100);
        Player p2 = new Player("forty", 40, 100);
        Player p3 = new Player("ten", 10, 100);
        Player p4 = new Player("twenty5", 25, 100);
        Player p5 = new Player("thrity", 30, 100);

        AVLPlayerNode tree = new AVLPlayerNode(p1, p1.getID());
        tree = tree.insert(p2, p2.getID());
        tree = tree.insert(p3, p3.getID());
        tree = tree.insert(p4, p4.getID());
        tree = tree.insert(p5, p5.getID());

        tree = tree.delete(40);

        assertEquals("NAME\tID  SCORE\n" + //
                "thrity\t30  100.00\n" + //
                "twenty5\t25  100.00\n" + //
                "twenty\t20  100.00\n" + //
                "ten\t10  100.00\n", tree.scoreboard());
        assertEquals(2, tree.getRightWeight());
    }

    @Test
    public void testDeleteRoot() {

        Player p1 = new Player("twenty", 20, 100);
        Player p2 = new Player("forty", 40, 100);
        Player p3 = new Player("ten", 10, 100);
        Player p4 = new Player("twenty5", 25, 100);
        Player p5 = new Player("thrity", 30, 100);

        AVLPlayerNode tree = new AVLPlayerNode(p1, p1.getID());
        tree = tree.insert(p2, p2.getID());
        tree = tree.insert(p3, p3.getID());
        tree = tree.insert(p4, p4.getID());
        tree = tree.insert(p5, p5.getID());

        tree = tree.delete(20);

        assertEquals("NAME\tID  SCORE\n" + //
                "forty\t40  100.00\n" + //
                "thrity\t30  100.00\n" + //
                "twenty5\t25  100.00\n" + //
                "ten\t10  100.00\n", tree.scoreboard());
        assertEquals(2, tree.getRightWeight());
    }

    @Test
    public void testGetPlayer() {
        Player p1 = new Player("twenty", 20, 100);
        Player p2 = new Player("forty", 40, 100);
        Player p3 = new Player("ten", 10, 100);
        AVLPlayerNode tree = new AVLPlayerNode(p1, p1.getID());
        tree = tree.insert(p2, p2.getID());
        tree = tree.insert(p3, p3.getID());

        assertEquals(p1, tree.getPlayer(20));
        assertEquals(p2, tree.getPlayer(40));
        assertEquals(p3, tree.getPlayer(10));
        assertEquals(null, tree.getPlayer(99));
        assertEquals(null, tree.getPlayer(-99));
    }
}
