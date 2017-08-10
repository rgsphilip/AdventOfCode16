/**
 * Created by rPhilip on 8/7/17.
 */
public class AoC16 {

    static public String AoC16pt1(String input, int discsize) {
        char[] dragonCurve = input.toCharArray();
        while (dragonCurve.length < discsize) {
            dragonCurve = getDragon(dragonCurve, discsize);
        }
        char[] checksum = dragonCurve;
        int checksumIx = 0;
        while (checksum.length % 2 == 0) {
            char[] newChecksum = new char[checksum.length / 2];
            for (int i = 0; i < checksum.length - 1; i += 2) {
                char firstChar = checksum[i];
                char secondChar = checksum[i + 1];
                char nextChar = calculateChecksum(firstChar, secondChar);
                newChecksum[checksumIx] = nextChar;
                checksumIx++;
            }
            checksum = newChecksum;
            checksumIx = 0;
        }

        return new String(checksum);
    }

    static char[] getDragon(char[] input, int discSize) {
        int outputSize = Math.min(input.length * 2 + 1, discSize);
        char[] output = new char[outputSize];
        for (int i = 0; i < input.length; i++) {
            output[i] = input[i];
        }
        output[input.length] = '0';
        int inputIx = input.length - 1;
        for (int i = input.length + 1; i < output.length; i++) {
            if (input[inputIx] == '1') {
                output[i] = '0';
            } else {
                output[i] = '1';
            }
            inputIx--;
        }
        return output;
    }

    static char calculateChecksum(char a, char b) {
        if (a == b) {
            return '1';
        }
        return '0';
    }


    static public void main(String[] args) {
        System.out.println("The solution to AoC16.1 is: " + AoC16pt1("11011110011011101", 272));
        System.out.println("The solution to AoC16.2 is: " + AoC16pt1("11011110011011101", 35651584));

    }
}
