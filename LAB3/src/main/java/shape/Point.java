package shape;
import java.awt.Color;

public class Point extends Shape {
    public Point(int x, int y, Color color) { super(x, y, color); }

    @Override
    public boolean contains(int px, int py) {
        // Point is hard to click exactly, give it a 5-pixel margin
        return Math.abs(this.x - px) <= 5 && Math.abs(this.y - py) <= 5;
    }
}