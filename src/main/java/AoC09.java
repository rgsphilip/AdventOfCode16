import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by rPhilip on 7/15/17.
 */
public class AoC09 {

    static long AoC09pt2() throws IOException {
        FileReader fr = new FileReader("AoC09.txt");
        BufferedReader br = new BufferedReader(fr);
        String currentLine = br.readLine();
        return calculateSubstringLength(currentLine, 1);
    }

    static long calculateSubstringLength(String str, int numRepetitions) {
        long decompressedLength = 0;

        for(int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '(') {
                String currentStr = str.substring(i);
                // 1) Get the numChars value
                String[] parts = currentStr.split("\\(|\\)");
                String[] dimensions = parts[1].split("x");
                int numChars = Integer.parseInt(dimensions[0]);
                int internalRepetitions = Integer.parseInt(dimensions[1]);

                // 2) Get the substring that numChars applies to
                int charsToTrim = parts[1].length() + 2;
                String markedString = currentStr.substring(charsToTrim, charsToTrim + numChars);

                // 3) Calculate the number of letters in that substring
                decompressedLength += calculateSubstringLength(markedString, internalRepetitions);
                i += charsToTrim + markedString.length() - 1;
            } else {
                decompressedLength++;
            }
        }
        return decompressedLength * numRepetitions;
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
        System.out.println("The solution to AoC09.2 is: " + AoC09pt2());
    }
}

