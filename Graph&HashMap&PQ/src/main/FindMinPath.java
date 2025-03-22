package main;



import java.io.IOException;
import java.io.PrintStream;

public class FindMinPath {
    /**
     * Main method tha runs djikstra's for the graph
     * O((V+E)logV)
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        PriorityQueue pq = new PriorityQueue();
        GraphWrapper gw = new GraphWrapper();
        GraphNode home = gw.getHome();
        home.priority = 0;
        pq.insert(home);
        GraphNode current = null;
        GraphNode goal = null;
        while (!pq.isEmpty()) {

            current = pq.pullHighestPriorityElement();
            // System.out.println(current.getId());

            if (current.isGoalNode()) {
                goal = current;
            } else {
                if (current.hasSouth()) {
                    GraphNode south = current.getSouth();
                    int x = current.priority + current.getSouthWeight();
                    if (!pq.contains(south) && south.priority != -1) {
                        south.priority = x;
                        south.previousNode = current;
                        south.previousDirection = "South";
                        pq.insert(south);
                    } else {
                        if (x < south.priority) {
                            south.priority = x;
                            pq.rebalance(south);
                            south.previousNode = current;
                            south.previousDirection = "South";
                        }
                    }
                }
                if (current.hasNorth()) {
                    GraphNode north = current.getNorth();
                    int x = current.priority + current.getNorthWeight();
                    if (!pq.contains(north) && north.priority != -1) {
                        north.priority = x;
                        north.previousNode = current;
                        north.previousDirection = "North";
                        pq.insert(north);
                    } else {
                        if (x < north.priority) {
                            north.priority = x;
                            pq.rebalance(north);
                            north.previousNode = current;
                            north.previousDirection = "North";
                        }
                    }
                }
                if (current.hasEast()) {
                    GraphNode east = current.getEast();
                    int x = current.priority + current.getEastWeight();
                    if (!pq.contains(east) && east.priority != -1) {
                        east.priority = x;
                        east.previousNode = current;
                        east.previousDirection = "East";
                        pq.insert(east);
                    } else {
                        if (x < east.priority) {
                            east.priority = x;
                            pq.rebalance(east);
                            east.previousNode = current;
                            east.previousDirection = "East";
                        }
                    }
                }
                if (current.hasWest()) {
                    GraphNode west = current.getWest();
                    int x = current.priority + current.getWestWeight();
                    if (!pq.contains(west) && west.priority != -1) {
                        west.priority = x;
                        west.previousNode = current;
                        west.previousDirection = "West";
                        pq.insert(west);
                    } else {
                        if (x < west.priority) {
                            west.priority = x;
                            pq.rebalance(west);
                            west.previousNode = current;
                            west.previousDirection = "West";
                        }
                    }
                }
            }
            current.priority = -1;

        }
        output(goal);

    }

    /**
     * Output the path of directions to the file answer.txt
     * o(n)
     *
     * @param g the graph node which is the goal node
     * @throws IOException
     */
    private static void output(GraphNode g) throws IOException {
        PrintStream out = new PrintStream("answer.txt");
        if (g == null) {
            out.close();
            return;
        }

        int count = 0;
        GraphNode curr = g;
        while (curr.previousNode != null) {
            count++;
            curr = curr.previousNode;
        }

        String[] output = new String[count];
        for (int i = count - 1; i >= 0; i--) {
            output[i] = g.previousDirection;
            g = g.previousNode;
        }

        for (String directions : output) {
            out.println(directions);

        }
        out.close();
    }

}
