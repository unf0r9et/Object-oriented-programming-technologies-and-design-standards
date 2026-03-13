package shape;
import java.awt.Color;

/**
 * Abstract base class for all geometric shapes.
 * Stores only data (coordinates and color). It DOES NOT contain drawing methods.
 */
public abstract class Shape {
    protected int x, y;
    protected Color color;

    /**
     * Constructor to initialize basic shape properties.
     * @param x starting X coordinate
     * @param y starting Y coordinate
     * @param color shape color
     */
    public Shape(int x, int y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public Color getColor() { return color; }
}