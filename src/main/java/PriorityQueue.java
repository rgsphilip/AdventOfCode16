import java.util.ArrayList;

/**
 * Created by rPhilip on 7/28/17.
 */
public class PriorityQueue {
    //construct a min heap
    ArrayList<Node> queue = new ArrayList<>();

    PriorityQueue() {
        queue.add(new Node(0, 0, 0));
    }

    public int getParent(int index) {
        return index / 2;
    }

    public int getLeft(int index) {
        return (index * 2);
    }

    public int getRight (int index) {
        return (index * 2 + 1);
    }

    public boolean hasLeft(int index) {
        return queue.size() > index * 2;
    }

    public boolean hasRight(int index) {
        return queue.size() > index * 2 + 1;
    }

    public void minHeapify(int index) {
        int left = getLeft(index);
        int right = getRight(index);
        int smallest = index;
        if (left < queue.size() && queue.get(left).priority < queue.get(index).priority) {
            smallest = left;
        }
        if (right < queue.size() && queue.get(right).priority < queue.get(smallest).priority) {
            smallest = right;
        }
        if (smallest != index) {
            //swap
            Node temp = queue.get(index);
            queue.set(index, queue.get(smallest));
            queue.set(smallest, temp);
            minHeapify(smallest);
        }
    }

    public Node dequeue() {
        if (queue.size() < 2) {
            return null;
        }
        Node min = queue.get(1);
        queue.set(1, queue.get(queue.size() - 1));
        queue.remove(queue.size() - 1);
        minHeapify(1);
        return min;
    }

    public void enqueue(Node node) {
        queue.add(node);
        int nodeIx = queue.size() - 1;

        while (nodeIx > 1 && queue.get(nodeIx).priority < queue.get(getParent(nodeIx)).priority) {
            int parentIx = getParent(nodeIx);
            Node temp = queue.get(nodeIx);
            queue.set(nodeIx, queue.get(parentIx));
            queue.set(parentIx, temp);
            nodeIx = parentIx;
        }
    }

    public boolean isQueueEmpty() {
        return queue.size() == 1;
    }

    public void printQueueByPriority() {
        for (int i = 1; i < queue.size(); i++) {
             System.out.print(queue.get(i).priority + " ");
        }
    }

}
