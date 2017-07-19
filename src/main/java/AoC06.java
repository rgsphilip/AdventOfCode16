import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by rPhilip on 7/14/17.
 */
public class AoC06 {

    static public String AoC06pt2() throws IOException{
        FileReader fr = new FileReader("AoC06.txt");
        BufferedReader br = new BufferedReader(fr);
        String currentLine;
        String message = "";

        int[][] matrix = new int[8]['z' + 1];

        while ((currentLine = br.readLine()) != null) {
            assert currentLine.length() == 8;
            for (int i = 0; i < 8; i++) {
                char currentLetter = currentLine.charAt(i);
                matrix[i][currentLetter]++;
            }
        }
        for (int i = 0; i < 8; i++) {
            int mostFrequent = Tools.getMinIndexIgnoringZero(matrix[i]);
            message += (char) mostFrequent;
        }

        return message;
    }

    static public String AoC06pt1() throws IOException{
        FileReader fr = new FileReader("AoC06.txt");
        BufferedReader br = new BufferedReader(fr);
        String currentLine;
        String message = "";

        int[][] matrix = new int[8]['z' + 1];

        while ((currentLine = br.readLine()) != null) {
            for (int i = 0; i < 8; i++) {
                char currentLetter = currentLine.charAt(i);
                matrix[i][currentLetter]++;
            }
        }
        for (int i = 0; i < 8; i++) {
            int mostFrequent = Tools.getMaxIndex(matrix[i]);
            message += (char) mostFrequent;
        }

        return message;
    }

    static public void main(String[] args) throws IOException {
        System.out.println("The solution to AoC06.1 is: " + AoC06pt1());
        System.out.println("The solution to AoC06.2 is: " + AoC06pt2());
    }

}
