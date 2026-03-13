package shape;
import java.awt.Color;

/**
 * Represents a Rectangle shape data.
 */
public class Rectangle extends Shape {
    private int width, height;

    public Rectangle(int x, int y, int width, int height, Color color) {
        super(x, y, color);
        this.width = width;
        this.height = height;
    }

    public int getWidth() { return width; }
    public int getHeight() { return height; }
}