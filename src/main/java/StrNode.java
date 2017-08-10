/**
 * Created by rPhilip on 8/10/17.
 */
public class StrNode extends Node {
    String code;
    StrNode(int xValue, int yValue, int priority, String code) {
        super(xValue, yValue, priority);
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
