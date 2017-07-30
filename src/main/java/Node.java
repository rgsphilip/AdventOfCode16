/**
 * Created by rPhilip on 7/28/17.
 */
public class Node {
    int xValue;
    int yValue;
    int priority;
    
    Node (int xValue, int yValue, int priority) {
        this.xValue = xValue;
        this.yValue = yValue;
        this.priority = priority;
    }

    public int getxValue() {
        return xValue;
    }

    public void setxValue(int xValue) {
        this.xValue = xValue;
    }

    public int getyValue() {
        return yValue;
    }

    public void setyValue(int yValue) {
        this.yValue = yValue;
    }

    public int getVisitedTimeStamp() {
        return priority;
    }

    public void setPriority(int visitedTimeStamp) {
        this.priority = visitedTimeStamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Node node = (Node) o;

        if (xValue != node.xValue) return false;
        return yValue == node.yValue;

    }

    @Override
    public int hashCode() {
        int result = xValue;
        result = 31 * result + yValue;
        return result;
    }
}
