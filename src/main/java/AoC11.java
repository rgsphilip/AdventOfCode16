import java.util.PriorityQueue;

/**
 * Created by rPhilip on 7/22/17.
 */
public class AoC11 {
    static public int AoC11pt1(FactoryState startState, FactoryState endState) {
        PriorityQueue<FactoryState> queue = new PriorityQueue<>();
        queue.add(startState);
        while (!queue.isEmpty()) {
            FactoryState currentState = queue.remove();
            if (currentState.equals(endState)) {
                return currentState.distFromStart;
            }
            //time to get children
        }

        return -1;
    }
}
