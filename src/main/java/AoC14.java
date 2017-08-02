import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * Created by rPhilip on 7/30/17.
 */
public class AoC14 {

    static public int AoC14pt2(String input) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        HashMap<Integer, Character> triplesFound = new HashMap<>();
        HashMap<Integer, Character> quintuplesFound = new HashMap<>();

        int index = -1;
        ArrayList<Integer> indices = new ArrayList<>();

        while (indices.size() < 100) {
            index++;
            String key = input + Integer.toString(index);
            md.update(StandardCharsets.UTF_8.encode(key));
            String result = String.format("%032x", new BigInteger(1, md.digest()));
            for (int i = 0; i < 2016; i++) {
                md.update(StandardCharsets.UTF_8.encode(result));
                result = String.format("%032x", new BigInteger(1, md.digest()));
            }
            //System.out.println(result);
            char findTriples = getMultCharsInARow(3, result);
            if (findTriples != '!') {
                triplesFound.put(index, findTriples);
            }

            char findQuintuples = getMultCharsInARow(5, result);
            if (findQuintuples != '!') {
                quintuplesFound.put(index, findQuintuples);
                for (int i = Math.max(0, index - 1000); i < index; i++) {
                    if (triplesFound.containsKey(i)) {
                        if (triplesFound.get(i) == findQuintuples) {
                            indices.add(i);
                            System.out.println("match found");
                        }
                    }
                }
            }


        }
        Collections.sort(indices);
        for (int i = 0; i < indices.size(); i++) {
            System.out.println(i + ": " + indices.get(i));
        }

        return indices.get(63);
    }

    static public int AoC14pt1(String input) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        HashMap<Integer, Character> triplesFound = new HashMap<>();
        HashMap<Integer, Character> quintuplesFound = new HashMap<>();

        int index = -1;
        ArrayList<Integer> indices = new ArrayList<>();

        while (indices.size() < 200) {
            index++;
            String key = input + Integer.toString(index);
            md.update(StandardCharsets.UTF_8.encode(key));
            String result = String.format("%032x", new BigInteger(1, md.digest()));

            char findTriples = getMultCharsInARow(3, result);
            if (findTriples != '!') {
                triplesFound.put(index, findTriples);

            }

            char findQuintuples = getMultCharsInARow(5, result);
            if (findQuintuples != '!') {
                quintuplesFound.put(index, findQuintuples);
                for (int i = Math.max(0, index - 1000); i < index; i++) {
                    if (triplesFound.containsKey(i)) {
                        if (triplesFound.get(i) == findQuintuples) {
                            indices.add(i);
                        }
                    }
                }
            }


        }
        Collections.sort(indices);
//        for (int i = 0; i < indices.size(); i++) {
//            System.out.println(i + ": " + indices.get(i));
//        }

        return indices.get(63);
    }

    private static char getMultCharsInARow(int multiple, String str) {
        char character = '!';
        outerLoop:
        for (int i = 0; i < str.length() - multiple; i++) {
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
        System.out.println("The solution to AoC14.1 is: " + AoC14pt1("cuanljph"));
        System.out.println("The solution to AoC14.2 is: " + AoC14pt2("abc"));

    }
}
