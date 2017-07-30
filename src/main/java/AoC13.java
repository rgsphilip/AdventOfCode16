import java.util.HashSet;

/**
 * Created by rPhilip on 7/28/17.
 */
public class AoC13 {
    static public int AoC13pt2(int favNumber) {
        //starting position in the maze is 1, 1
        int xCord = 1;
        int yCord = 1;

        HashSet<Node> seenNodes = new HashSet<>();
        Node firstNode = new Node(xCord, yCord, 0);
        seenNodes.add(firstNode);
        PriorityQueue queue = new PriorityQueue();
        queue.enqueue(firstNode);
        while (true) {

            Node node = queue.dequeue();
            if (node.priority >= 50) {
                break;
            }

            xCord = node.xValue;
            yCord = node.yValue;

            //get the possible nodes... look up, down, left, right.
            int candidatePriority = node.priority + 1;
            if (xCord - 1 >= 0) {
                addNewNodeIfPossible(queue, seenNodes, favNumber, xCord - 1, yCord, candidatePriority);
            }
            if (xCord + 1 >= 0) {
                addNewNodeIfPossible(queue, seenNodes, favNumber, xCord + 1, yCord, candidatePriority);
            }
            if (yCord - 1 >= 0) {
                addNewNodeIfPossible(queue, seenNodes, favNumber, xCord, yCord - 1, candidatePriority);
            }
            if (yCord + 1 >= 0) {
                addNewNodeIfPossible(queue, seenNodes, favNumber, xCord, yCord + 1, candidatePriority);
            }
        }
        return seenNodes.size();
    }

    static public int AoC13pt1(int favNumber, int xCordDestination, int yCordDestination) {
        //three problems:
        //1) generate map <--- use a function
        //2) turn it into a graph <--- make a graph on the fly using the function... as we go through, find the
        // adjacent cells
        //3) find shortest distance <--- Dijkstra!

        //starting position in the maze is 1, 1
        int xCord = 1;
        int yCord = 1;
        HashSet<Node> seenNodes = new HashSet<>();
        Node firstNode = new Node(xCord, yCord, 0);
        seenNodes.add(firstNode);
        PriorityQueue queue = new PriorityQueue();
        queue.enqueue(firstNode);
        while (!queue.isQueueEmpty()) {
            Node node = queue.dequeue();
            xCord = node.xValue;
            yCord = node.yValue;
            if (node.xValue == xCordDestination && node.yValue == yCordDestination) {
                return node.priority;
            }
            //get the possible nodes... look up, down, left, right.
            int candidatePriority = node.priority + 1;
            if (xCord - 1 >= 0) {
                addNewNodeIfPossible(queue, seenNodes, favNumber, xCord - 1, yCord, candidatePriority);
            }
            if (xCord + 1 >= 0) {
                addNewNodeIfPossible(queue, seenNodes, favNumber, xCord + 1, yCord, candidatePriority);
            }
            if (yCord - 1 >= 0) {
                addNewNodeIfPossible(queue, seenNodes, favNumber, xCord, yCord - 1, candidatePriority);
            }
            if (yCord + 1 >= 0) {
                addNewNodeIfPossible(queue, seenNodes, favNumber, xCord, yCord + 1, candidatePriority);
            }
        }

        return -1;
    }

    static void addNewNodeIfPossible(PriorityQueue queue, HashSet seenNodes, int favNumber, int xCord, int yCord, int priority) {
        Node candidateNode = new Node(xCord, yCord, priority);
        if (isCellOpen(favNumber, xCord, yCord) && !seenNodes.contains(candidateNode)) {
            queue.enqueue(candidateNode);
            seenNodes.add(candidateNode);
        }
    }

    static boolean isCellOpen(int favNumber, int xCord, int yCord) {
        /*From the problem statement:
        Find x*x + 3*x + 2*x*y + y + y*y.
        Add the office designer's favorite number (your puzzle input).
        Find the binary representation of that sum; count the number of bits that are 1.
        If the number of bits that are 1 is even, it's an open space.
        If the number of bits that are 1 is odd, it's a wall. */

        int numToEvaluate = (xCord * xCord) + (3 * xCord) + (2 * xCord * yCord) + yCord + (yCord * yCord) + favNumber;
        String binNum = Integer.toBinaryString(numToEvaluate);
        int count = 0;
        for (int i = 0; i < binNum.length(); i++) {
            if (binNum.charAt(i) == '1') {
                count++;
            }
        }
        return count % 2 == 0;
    }

    static public void main(String[] args) {
        System.out.println("The solution to AoC13.1 is: " + AoC13pt1(1364, 31, 39));
        System.out.println("The solution to AoC13.2 is: " + AoC13pt2(1364));

    }
}
