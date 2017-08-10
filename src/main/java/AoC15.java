import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by rPhilip on 8/6/17.
 */
public class AoC15 {

    static public int AoC15pt2() throws IOException {

        FileReader fr = new FileReader("AoC15.txt");
        BufferedReader br = new BufferedReader(fr);
        String currentLine;
        ArrayList<String> positions = new ArrayList<>();
        while ((currentLine = br.readLine()) != null) {
            positions.add(currentLine);
        }
        ArrayList<Disc> discs = new ArrayList<>();
        //String parsing
        for (int i = 0; i < positions.size(); i++) {
            String currentDisc = positions.get(i);
            String[] currentSplit = currentDisc.split(" ");
            int discNum = Integer.parseInt(currentSplit[1].substring(1));
            int numPositions = Integer.parseInt(currentSplit[3]);
            int timeReference = Integer.parseInt(currentSplit[6].substring(5, currentSplit[6].length() - 1));
            int positionAtStartTime = Integer.parseInt(currentSplit[11].substring(0, currentSplit[11].length() - 1));
            discs.add(new Disc(discNum, numPositions, timeReference, positionAtStartTime));
        }

        discs.add(new Disc(positions.size() + 1, 11, 0, 0));

        //need to find time at which discs will be at 0
        boolean foundTime = false;
        int time = -1;
        findTime:
        while (!foundTime) {
            time++;
            for (int i = 0; i < discs.size(); i++) {
                Disc currentDisc = discs.get(i);
                int position = time + currentDisc.discNum + currentDisc.posAtTimeRef - currentDisc.timeReference; //this is the time at which the capsule gets to the disc
                position = position % currentDisc.numPositions;
                if (position != 0) {
                    continue findTime;
                }
            }
            foundTime = true;
        }


        return time;
    }

    static public int AoC15pt1() throws IOException {

        FileReader fr = new FileReader("AoC15.txt");
        BufferedReader br = new BufferedReader(fr);
        String currentLine;
        ArrayList<String> positions = new ArrayList<>();
        while ((currentLine = br.readLine()) != null) {
            positions.add(currentLine);
        }
        ArrayList<Disc> discs = new ArrayList<>();
        //String parsing
        for (int i = 0; i < positions.size(); i++) {
            String currentDisc = positions.get(i);
            String[] currentSplit = currentDisc.split(" ");
            int discNum = Integer.parseInt(currentSplit[1].substring(1));
            int numPositions = Integer.parseInt(currentSplit[3]);
            int timeReference = Integer.parseInt(currentSplit[6].substring(5, currentSplit[6].length() - 1));
            int positionAtStartTime = Integer.parseInt(currentSplit[11].substring(0, currentSplit[11].length() - 1));
            discs.add(new Disc(discNum, numPositions, timeReference, positionAtStartTime));
        }

        //need to find time at which discs will be at 0
        boolean foundTime = false;
        int time = -1;
        findTime:
        while (!foundTime) {
            time++;
            for (int i = 0; i < discs.size(); i++) {
                Disc currentDisc = discs.get(i);
                int position = time + currentDisc.discNum + currentDisc.posAtTimeRef - currentDisc.timeReference; //this is the time at which the capsule gets to the disc
                position = position % currentDisc.numPositions;
                if (position != 0) {
                   continue findTime;
                }
            }
            foundTime = true;
        }


        return time;
    }

    static public void main(String[] args) throws IOException {
        System.out.println("The solution to AoC15.1 is: " + AoC15pt1());
        System.out.println("The solution to AoC15.2 is: " + AoC15pt2());
    }
}
