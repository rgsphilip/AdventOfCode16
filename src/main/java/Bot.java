/**
 * Created by rPhilip on 7/22/17.
 */
public class Bot {
    int lowChip = -1;
    int highChip = -1;
    boolean hasTwoChips;
    boolean hasOneChip;
    boolean hasInstruction;
    Instruction[] instructions = new Instruction[2]; //low first always

    Bot() {
        hasTwoChips = false;
        hasOneChip = false;
        hasInstruction = false;
    }

    void giveBotChip(int chip) {
        if (!hasOneChip) {
            highChip = chip;
            hasOneChip = true;
            return;
        }
        hasTwoChips = true;
        if (chip > highChip) {
            lowChip = highChip;
            highChip = chip;
        } else {
            lowChip = chip;
        }
    }

    void giveBotInstruction(Instruction giveLow, Instruction giveHigh) {
        instructions[0] = giveLow;
        instructions[1] = giveHigh;
        hasInstruction = true;
    }

    public Instruction[] getInstructions() {
        return instructions;
    }

    boolean canBotAct() {
        return hasInstruction && hasTwoChips;
    }

    int[] takeChipsFromBot() {
        int[] takenChips = new int[]{lowChip, highChip};
        lowChip = -1;
        highChip = -1;
        hasOneChip = false;
        hasTwoChips = false;
        hasInstruction = false;
        return takenChips;
    }

}
