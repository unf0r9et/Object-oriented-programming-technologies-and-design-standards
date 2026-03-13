package shape;
import java.awt.Color;

public class Rectangle extends Shape {
    private int width, height;

    public Rectangle(int x, int y, int width, int height, Color color) {
        super(x, y, color);
        this.width = width;
        this.height = height;
    }

    public int getWidth() { return width; }
    public void setWidth(int width) { this.width = width; }

    public int getHeight() { return height; }
    public void setHeight(int height) { this.height = height; }

    @Override
    public boolean contains(int px, int py) {
        // Simple bounding box check
        return px >= x && px <= x + width && py >= y && py <= y + height;
    }
}