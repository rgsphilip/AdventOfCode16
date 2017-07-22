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
        String currentLine;
        long decompressedLength = 0;
        while ((currentLine = br.readLine()) != null) {

            //iterate through the string
            for(int i = 0; i < currentLine.length(); i++) {
                long prevDecompressed = decompressedLength;
                if (currentLine.charAt(i) == '(') {
                    //we need to do multiplication
                    //get the substring from i (we don't need to look back at already-processed letters)
                    String substring = currentLine.substring(i);
                    //get the decompressed length. We set the second param to true because we are looking at the outer
                    //part of the equation
                    decompressedLength += calculateDecompressedSize(substring, true);
                    //get the length of the current section analyzed
                    String[] parts = substring.split("\\(|\\)");
                    String[] dimensions = parts[1].split("x");
                    int increment = Integer.parseInt(dimensions[0]) + parts[1].length() + 1;
                    //increment i so that we don't re-analyze what we've already looked at.
                    i += increment;
                } else {
                    //it's just a letter we count
                    decompressedLength++;
                }
                if (prevDecompressed > decompressedLength) {
                    System.out.println("OVERFLOW!!!");
                }
            }
        }
        return decompressedLength;
    }

    static long calculateDecompressedSize(String str, boolean isOuter) {
        //get the parameters
        String[] parts = str.split("\\(|\\)");
        String[] dimensions = parts[1].split("x");
        int numChars = Integer.parseInt(dimensions[0]);
        int numRepetitions = Integer.parseInt(dimensions[1]);
        int charsToTrim = parts[1].length() + 2;
        String trimmedStr = str.substring(charsToTrim); //takes of first set of parens for this substring

        boolean isAdditive = false;

        String checkStr = trimmedStr.substring(0, numChars); //this is the string within the numChars

        if (trimmedStr.charAt(0) != '(') {
            isAdditive = true;
            trimmedStr = trimmedStr.substring(numChars);
        } else {
            trimmedStr = trimmedStr.substring(0, numChars);
        }

        if (isOuter) {
            if (doesStrContainParens(checkStr)) {
                if (isAdditive) {
                    return numRepetitions * numChars + calculateDecompressedSize(trimmedStr, false);
                } else {
                    return numRepetitions * calculateDecompressedSize(trimmedStr, false);
                }
            }
        } else {
            if (doesStrContainParens(trimmedStr)) {
                if (isAdditive) {
                    return numRepetitions * numChars + calculateDecompressedSize(trimmedStr, false);
                } else {
                    return numRepetitions * calculateDecompressedSize(trimmedStr, false);
                }
            }
        }

        return numChars * numRepetitions;
    }

    static boolean doesStrContainParens(String str) {
        return str.contains("(");
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

