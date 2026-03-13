package shape;
import java.awt.Color;

public abstract class Shape {
    protected int x, y;
    protected Color color;

    public Shape(int x, int y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public int getX() { return x; }
    public void setX(int x) { this.x = x; }

    public int getY() { return y; }
    public void setY(int y) { this.y = y; }

    public Color getColor() { return color; }
    public void setColor(Color color) { this.color = color; }

    /**
     * Checks if a given point is inside the shape bounds. Used for selection.
     */
    public abstract boolean contains(int px, int py);
}