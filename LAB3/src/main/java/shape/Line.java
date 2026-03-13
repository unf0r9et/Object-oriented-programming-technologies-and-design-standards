package shape;
import java.awt.Color;

public class Line extends Shape {
    private int x2, y2;

    public Line(int x, int y, int x2, int y2, Color color) {
        super(x, y, color);
        this.x2 = x2;
        this.y2 = y2;
    }

    public int getX2() { return x2; }
    public void setX2(int x2) { this.x2 = x2; }
    public int getY2() { return y2; }
    public void setY2(int y2) { this.y2 = y2; }

    @Override
    public boolean contains(int px, int py) {
        // Check if point is within a bounding box of the line with a small margin
        int minX = Math.min(x, x2) - 5;
        int maxX = Math.max(x, x2) + 5;
        int minY = Math.min(y, y2) - 5;
        int maxY = Math.max(y, y2) + 5;
        return px >= minX && px <= maxX && py >= minY && py <= maxY;
    }
}