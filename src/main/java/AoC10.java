import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by rPhilip on 7/22/17.
 */
public class AoC10 {

    static public int AoC10pt2() throws IOException {

        FileReader fr = new FileReader("AoC10.txt");
        BufferedReader br = new BufferedReader(fr);
        String currentLine;
        HashMap<Integer, Bot> bots = new HashMap<>();
        HashMap<Integer, ArrayList<Integer>> outputs = new HashMap<>();
        while ((currentLine = br.readLine()) != null) {
            int botId;
            Bot currentBot;
            if (currentLine.contains("value")) {
                //parse to see where the value goes to
                String[] words = currentLine.split(" ");
                botId = Integer.parseInt(words[5]);
                currentBot = getBot(botId, bots);
                currentBot.giveBotChip(Integer.parseInt(words[1]));
            } else {
                //bot is given instructions
                String[] words = currentLine.split(" ");
                //need to get:
                //1) what bot is given instruction
                botId = Integer.parseInt(words[1]);
                currentBot = getBot(botId, bots);

                //2) who bot gives lower to
                boolean isLowerABot = false;
                if (words[5].equals("bot")) {
                    isLowerABot = true;
                }
                int lowerId = Integer.parseInt(words[6]);

                //3)who bot gives higher to
                boolean isHigherABot = false;
                if (words[10].equals("bot")) {
                    isHigherABot = true;
                }
                int higherId = Integer.parseInt(words[11]);
                currentBot.giveBotInstruction(new Instruction(lowerId, isLowerABot), new Instruction(higherId, isHigherABot));
            }
            //check to see if bot can do its action

            if (currentBot.canBotAct()) {
                botDoesActions(currentBot, bots, outputs, botId);
            }
        }

        int output0 = getOutput(0, outputs).get(0);
        int output1 = getOutput(1, outputs).get(0);
        int output2 = getOutput(2, outputs).get(0);

        return output0 * output1 * output2;
    }

    static public int AoC10pt1() throws IOException {

        FileReader fr = new FileReader("AoC10.txt");
        BufferedReader br = new BufferedReader(fr);
        String currentLine;
        int botNumber = -1;
        HashMap<Integer, Bot> bots = new HashMap<>();
        HashMap<Integer, ArrayList<Integer>> outputs = new HashMap<>();
        while ((currentLine = br.readLine()) != null) {
            int botId;
            Bot currentBot;
            String[] words = currentLine.split(" ");
            if (currentLine.contains("value")) {
                botId = Integer.parseInt(words[5]);
                currentBot = getBot(botId, bots);
                currentBot.giveBotChip(Integer.parseInt(words[1]));
            } else {
                //bot is given instructions
                //need to get:
                //1) what bot is given instruction
                botId = Integer.parseInt(words[1]);
                currentBot = getBot(botId, bots);

                //2) who bot gives lower to
                boolean isLowerABot = false;
                if (words[5].equals("bot")) {
                    isLowerABot = true;
                }
                int lowerId = Integer.parseInt(words[6]);

                //3)who bot gives higher to
                boolean isHigherABot = false;
                if (words[10].equals("bot")) {
                    isHigherABot = true;
                }
                int higherId = Integer.parseInt(words[11]);
                currentBot.giveBotInstruction(new Instruction(lowerId, isLowerABot), new Instruction(higherId, isHigherABot));
            }
            if (currentBot.canBotAct()) {
                int number = botDoesActions(currentBot, bots, outputs, botId);
                if (number > 0) {
                    botNumber= number;
                }
            }
        }

        return botNumber;
    }

    static int botDoesActions(Bot bot, HashMap<Integer, Bot> bots, HashMap<Integer, ArrayList<Integer>> outputs, int botId) {
        int botNumber = -1;
        if ((bot.highChip == 61 && bot.lowChip == 17) || (bot.highChip == 17 && bot.lowChip == 61)) {
            botNumber = botId;
        }
        Instruction[] instructions = bot.getInstructions();
        Instruction lowerInstruction = instructions[0];
        Instruction higherInstruction = instructions[1];
        int[] chipsToGive = bot.takeChipsFromBot();
        if (lowerInstruction.isBot()) {
            //get bot
            Bot getsLowerChip = getBot(lowerInstruction.getIdNumber(), bots);
            getsLowerChip.giveBotChip(chipsToGive[0]);

        } else {
            ArrayList<Integer> output = getOutput(lowerInstruction.getIdNumber(), outputs);
            output.add(chipsToGive[0]);
        }

        if (higherInstruction.isBot()) {
            //get bot
            Bot getsHigherChip = getBot(higherInstruction.getIdNumber(), bots);
            getsHigherChip.giveBotChip(chipsToGive[1]);
        } else {
            ArrayList<Integer> output = getOutput(higherInstruction.getIdNumber(), outputs);
            output.add(chipsToGive[1]);
        }

        //recursion
        if (lowerInstruction.isBot()) {
            Bot getsLowerChip = getBot(lowerInstruction.getIdNumber(), bots);
            if (getsLowerChip.canBotAct()) {
                int lowerNum = botDoesActions(getsLowerChip, bots, outputs, lowerInstruction.getIdNumber());
                if (lowerNum > 0) {
                    botNumber = lowerNum;
                }
            }
        }
        if (higherInstruction.isBot()) {
            Bot getsHigherChip = getBot(higherInstruction.getIdNumber(), bots);
            if (getsHigherChip.canBotAct()) {
                int higherNum = botDoesActions(getsHigherChip, bots, outputs, higherInstruction.getIdNumber());
                if (higherNum > 0) {
                    botNumber = higherNum;
                }
            }
        }

        return botNumber;

    }

    static Bot getBot(int id, HashMap<Integer, Bot> bots) {
        if (!bots.containsKey(id)) {
            bots.put(id, new Bot());
        }
        return bots.get(id);
    }

    static ArrayList<Integer> getOutput(int id, HashMap<Integer, ArrayList<Integer>> outputs) {
        if (!outputs.containsKey(id)) {
            outputs.put(id, new ArrayList<Integer>());
        }
        return outputs.get(id);
    }

    static public void main(String[] args) throws IOException {
        System.out.println("The solution to AoC10.1 is: " + AoC10pt1());
        System.out.println("The solution to AoC10.2 is: " + AoC10pt2());
    }
}
