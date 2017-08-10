

/**
 * Created by rPhilip on 8/10/17.
 */
public class AoC18 {

    static int AoC18pt2(String input, int numRows) {
        return 0;
    }

    static int AoC18pt1(String input, int numRows) {
        char[][] floor = new char[numRows][input.length()];
        floor[0] = input.toCharArray();
        int safeTiles = 0;
        for (int i = 0; i < floor[0].length; i++) {
            if (floor[0][i] == '.') {
                safeTiles++;
            }
        }
        for (int i = 1; i < numRows; i++) {
            for (int j = 0; j < input.length(); j++) {
                //this could be optimized by doing this assignment out of the loop and shifting the values over
                char left;
                char center = floor[i - 1][j];
                char right;
                if (j == 0) {
                    left = '.';
                } else {
                    left = floor[i - 1][j - 1];
                }
                if (j == input.length() - 1) {
                    right = '.';
                } else {
                    right = floor[i - 1][j + 1];
                }
                if (isItSafe(left, center, right)) {
                    floor[i][j] = '.';
                    safeTiles++;
                } else {
                    floor[i][j] = '^';
                }
            }
        }

        return safeTiles;
    }

    static boolean isItSafe(char left, char center, char right) {
        if (left == '^' && center == '^' && right == '.') {
            return false;
        } else if (left == '.' && center == '^' && right == '^') {
            return false;
        } else if (left == '.' && center == '.' && right == '^') {
            return false;
        } else if (left == '^' && center == '.' && right == '.') {
            return false;
        }
        return true;
    }


//   Its left and center tiles are traps, but its right tile is not.
//   Its center and right tiles are traps, but its left tile is not.
//   Only its left tile is a trap.
//   Only its right tile is a trap.

    static public void main(String[] args) {
        System.out.println("The solution to AoC18.1 is: " + AoC18pt1(".^^^.^.^^^^^..^^^..^..^..^^..^.^.^.^^.^^....^.^...^.^^.^^.^^..^^..^.^..^^^.^^...^...^^....^^.^^^^^^^", 40));
        System.out.println("The solution to AoC18.2 is: " + AoC18pt1(".^^^.^.^^^^^..^^^..^..^..^^..^.^.^.^^.^^....^.^...^.^^.^^.^^..^^..^.^..^^^.^^...^...^^....^^.^^^^^^^", 400000));

    }
}
