package main;

/**
 * This is prq
 * Known Bugs: not sure
 *
 * @author phuoc uong
 *         phuocuong@brandeis.edu
 *         12/4/2024
 *         COSI 21A PA3
 */
public class PriorityQueue {
    private GraphNode[] table;
    private int heapSize;
    private HashMap map;

    /**
     * Constructor for priority queue
     */
    public PriorityQueue() {
        table = new GraphNode[10];
        this.heapSize = 0;
        this.map = new HashMap();
    }

    /**
     * Insert a graph node
     * o(logn)
     *
     * @param g the graph node
     */
    public void insert(GraphNode g) {
        if (heapSize == table.length) {
            resize();
        }
        table[heapSize] = g;
        // put this in the map, key is the node and value is the heap index
        map.set(g, heapSize);
        heapSize++;

        // heapify up the element added
        heapify(g);
    }

    /**
     * Pull the highest priority element
     * o(logn)
     *
     * @return the highest priority element
     */
    public GraphNode pullHighestPriorityElement() {
        GraphNode min = getRoot();
        // set the last element to be the root
        table[0] = table[heapSize - 1];
        heapSize--;
        // if there are more than 1 elements then set the index in the heap to be 0
        if (heapSize != 0) {
            // heapify down
            map.set(table[0], 0);
            heapifyDown(0);
        }
        table[heapSize] = null;

        return min;

    }

    /**
     * Rebalance the graph node
     * o(logn)
     *
     * @param g the graph node
     */
    public void rebalance(GraphNode g) {
        heapify(g);
    }

    /**
     * Check if the priority queue is empty
     * o(1)
     *
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {
        return this.heapSize == 0;
    }

    /**
     * Get the root of the priority queue
     * o(1)
     *
     * @return the root
     */
    public GraphNode getRoot() {
        // deletion to be -1
        map.set(table[0], -1);
        return table[0];
    }

    /**
     * Get the parent of the index
     * o(1)
     *
     * @param i the index
     * @return the parent index
     */
    public int getParent(int i) {
        return (i - 1) / 2;
    }

    /**
     * Get the left child of the index
     * o(1)
     *
     * @param i the index
     * @return the left child index
     */
    public int getLeftChild(int i) {
        return 2 * i + 1;
    }

    /**
     * Get the right child of the index
     * o (1)
     *
     * @param i the index
     * @return the right child index
     */
    public int getRightChild(int i) {
        return 2 * i + 2;
    }

    /**
     * Heapify down
     * o(logn)
     *
     * @param gIndex the index
     */
    public void heapifyDown(int gIndex) {
        // int gIndex = map.getValue(g);
        int leftChildIndex = getLeftChild(gIndex);
        int rightChildIndex = getRightChild(gIndex);
        int smallest = gIndex;
        if (leftChildIndex <= heapSize && table[leftChildIndex].priority < table[gIndex].priority) {
            smallest = leftChildIndex;
        }
        if (rightChildIndex <= heapSize && table[rightChildIndex].priority < table[smallest].priority) {
            smallest = rightChildIndex;
        }
        if (smallest != gIndex) {
            swap(smallest, gIndex);
            heapifyDown(smallest);
        }
    }

    // heapify up
    /**
     * Heapify the graph node
     * o(logn)
     *
     * @param g the graph node
     */
    public void heapify(GraphNode g) {
        int gIndex = map.getValue(g);
        int parentIndex = getParent(gIndex);
        while (gIndex > 0 && table[gIndex].priority < table[parentIndex].priority) {
            swap(parentIndex, gIndex);
            gIndex = parentIndex;
            parentIndex = getParent(gIndex);
        }
    }

    /**
     * Swap the graph nodes
     * o(n)
     *
     * @param parentIndex the parent index
     * @param gIndex      the g index
     */
    public void swap(int parentIndex, int gIndex) {
        GraphNode temp = table[parentIndex];
        table[parentIndex] = table[gIndex];
        table[gIndex] = temp;
        map.set(table[parentIndex], parentIndex);
        map.set(table[gIndex], gIndex);
    }

    /**
     * to string method
     * o(n)
     *
     * @return the string representation of the priority queue
     */
    public String toString() {
        String result = "";
        for (int i = 0; i < heapSize; i++) {
            result += table[i].priority + "\n";
        }
        return result;
    }

    /**
     * Check if the priority queue contains the graph node
     * o(n)
     *
     * @param g the graph node
     * @return true if contains, false otherwise
     */
    public boolean contains(GraphNode g) {
        return map.hasKey(g);
    }

    /**
     * Resize the table
     * o(n)
     */
    public void resize() {
        GraphNode[] oldTable = table;
        this.table = new GraphNode[oldTable.length * 2];
        for (int i = 0; i < oldTable.length; i++) {
            this.table[i] = oldTable[i];
        }
    }

}
