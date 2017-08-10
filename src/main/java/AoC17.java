import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by rPhilip on 8/10/17.
 */
public class AoC17 {

    static public int AoC17pt2(String input) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        int xCord = 0;
        int yCord = 0;
        int minX = 0;
        int minY = 0;
        int maxX = 3;
        int maxY = 3;
        PriorityQueue queue = new PriorityQueue();
        StrNode firstNode = new StrNode(xCord, yCord, 0, input);
        queue.enqueue(firstNode);
        String result = "";
        while (!queue.isQueueEmpty()) {
            StrNode node = (StrNode) queue.dequeue();
            if (node.xValue == 3 && node.yValue == 3) {
                if (result.length() < node.getCode().length()) {
                    result = node.getCode();
                }
            } else {

                md.update(StandardCharsets.UTF_8.encode(node.getCode()));
                String directions = String.format("%032x", new BigInteger(1, md.digest()));

                if (node.xValue == 3 && node.yValue == 3) {
                    System.out.println("fuck");
                }
                //test whether up is available
                if (directions.charAt(0) >= 'b' && directions.charAt(0) <= 'f') {
                    if (isDoorInBound(node.xValue - 1, node.yValue, minX, minY, maxX, maxY)) {
                        queue.enqueue(new StrNode(node.xValue - 1, node.yValue, node.priority + 1, node.getCode() + 'U'));
                    }
                }
                //test whether down is available
                if (directions.charAt(1) >= 'b' && directions.charAt(1) <= 'f') {
                    if (isDoorInBound(node.xValue + 1, node.yValue, minX, minY, maxX, maxY)) {
                        queue.enqueue(new StrNode(node.xValue + 1, node.yValue, node.priority + 1, node.getCode() + 'D'));
                    }
                }
                //test whether left is available
                if (directions.charAt(2) >= 'b' && directions.charAt(2) <= 'f') {
                    if (isDoorInBound(node.xValue, node.yValue - 1, minX, minY, maxX, maxY)) {
                        queue.enqueue(new StrNode(node.xValue, node.yValue - 1, node.priority + 1, node.getCode() + 'L'));
                    }
                }
                //test whether right is available
                if (directions.charAt(3) >= 'b' && directions.charAt(3) <= 'f') {
                    if (isDoorInBound(node.xValue, node.yValue + 1, minX, minY, maxX, maxY)) {
                        queue.enqueue(new StrNode(node.xValue, node.yValue + 1, node.priority + 1, node.getCode() + 'R'));
                    }
                }
            }

        }
        result = result.substring(input.length());
        return result.length();
    }

    static public String AoC17pt1(String input) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        int xCord = 0;
        int yCord = 0;
        int minX = 0;
        int minY = 0;
        int maxX = 3;
        int maxY = 3;
        PriorityQueue queue = new PriorityQueue();
        StrNode firstNode = new StrNode(xCord, yCord, 0, input);
        queue.enqueue(firstNode);
        String result = "";
        while (true) {
            StrNode node = (StrNode) queue.dequeue();
            if (node.xValue == 3 && node.yValue == 3) {
                result = node.getCode();
                break;
            }

            md.update(StandardCharsets.UTF_8.encode(node.getCode()));
            String directions = String.format("%032x", new BigInteger(1, md.digest()));

            //test whether up is available
            if (directions.charAt(0) >= 'b' && directions.charAt(0) <= 'f') {
                if (isDoorInBound(node.xValue - 1, node.yValue, minX, minY, maxX, maxY)) {
                    queue.enqueue(new StrNode(node.xValue - 1, node.yValue, node.priority + 1, node.getCode() + 'U'));
                }
            }
            //test whether down is available
            if (directions.charAt(1) >= 'b' && directions.charAt(1) <= 'f') {
                if (isDoorInBound(node.xValue + 1, node.yValue, minX, minY, maxX, maxY)) {
                    queue.enqueue(new StrNode(node.xValue + 1, node.yValue, node.priority + 1, node.getCode() + 'D'));
                }
            }
            //test whether left is available
            if (directions.charAt(2) >= 'b' && directions.charAt(2) <= 'f') {
                if (isDoorInBound(node.xValue, node.yValue - 1, minX, minY, maxX, maxY)) {
                    queue.enqueue(new StrNode(node.xValue, node.yValue - 1, node.priority + 1, node.getCode() + 'L'));
                }
            }
            //test whether right is available
            if (directions.charAt(3) >= 'b' && directions.charAt(3) <= 'f') {
                if (isDoorInBound(node.xValue, node.yValue + 1, minX, minY, maxX, maxY)) {
                    queue.enqueue(new StrNode(node.xValue, node.yValue + 1, node.priority + 1, node.getCode() + 'R'));
                }
            }

        }
        result = result.substring(input.length());
        return result;
    }

    static boolean isDoorInBound(int x, int y, int minX, int minY, int maxX, int maxY) {
        if (x >= minX && x <= maxX) {
            if (y >= minY && y <= maxY) {
                return true;
            }
        }
        return false;
    }

    static public void main(String[] args) throws NoSuchAlgorithmException {
        System.out.println("The solution to AoC17.1 is: " + AoC17pt1("hhhxzeay"));
        System.out.println("The solution to AoC17.2 is: " + AoC17pt2("hhhxzeay"));

    }
}
