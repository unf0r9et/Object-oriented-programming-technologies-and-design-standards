package shape;
import java.awt.Color;

/**
 * Represents a Triangle shape data. Stores 3 points.
 */
public class Triangle extends Shape {
    private int x2, y2, x3, y3;

    public Triangle(int x, int y, int x2, int y2, int x3, int y3, Color color) {
        super(x, y, color);
        this.x2 = x2;
        this.y2 = y2;
        this.x3 = x3;
        this.y3 = y3;
    }

    public int getX2() { return x2; }
    public int getY2() { return y2; }
    public int getX3() { return x3; }
    public int getY3() { return y3; }
}