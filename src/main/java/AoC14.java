import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by rPhilip on 7/30/17.
 */
public class AoC14 {

    static public int AoC14pt2(String input) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        HashMap<Integer, String> foundHashes = new HashMap<>();
        int index = -1;
        ArrayList<Integer> indices = new ArrayList<>();

        while (indices.size() < 64) {
            index++;
            String result = "";
            if (foundHashes.containsKey(index)) {
                result = foundHashes.get(index);
            } else {
                String key = input + Integer.toString(index);
                md.update(StandardCharsets.UTF_8.encode(key));
                result = String.format("%032x", new BigInteger(1, md.digest()));
                for (int i = 0; i < 2016; i++) {
                    key = result;
                    md.update(StandardCharsets.UTF_8.encode(key));
                    result = String.format("%032x", new BigInteger(1, md.digest()));
                }
            }

            char findTriple = getMultCharsInARow(3, result);
            if (findTriple != '!') {
                for (int i = index + 1; i <= index + 1000; i++) {
                    String nextResult = "";
                    if (foundHashes.containsKey(i)) {
                        nextResult = foundHashes.get(i);
                    } else {
                        String nextKey = input + Integer.toString(i);
                        md.update(StandardCharsets.UTF_8.encode(nextKey));
                        nextResult = String.format("%032x", new BigInteger(1, md.digest()));
                        for (int j = 0; j < 2016; j++) {
                            nextKey = nextResult;
                            md.update(StandardCharsets.UTF_8.encode(nextKey));
                            nextResult = String.format("%032x", new BigInteger(1, md.digest()));
                        }
                        foundHashes.put(i, nextResult);
                    }

                    if (getMultCharsInARow(5, nextResult) == findTriple) {
                        indices.add(index);
                        //System.out.println("found one");
                        break;
                    }
                }
            }
        }

        return indices.get(63);
    }

    static public int AoC14pt1(String input) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");

        int index = -1;
        ArrayList<Integer> indices = new ArrayList<>();

        while (indices.size() < 64) {
            index++;
            String key = input + Integer.toString(index);
            md.update(StandardCharsets.UTF_8.encode(key));
            String result = String.format("%032x", new BigInteger(1, md.digest()));
            char findTriple = getMultCharsInARow(3, result);
            if (findTriple != '!') {
                for (int i = index + 1; i <= index + 1000; i++) {
                    String nextKey = input + Integer.toString(i);
                    md.update(StandardCharsets.UTF_8.encode(nextKey));
                    String nextResult = String.format("%032x", new BigInteger(1, md.digest()));
                    if (getMultCharsInARow(5, nextResult) == findTriple) {
                        indices.add(index);
                    }
                }
            }
        }

        return indices.get(63);
    }

    private static char getMultCharsInARow(int multiple, String str) {
        char character = '!';
        outerLoop:
        for (int i = 0; i < str.length() - multiple + 1; i++) {
            char currentChar = str.charAt(i);
            for (int j = i + 1; j < i + multiple; j++) {
                if (str.charAt(j) != currentChar) {
                    i = j - 1;
                    continue outerLoop;
                }
            }
            character = currentChar;
            break;
        }

        return character;
    }


    static public void main(String[] args) throws NoSuchAlgorithmException {
        //System.out.println("The solution to AoC14.1 is: " + AoC14pt1("cuanljph"));
        System.out.println("The solution to AoC14.2 is: " + AoC14pt2("cuanljph"));

    }
}
