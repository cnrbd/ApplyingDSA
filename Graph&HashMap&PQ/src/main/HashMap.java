package main;

/**
 * This is hashmap class
 * Known Bugs: not sure
 *
 * @author phuoc uong
 *         phuocuong@brandeis.edu
 *         12/4/2024
 *         COSI 21A PA3
 */
public class HashMap {
    private Entry[] table;
    private int size;
    private int numEntries;

    /**
     * Constructor for hashmap
     */
    public HashMap() {
        this.size = 10;
        this.table = new Entry[this.size];
        this.numEntries = 0;
    }

    /**
     * Set the value of the key
     * o(n)
     *
     * @param key   the key
     * @param value the value
     */
    public void set(GraphNode key, int value) {
        String nodeId = key.getId();
        int hashIndex = hash(nodeId);

        // the node is not in the map
        if (!hasKey(key)) {
            Entry newEntry = new Entry(key, value);

            while (this.table[hashIndex] != null) {
                // linear probing
                hashIndex = (hashIndex + 1) % this.size;
            }
            this.table[hashIndex] = newEntry;
            this.numEntries++;

            // if load factor > 0.5, double table size
            if ((double) (this.numEntries / this.size) > 0.5) {
                resize();
            }

        } else {
            // the node is in the map
            while (this.table[hashIndex] != null) {
                if (this.table[hashIndex].getGraphNode().getId().equals(nodeId)) {
                    this.table[hashIndex].setHeapIndex(value);
                }
                // linear probing
                hashIndex = (hashIndex + 1) % this.size;
            }
        }
    }

    /**
     * Get the value of the key
     * o(n)
     *
     * @param key the key
     * @return the value
     */
    public int getValue(GraphNode key) {
        // key does not exist so return -1

        // key exists in map so find it
        String nodeId = key.getId();
        int hashIndex = hash(nodeId);

        while (this.table[hashIndex] != null) {
            if (this.table[hashIndex].getGraphNode().getId().equals(nodeId)) {
                return this.table[hashIndex].getHeapIndex();
            }
            // linear probing
            hashIndex = (hashIndex + 1) % this.size;
        }
        return -1;

    }

    /**
     * Check if the key exists
     * o(n)
     *
     * @param g the key
     * @return true if the key exists, false otherwise
     */
    public boolean hasKey(GraphNode g) {
        String nodeId = g.getId();
        int hashIndex = hash(nodeId);

        // loop until an empty slot is found that means the value does not exist
        while (this.table[hashIndex] != null) {
            if (this.table[hashIndex].getGraphNode().getId().equals(nodeId)) {
                if (this.table[hashIndex].getHeapIndex() == -1) {
                    return false;
                } else {
                    return true;
                }
            }
            // linear probing
            hashIndex = (hashIndex + 1) % this.size;
        }
        return false;
    }

    /**
     * Hash function
     * o(n)
     *
     * @param key the key
     * @return the hash value
     */
    public int hash(String key) {
        int ascii_sum = 0;
        for (int i = 0; i < key.length(); i++) {
            // ignore the dash
            if (key.charAt(i) != '-') {
                ascii_sum += (int) key.charAt(i) * (i + 1);
            }
        }
        return ascii_sum % this.size;
    }

    /**
     * o(n)
     * Resize the table
     */
    public void resize() {
        this.size = this.size * 2;
        Entry[] oldTable = this.table;
        this.table = new Entry[this.size];

        for (int i = 0; i < oldTable.length; i++) {
            if (oldTable[i] != null) {
                set(oldTable[i].getGraphNode(), oldTable[i].getHeapIndex());
            }
        }
    }

    /**
     * to string method
     * o(n)
     *
     * @return the string representation of the hashmap
     */
    public String toString() {
        String result = "[";
        for (int i = 0; i < this.size - 1; i++) {
            if (this.table[i] != null) {
                result += this.table[i].getHeapIndex();
            } else {
                result += "null";
            }
            result += ", ";
        }
        if (this.table[this.size - 1] != null) {
            result += this.table[this.size - 1].getHeapIndex();
        } else {
            result += "null";
        }

        return result + "]";

    }
}
