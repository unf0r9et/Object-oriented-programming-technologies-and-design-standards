package shape;
import java.awt.Color;

/**
 * Represents a Line shape data.
 */
public class Line extends Shape {
    private int x2, y2;

    public Line(int x, int y, int x2, int y2, Color color) {
        super(x, y, color);
        this.x2 = x2;
        this.y2 = y2;
    }

    public int getX2() { return x2; }
    public int getY2() { return y2; }
}