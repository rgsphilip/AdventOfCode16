import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by rPhilip on 7/14/17.
 */
public class AoC08 {

    static public int AoC08pt1() throws IOException{
        char[][] board = new char[6][50];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] = '.';
            }
        }
        FileReader fr = new FileReader("AoC08.txt");
        BufferedReader br = new BufferedReader(fr);
        String currentLine;
        int numLightsOn = 0;

        while ((currentLine = br.readLine()) != null) {
            System.out.println(currentLine);
            if (currentLine.contains("rect")) {
                String dimensions = currentLine.substring(5);
                String[] parts = dimensions.split("x");
                int numCol = Integer.parseInt(parts[0]);
                int numRows = Integer.parseInt(parts[1]);
                for (int i = 0; i < numRows; i++) {
                    for (int j = 0; j < numCol; j++) {
                        board[i][j] = '#';
                    }
                }
            }
            if (currentLine.contains("rotate row")) {
                String trimmed = currentLine.substring(13);
                String parts[] = trimmed.split(" by ");
                //process which row it is
                int row = Integer.parseInt(parts[0]);
                // process by how much
                int rotation = Integer.parseInt(parts[1]);
                //do it
                for (int i = 0; i < rotation; i++) {
                    char prevChar = board[row][0];
                    for (int j = 1; j < board[0].length; j++) {
                        char thisChar = board[row][j];
                        board[row][j] = prevChar;
                        prevChar = thisChar;
                    }
                    board[row][0] = prevChar;
                }

            }
            if (currentLine.contains("rotate column")) {
                String trimmed = currentLine.substring(16);
                String parts[] = trimmed.split(" by ");
                //process which column it is
                int column = Integer.parseInt(parts[0]);
                //process by how much
                int rotation = Integer.parseInt(parts[1]);
                //do it
                for (int i = 0; i < rotation; i++) {
                    char prevChar = board[0][column];
                    for (int j = 1; j < board.length; j++) {
                        char thisChar = board[j][column];
                        board[j][column] = prevChar;
                        prevChar = thisChar;
                    }
                    board[0][column] = prevChar;
                }
            }
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                System.out.print(board[i][j] + " ");
                if (board[i][j] == '#') numLightsOn++;
            }
            System.out.println();
        }




        return numLightsOn;
    }

    static public void main(String[] args) throws IOException {
        System.out.println("The solution to AoC08.1 is: " + AoC08pt1());
    }
}
