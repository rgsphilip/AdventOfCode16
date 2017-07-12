/**
 * Created by rPhilip on 7/2/17.
 */
public class LineSegment {
    int xCordStart;
    int yCordStart;
    int xCordEnd;
    int yCordEnd;
    boolean isHorizontal;

    public LineSegment(int xCordStart, int yCordStart, int xCordEnd, int yCordEnd) {
        this.xCordStart = xCordStart;
        this.yCordStart = yCordStart;

        this.xCordEnd = xCordEnd;
        this.yCordEnd = yCordEnd;

        isHorizontal = (yCordStart == yCordEnd);
    }

    public int xStart() {
        return xCordStart;
    }

    public int yStart() {
        return yCordStart;
    }

    public int xEnd() {
        return xCordEnd;
    }

    public int yEnd() {
        return yCordEnd;
    }

    public boolean isHorizontal() {
        return isHorizontal;
    }

    public boolean doLinesIntersect(LineSegment line1, LineSegment line2) {
        if (line1.isHorizontal() && line2.isHorizontal()) {
            if (line1.yCordStart == line2.yCordStart) {
                //do they share any xs


            } else {
                return false;
            }
        } else if (!line1.isHorizontal() && !line2.isHorizontal()) {
            if (line1.xCordStart == line2.xCordStart) {
                //do they share any ys
            } else {
                return false;
            }
        } else {
            //lines are perpendicular
            LineSegment hLine = line1;
            LineSegment vLine = line2;
            if (line2.isHorizontal()) {
                hLine = line2;
                vLine = line1;
            }

            //if (hLine.yStart() <= vLine.xStart() && hLine.yStart() )

        }
        return false;
    }
}
