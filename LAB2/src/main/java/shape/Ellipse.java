package shape;
import java.awt.Color;

/**
 * Represents an Ellipse shape data.
 */
public class Ellipse extends Shape {
    private int width, height;

    public Ellipse(int x, int y, int width, int height, Color color) {
        super(x, y, color);
        this.width = width;
        this.height = height;
    }

    public int getWidth() { return width; }
    public int getHeight() { return height; }
}