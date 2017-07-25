/**
 * Created by rPhilip on 7/22/17.
 */
public class Instruction {
    int idNumber;
    boolean isBot;
    Instruction(int idNumber, boolean isBot) {
        this.idNumber = idNumber;
        this.isBot = isBot;
    }

    public int getIdNumber() {
        return idNumber;
    }

    public boolean isBot() {
        return isBot;
    }
}
