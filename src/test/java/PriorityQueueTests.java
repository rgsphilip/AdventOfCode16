import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Created by rPhilip on 7/30/17.
 */
public class PriorityQueueTests {
    @Test
    public void testEnqueueAndDequeue() {
        PriorityQueue queue = new PriorityQueue();
        queue.enqueue(new Node(0, 0, 3));
        queue.enqueue(new Node(0, 0, 4));
        queue.enqueue(new Node(0, 0, 2));
        queue.enqueue(new Node(0, 0, 10));
        queue.enqueue(new Node(0, 0, 7));
        queue.enqueue(new Node(0, 0, 8));
        queue.enqueue(new Node(0, 0, 1));
        queue.enqueue(new Node(0, 0, 5));
        queue.enqueue(new Node(0, 0, 6));
        queue.enqueue(new Node(0, 0, 9));
        for (int i = 1; i <= 10; i++) {
            assertEquals(queue.dequeue().getVisitedTimeStamp(), i);
        }
    }
}
