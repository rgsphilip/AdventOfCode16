import com.opencsv.CSVReader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by rPhilip on 6/27/17.
 */
public class AdventSolutions {

    public static int advent04pt1() throws IOException {

        FileReader fr = new FileReader("AoC04.txt");
        BufferedReader br = new BufferedReader(fr);
        String currentLine;
        int numValid = 0;

        while ((currentLine = br.readLine()) != null) {

            int[] charCounter = new int['z' + 1];
            String roomNum = "";
            String checksum = "";
            boolean inChecksum = false;
            String code = "";
            //line processing
            for (int i = 0; i < currentLine.length(); i++) {
                char c = currentLine.charAt(i);
                if (inChecksum) {
                    if (c != ']') {
                        checksum += c;
                    }
                } else {
                    if (c == '[') {
                        inChecksum = true;
                        continue;
                    }
                    if (c == '-') {
                        code += c;
                        continue;
                    }
                    if (c <= '9' && c >= '0') {
                        roomNum += c;
                        continue;
                    } else {
                        code += c;
                        charCounter[c]++;
                    }
                }
            }

            //count up the results
            assert checksum.length() == 5;

            int[] countArray = new int[5];
            boolean isNumValid = true;

            for (int i = 0; i < 5; i++) {
                //verify that the next letter in the checksum has the max number of letters after its predecessor
                char charInChecksum = checksum.charAt(i);
                int maxVal = getMaxValue(charCounter);
                int actualValOfChar = charCounter[charInChecksum];
                if (maxVal == actualValOfChar) {
                    charCounter[charInChecksum] = 0;
                    countArray[i] = maxVal;
                } else {
                    isNumValid = false;
                    break;
                }
            }

            if (isNumValid) {
                //verify that if there are ties in frequency, that they are appropriately broken by alphabetical order.
                for (int i = 1; i < 5; i++) {
                    if (countArray[i - 1] > countArray[i]) {
                        continue;
                    } else {
                        if (checksum.charAt(i - 1) < checksum.charAt(i)) {
                            continue;
                        } else {
                            isNumValid = false;
                            break;
                        }
                    }
                }
            }

            if (isNumValid) {

                int num = Integer.parseInt(roomNum);
                String decoded = "";
                for (int i = 0; i < code.length(); i++) {
                    decoded += charConverter(code.charAt(i), num);
                }
                if (decoded.contains("north")) {
                    System.out.println(decoded + " " + roomNum);
                }
                numValid += num;
            }

        }

        return numValid;
    }

    static char charConverter(char c, int rotations) {
        if (c == '-') {
            return ' ';
        }
        for (int i = 0; i < rotations; i++) {
            if (c == 'z') {
                c = 'a';
            } else {
                c++;
            }
        }
        return c;
    }

    static int getMaxValue(int[] array) {
        int max = array[0];
        for (int i = 0; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        return max;
    }


    public static int advent03pt2() throws IOException {
        int numTriangles = 0;
        FileReader fr = new FileReader("AoC03.txt");
        BufferedReader br = new BufferedReader(fr);
        String currentLine;
        String[][] currentBlock = new String[3][3];
        int i = 0;
        while ((currentLine = br.readLine()) != null) {
            //parse currentLine into three integers
            //credit for processing the data method at: https://stackoverflow.com/questions/23851668/converting-a-string-with-spaces-to-an-integer-in-java
            if (i == 3) {
                numTriangles += isThisATriangleColumn(currentBlock);
                i = 0;
            }
            currentLine = currentLine.trim();
            String[] currentLineArray = currentLine.split(" +");
            for (int j = 0; j < currentLineArray.length; j++) {
                currentBlock[i][j] = currentLineArray[j];
            }
            i++;

        }
        numTriangles = numTriangles + isThisATriangleColumn(currentBlock);
        return numTriangles;
    }

    public static int isThisATriangleColumn(String[][] currentBlock) {
        int numTriangles = 0;
        for (int j = 0; j < currentBlock[0].length; j++) {
            int largest = 0;
            int side1 = 0;
            int side2 = 0;
            for(int k = 0; k < currentBlock.length; k++) {
                int currentNum = Integer.parseInt(currentBlock[k][j]);
                if (currentNum > largest) {
                    if (side1 != 0) {
                        side2 = largest;
                        largest = currentNum;
                    } else {
                        side1 = largest;
                        largest = currentNum;
                    }
                } else {
                    if (side1 == 0) {
                        side1 = currentNum;
                    } else {
                        side2 = currentNum;
                    }
                }
            }
            if (side1 + side2 > largest) {
                numTriangles++;
            }
        }
        return numTriangles;
    }

    public static int advent03pt1() throws IOException {
        int numTriangles = 0;
        FileReader fr = new FileReader("AoC03.txt");
        BufferedReader br = new BufferedReader(fr);
        String currentLine;
        while ((currentLine = br.readLine()) != null) {
            //parse currentLine into three integers
            //credit for processing the data method at: https://stackoverflow.com/questions/23851668/converting-a-string-with-spaces-to-an-integer-in-java
            currentLine = currentLine.trim();
            String[] currentLineArray = currentLine.split(" +");
            int largest = 0;
            int side1 = 0;
            int side2 = 0;
            for(int i = 0; i < currentLineArray.length; i++) {
                int currentNum = Integer.parseInt(currentLineArray[i]);
                if (currentNum > largest) {
                    if (side1 != 0) {
                        side2 = largest;
                        largest = currentNum;
                    } else {
                        side1 = largest;
                        largest = currentNum;
                    }
                } else {
                    if (side1 == 0) {
                        side1 = currentNum;
                    } else {
                        side2 = currentNum;
                    }
                }
            }
            if (side1 + side2 > largest) {
                numTriangles++;
            }
        }
        return numTriangles;
    }
    public static String advent02pt2() throws IOException {
        String bathroomCode = "";
        char[][] pinPad = {
                {'0', '0', '1', '0', '0'},
                {'0', '2', '3', '4', '0'},
                {'5', '6', '7', '8', '9'},
                {'0', 'A', 'B', 'C', '0'},
                {'0', '0', 'D', '0', '0'}
        };
        //per instructions, our point of reference to start is 5 (i.e., 2, 0)
        int rowCord = 2;
        int colCord = 0;

        int rowSize = pinPad.length - 1;
        int colSize = pinPad[0].length - 1;

        FileReader fr = new FileReader("AoC02.txt");
        BufferedReader br = new BufferedReader(fr);

        String currentLine;
        while ((currentLine = br.readLine()) != null) {
            for (int i = 0; i < currentLine.length(); i++) {
                char direction = currentLine.charAt(i);
                if (direction == 'U') {
                    //decrease rowCord down to 0
                    if (rowCord > 0 && pinPad[rowCord -1][colCord] != '0') {
                        rowCord--;
                    }
                } else if (direction == 'D') {
                    //increase rowCord up to rowSize
                    if (rowCord < rowSize && pinPad[rowCord + 1][colCord] != '0') {
                        rowCord++;
                    }
                } else if (direction == 'L') {
                    //decrease colCord down to 0
                    if (colCord > 0 && pinPad[rowCord][colCord - 1] != '0') {
                        colCord --;
                    }
                } else if (direction == 'R') {
                    //increase colCord up to colSize
                    if (colCord < colSize && pinPad[rowCord][colCord + 1] != '0') {
                        colCord++;
                    }
                }
            }
            bathroomCode += Character.toString(pinPad[rowCord][colCord]);
        }
        return bathroomCode;
    }

    public static String advent02pt1() throws IOException {
        String bathroomCode = "";
        int[][] pinPad = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        //per instructions, our point of reference to start is 5 (i.e., 1, 1)
        int rowCord = 1;
        int colCord = 1;

        //I could just make these to 2, but this way a different pinpad could be put in and we wouldn't
        //need to change any magic constants
        int rowSize = pinPad.length - 1;
        int colSize = pinPad[0].length - 1;

        FileReader fr = new FileReader("AoC02.txt");
        BufferedReader br = new BufferedReader(fr);

        String currentLine;
        while ((currentLine = br.readLine()) != null) {
            for (int i = 0; i < currentLine.length(); i++) {
                char direction = currentLine.charAt(i);
                if (direction == 'U') {
                    //decrease rowCord down to 0
                    if (rowCord > 0) {
                        rowCord--;
                    }
                } else if (direction == 'D') {
                    //increase rowCord up to rowSize
                    if (rowCord < rowSize) {
                        rowCord++;
                    }
                } else if (direction == 'L') {
                    //decrease colCord down to 0
                    if (colCord > 0) {
                        colCord --;
                    }
                } else if (direction == 'R') {
                    //increase colCord up to colSize
                    if (colCord < colSize) {
                        colCord++;
                    }
                }
            }
            bathroomCode += Integer.toString(pinPad[rowCord][colCord]);
        }
        return bathroomCode;
    }


    public static int advent01pt2() throws IOException {
        CSVReader reader = new CSVReader(new FileReader("AoC01.csv"));
        String[] nextLine;
        nextLine = reader.readNext();
        ArrayList<int[]> visitedCoordinates = new ArrayList<>();
        //we start out facing North
        int currentDirNS = 1; //N is 1, S is -1
        int currentDirEW = 0; //E is 1, W is -1

        //we start at coordinates 0, 0
        int distFromOriginX = 0;
        int distFromOriginY = 0;
        visitedCoordinates.add(new int[]{0, 0});
        for (int i = 0; i < nextLine.length; i++) {
            //get the next CSV input, strip out the leading space
            String directionStr = nextLine[i].substring(1);
            //Get the way we are turning
            char direction = directionStr.charAt(0);
            //Strip off the direction
            directionStr = directionStr.substring(1);
            //translate the length to travel into an int from a String
            int lengthToTravel = Integer.parseInt(directionStr);

            if (Math.abs(currentDirNS) == 1) {
                //I'm facing North or South
                if (currentDirNS == 1) {
                    //I'm facing North; if I turn right, I will be facing East.
                    currentDirEW = (direction == 'R') ? 1 : -1;
                } else {
                    //I'm facing South; if I turn left, I will be facing East.
                    currentDirEW = (direction == 'L') ? 1 : -1;
                }
                currentDirNS = 0;
                for (int j = 0; j < lengthToTravel; j++) {
                    distFromOriginX += 1 * currentDirEW;
                    int[] coordToAdd = {distFromOriginX, distFromOriginY};
                    for (int k = 0; k < visitedCoordinates.size(); k++) {
                        if (visitedCoordinates.get(k)[0] == distFromOriginX) {
                            if (visitedCoordinates.get(k)[1] == distFromOriginY) {
                                return Math.abs(distFromOriginX) + Math.abs(distFromOriginY);
                            }
                        }
                    }
                    visitedCoordinates.add(coordToAdd);
                }
            } else {
                //I'm facing East or West
                if (currentDirEW == 1) {
                    //I'm facing East; if I turn left, I will be facing North.
                    currentDirNS = (direction == 'L') ? 1 : -1;
                } else {
                    //I'm facing West; if I turn right, I will be facing North.
                    currentDirNS = (direction == 'R') ? 1 : -1;
                }
                currentDirEW = 0;
                //distFromOriginY = distFromOriginY + (currentDirNS * lengthToTravel);
                for (int j = 0; j < lengthToTravel; j++) {
                    distFromOriginY += 1 * currentDirNS;
                    int[] coordToAdd = {distFromOriginX, distFromOriginY};
                    for (int k = 0; k < visitedCoordinates.size(); k++) {
                        if (visitedCoordinates.get(k)[0] == distFromOriginX) {
                            if (visitedCoordinates.get(k)[1] == distFromOriginY) {
                                return Math.abs(distFromOriginX) + Math.abs(distFromOriginY);
                            }
                        }
                    }
                    visitedCoordinates.add(coordToAdd);
                }
            }
            assert (Math.abs(currentDirEW + currentDirNS) == 1);
        }
        return Math.abs(distFromOriginX) + Math.abs(distFromOriginY);
    }

    public static int advent01pt1() throws IOException {
        CSVReader reader = new CSVReader(new FileReader("AoC01.csv"));
        String[] nextLine;
        nextLine = reader.readNext();

        //we start out facing North
        int currentDirNS = 1; //N is 1, S is -1
        int currentDirEW = 0; //E is 1, W is -1

        //we start at coordinates 0, 0
        int distFromOriginX = 0;
        int distFromOriginY = 0;

        for (int i = 0; i < nextLine.length; i++) {
            //get the next CSV input, strip out the leading space
            String directionStr = nextLine[i].substring(1);
            //Get the way we are turning
            char direction = directionStr.charAt(0);
            //Strip off the direction
            directionStr = directionStr.substring(1);
            //translate the length to travel into an int from a String
            int lengthToTravel = Integer.parseInt(directionStr);

            if (Math.abs(currentDirNS) == 1) {
                //I'm facing North or South
                if (currentDirNS == 1) {
                    //I'm facing North; if I turn right, I will be facing East.
                    currentDirEW = (direction == 'R') ? 1 : -1;
                } else {
                    //I'm facing South; if I turn left, I will be facing East.
                    currentDirEW = (direction == 'L') ? 1 : -1;
                }
                currentDirNS = 0;
                distFromOriginX = distFromOriginX + (currentDirEW * lengthToTravel);
            } else {
                //I'm facing East or West
                if (currentDirEW == 1) {
                    //I'm facing East; if I turn left, I will be facing North.
                    currentDirNS = (direction == 'L') ? 1 : -1;
                } else {
                    //I'm facing West; if I turn right, I will be facing North.
                    currentDirNS = (direction == 'R') ? 1 : -1;
                }
                currentDirEW = 0;
                distFromOriginY = distFromOriginY + (currentDirNS * lengthToTravel);
            }
            assert (Math.abs(currentDirEW + currentDirNS) == 1);
        }
        return Math.abs(distFromOriginX) + Math.abs(distFromOriginY);
    }

}
