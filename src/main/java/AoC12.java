import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by rPhilip on 7/24/17.
 */
public class AoC12 {
    static public int AoC12pt2() throws IOException {

        FileReader fr = new FileReader("AoC12.txt");
        BufferedReader br = new BufferedReader(fr);
        String currentLine;
        ArrayList<String> commands = new ArrayList<>();
        while ((currentLine = br.readLine()) != null) {
            commands.add(currentLine);
        }
        HashMap<String, Integer> registers = new HashMap<>();

        registers.put("c", 1);

        //time to do some parsing!
        for (int i = 0; i < commands.size(); i++) {
            String currentCommand = commands.get(i);
            String[] parsedCommand = currentCommand.split(" ");
            switch (parsedCommand[0]) {
                case "cpy":
                    //allocate the thing to the registers hashmap
                    try {
                        registers.put(parsedCommand[2], Integer.parseInt(parsedCommand[1]));
                    } catch (NumberFormatException e) {
                        registers.put(parsedCommand[2], registers.get(parsedCommand[1]));
                    }
                    break;
                case "jnz":
                    //jump; use i to jump around, only if first arg is not 0
                    try {
                        if (Integer.parseInt(parsedCommand[1]) != 0) {
                            i += Integer.parseInt(parsedCommand[2]) - 1;
                        }
                    } catch (NumberFormatException e) {
                        if (registers.containsKey(parsedCommand[1]) && registers.get(parsedCommand[1]) != 0) {
                            i += Integer.parseInt(parsedCommand[2]) - 1;
                        }
                    }
                    break;
                case "inc":
                    //increase the register
                    registers.put(parsedCommand[1], registers.get(parsedCommand[1]) + 1);
                    break;
                case "dec":
                    //decrease the register
                    registers.put(parsedCommand[1], registers.get(parsedCommand[1]) - 1);
                    break;
                default:
                    System.out.println("something bad happened");
                    break;
            }
        }

        return registers.get("a");
    }

    static public int AoC12pt1() throws IOException {

        FileReader fr = new FileReader("AoC12.txt");
        BufferedReader br = new BufferedReader(fr);
        String currentLine;
        ArrayList<String> commands = new ArrayList<>();
        while ((currentLine = br.readLine()) != null) {
            commands.add(currentLine);
        }
        HashMap<String, Integer> registers = new HashMap<>();

        //time to do some parsing!
        for (int i = 0; i < commands.size(); i++) {
            String currentCommand = commands.get(i);

            String[] parsedCommand = currentCommand.split(" ");
            switch (parsedCommand[0]) {
                case "cpy":
                    //allocate the thing to the registers hashmap
                    try {
                        registers.put(parsedCommand[2], Integer.parseInt(parsedCommand[1]));
                    } catch (NumberFormatException e) {
                        registers.put(parsedCommand[2], registers.get(parsedCommand[1]));
                    }
                    break;
                case "jnz":
                    //jump; use i to jump around, only if first arg is not 0
                    try {
                        if (Integer.parseInt(parsedCommand[1]) != 0) {
                            i += Integer.parseInt(parsedCommand[2]) - 1;
                        }
                    } catch (NumberFormatException e) {
                        if (registers.containsKey(parsedCommand[1]) && registers.get(parsedCommand[1]) != 0) {
                            i += Integer.parseInt(parsedCommand[2]) - 1;
                        }
                    }
                    break;
                case "inc":
                    //increase the register
                    registers.put(parsedCommand[1], registers.get(parsedCommand[1]) + 1);
                    break;
                case "dec":
                    //decrease the register
                    registers.put(parsedCommand[1], registers.get(parsedCommand[1]) - 1);
                    break;
                default:
                    System.out.println("something bad happened");
                    break;
            }
        }

        return registers.get("a");
    }

    static public void main(String[] args) throws IOException {
        System.out.println("The solution to AoC12.1 is: " + AoC12pt1());
        System.out.println("The solution to AoC12.2 is: " + AoC12pt2());
    }
}
