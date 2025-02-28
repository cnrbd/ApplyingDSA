package main;

/**
 * This is entry
 * Known Bugs: not sure
 *
 * @author phuoc uong
 *         phuocuong@brandeis.edu
 *         12/4/2024
 *         COSI 21A PA3
 */
public class Entry {
    GraphNode gNodekey;
    int heapIndexValue;

    /**
     * Constructor for entry
     *
     * @param graphNode the graph node
     * @param heapIndex the index in heap
     */
    public Entry(GraphNode graphNode, int heapIndex) {
        this.gNodekey = graphNode;
        this.heapIndexValue = heapIndex;
    }

    /**
     * Get the heap index
     *
     * @return the heap index
     */
    public int getHeapIndex() {
        return heapIndexValue;
    }

    /**
     * Set the heap index
     *
     * @param heapIndex the heap index
     */
    public void setHeapIndex(int heapIndex) {
        this.heapIndexValue = heapIndex;
    }

    /**
     * Get the graph node
     *
     * @return the graph node
     */
    public GraphNode getGraphNode() {
        return gNodekey;
    }

}
