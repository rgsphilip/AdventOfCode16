import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by rPhilip on 7/15/17.
 */
public class AoC09 {

    static int AoC09pt2() throws IOException {
        FileReader fr = new FileReader("AoC09.txt");
        BufferedReader br = new BufferedReader(fr);
        String currentLine;
        int decompressedLength = 0;
        String decompressedString = "";
        while ((currentLine = br.readLine()) != null) {

        }
        return decompressedLength;
    }

    static public int AoC09pt1() throws IOException {

        FileReader fr = new FileReader("AoC09.txt");
        BufferedReader br = new BufferedReader(fr);
        String currentLine;
        int decompressedLength = 0;
        String decompressedString = "";
        while ((currentLine = br.readLine()) != null) {
            for(int i = 0; i < currentLine.length(); i++) {
                if (currentLine.charAt(i) == '(') {
                    //do a bunch of processing
                    //step 1: get num of chars affected
                    String charLenStr = "";
                    int ix = i + 1;
                    while (currentLine.charAt(ix) != 'x') {
                        charLenStr += currentLine.charAt(ix);
                        ix++;
                    }
                    //step 2: get how many repetitions we are dealing with
                    ix++;
                    String numRepsStr = "";
                    while (currentLine.charAt(ix) != ')') {
                        numRepsStr += currentLine.charAt(ix);
                        ix++;
                    }
                    //step 3: get string we are adding in
                    int charLen = Integer.parseInt(charLenStr);
                    int numReps = Integer.parseInt(numRepsStr);
                    ix++;
                    String strToAddIn = "";
                    for (int j = 0; j < charLen; j++) {
                        strToAddIn += currentLine.charAt(ix);
                        ix++;
                    }
                    //step 4: splice in the new string.
                    String firstHalfOfString = currentLine.substring(0, i);
                    String secondHalfOfString = currentLine.substring(ix);
                    String newString = firstHalfOfString;
                    for (int j = 0; j < numReps; j++) {
                        newString += strToAddIn;
                    }
                    //set i to where we will pick up evaluating again
                    i = newString.length() - 1;

                    newString += secondHalfOfString;
                    currentLine = newString;
                }

            }
            decompressedLength = currentLine.length();
            decompressedString = currentLine;
        }
        System.out.println(decompressedString);

        return decompressedLength;

    }


    static public void main(String[] args) throws IOException {
        System.out.println("The solution to AoC09.1 is: " + AoC09pt1());
    }
}

