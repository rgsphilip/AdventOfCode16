import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by rPhilip on 7/14/17.
 */
public class AoC07 {

    static public int AoC07pt2() throws IOException {
        FileReader fr = new FileReader("AoC07.txt");
        BufferedReader br = new BufferedReader(fr);
        String currentLine;
        int numSupportingSSL = 0;
        while ((currentLine = br.readLine()) != null) {
            String[] parts = currentLine.split("\\[|\\]");
            boolean supportsSSL = false;
            ArrayList<String> foundPalindromeOutsideBrackets = new ArrayList<>();
            ArrayList<String> foundPalindromeInsideBrackets = new ArrayList<>();
            for (int i = 0; i < parts.length; i++) {
                ArrayList<String> palindromes = findThreeLetterPalindrome(parts[i]);
                if (i % 2 == 1) {
                    //we are in the brackets
                    foundPalindromeInsideBrackets.addAll(palindromes);
                } else {
                    //we are not in a bracket section
                    foundPalindromeOutsideBrackets.addAll(palindromes);
                }
            }

            for (String s : foundPalindromeInsideBrackets) {
                String invert = invertThreeLetterPalindrome(s);
                if (foundPalindromeOutsideBrackets.contains(invert)) {
                    supportsSSL = true;
                    break;
                }
            }
            if (supportsSSL) {
                numSupportingSSL++;
            }
        }
        return numSupportingSSL;
    }

    static public int AoC07pt1() throws IOException{
        FileReader fr = new FileReader("AoC07.txt");
        BufferedReader br = new BufferedReader(fr);
        String currentLine;
        int numSupportingTLS = 0;

        while ((currentLine = br.readLine()) != null) {
            String[] parts = currentLine.split("\\[|\\]");
            boolean supportsTLS = false;
            for (int i = 0; i < parts.length; i++) {
                if (i % 2 == 1) {
                    //we are in a bracket section
                    if (findFourLetterPalindrome(parts[i])) {
                        supportsTLS = false;
                        break;
                    }
                } else {
                    //we are not in a bracket section
                    if (findFourLetterPalindrome(parts[i])) {
                        supportsTLS = true;
                    }
                }
            }
            if (supportsTLS) {
                numSupportingTLS++;
            }
        }
        return numSupportingTLS;
    }

    static public void main(String[] args) throws IOException {
        System.out.println("The solution to AoC07.1 is: " + AoC07pt1());
        System.out.println("The solution to AoC07.2 is: " + AoC07pt2());
    }

    static String invertThreeLetterPalindrome(String input) {
        //assumes it is handed a valid palindrome of length 3
        String result = "";
        result += input.charAt(1);
        result += input.charAt(0);
        result += input.charAt(1);
        return result;
    }

    static ArrayList<String> findThreeLetterPalindrome(String input) {
        ArrayList<String> threeLetterPalindromes = new ArrayList<>();
        for (int i = 0; i < input.length() - 2; i++) {
            String candidate = input.substring(i, i + 3);
            if (candidate.charAt(0) == candidate.charAt(2)) {
                if (candidate.charAt(0) != candidate.charAt(1)) {
                    threeLetterPalindromes.add(candidate);
                }
            }
        }
        return threeLetterPalindromes;
    }

    static boolean findFourLetterPalindrome(String input) {
        if (input.length() < 4) return false;
        for (int i = 0; i < input.length() - 3; i++) {
            if (input.charAt(i) == input.charAt(i + 3)) {
                if (input.charAt(i + 1) == input.charAt(i + 2) && (input.charAt(i) != input.charAt(i + 1))) {
                    return true;
                }
            }
        }
        return false;
    }
}
