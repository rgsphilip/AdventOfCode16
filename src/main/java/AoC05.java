import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by rPhilip on 7/14/17.
 */
public class AoC05 {
    public static String AoC5pt1(String str) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        String password = "";
        int ix = 0;
        while (password.length() < 8) {
            String candidate = str + Integer.toString(ix);
            md.update(StandardCharsets.UTF_8.encode(candidate));
            String result = String.format("%032x", new BigInteger(1, md.digest()));
            if (result.substring(0, 5).equals("00000")) {
                password += result.charAt(5);
                System.out.println(password);
            }
            md.reset();
            ix++;
        }
        return password;
    }

    public static StringBuilder AoC5pt2(String str) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        StringBuilder password = new StringBuilder("        ");
        int ix = 0;
        while (password.toString().contains(" ")) {
            String candidate = str + Integer.toString(ix);
            md.update(StandardCharsets.UTF_8.encode(candidate));
            String result = String.format("%032x", new BigInteger(1, md.digest()));
            if (result.substring(0, 5).equals("00000")) {
                System.out.println(result);
                int index;
                String strIndex = result.substring(5, 6);
                try {
                    index = Integer.parseInt(strIndex);
                } catch (NumberFormatException e) {
                    ix++;
                    continue;

                }
                if (index <= 7 && password.charAt(index) == ' ') {
                    password.setCharAt(index, result.charAt(6));
                }
                System.out.println(password);
            }
            md.reset();
            ix++;
        }
        return password;
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        System.out.println("The solution to AoC05.1 is: " + AoC5pt1("wtnhxymk"));
        System.out.println("The solution to AoC05.2 is: " + AoC5pt2("wtnhxymk"));
    }

}
