import java.io.IOException;

/**
 * Created by rPhilip on 7/2/17.
 */
public class Runner {
    static public void main(String args[]) {
        try {
            System.out.println("AoC 04.1: The number of valid codes is " + AdventSolutions.advent04pt1());
            System.out.println("AoC 03.2: The number of valid triangles is " + AdventSolutions.advent03pt2());
            System.out.println("AoC 03.1: The number of valid triangles is " + AdventSolutions.advent03pt1());
            System.out.println("AoC 02.2: The bathroom code is " + AdventSolutions.advent02pt2());
            System.out.println("AoC 02.1: The bathroom code is " + AdventSolutions.advent02pt1());
            System.out.println("AoC 01.2: The first intersection is " + AdventSolutions.advent01pt2());
            System.out.println("AoC 01.1: The number of blocks is " + AdventSolutions.advent01pt1());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
