package shape;
import java.awt.Color;

public class Triangle extends Shape {
    private int x2, y2, x3, y3;

    public Triangle(int x, int y, int x2, int y2, int x3, int y3, Color color) {
        super(x, y, color);
        this.x2 = x2; this.y2 = y2; this.x3 = x3; this.y3 = y3;
    }

    public int getX2() { return x2; } public void setX2(int x2) { this.x2 = x2; }
    public int getY2() { return y2; } public void setY2(int y2) { this.y2 = y2; }
    public int getX3() { return x3; } public void setX3(int x3) { this.x3 = x3; }
    public int getY3() { return y3; } public void setY3(int y3) { this.y3 = y3; }

    @Override
    public boolean contains(int px, int py) {
        // Check bounding box for simplicity in selection
        int minX = Math.min(x, Math.min(x2, x3));
        int maxX = Math.max(x, Math.max(x2, x3));
        int minY = Math.min(y, Math.min(y2, y3));
        int maxY = Math.max(y, Math.max(y2, y3));
        return px >= minX && px <= maxX && py >= minY && py <= maxY;
    }
}