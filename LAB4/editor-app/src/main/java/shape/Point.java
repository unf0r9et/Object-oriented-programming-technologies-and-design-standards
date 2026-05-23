package shape;
import java.awt.Color;

/**
 * Single point shape on the canvas.
 */
public class Point extends Shape {

    /**
     * Creates a new point.
     *
     * @param x     x-coordinate of the point
     * @param y     y-coordinate of the point
     * @param color color used for rendering
     */
    public Point(int x, int y, Color color) { super(x, y, color); }

    /**
     * Checks whether the given point is close enough to this point
     * to be considered a hit for selection.
     *
     * @param px x-coordinate of the click
     * @param py y-coordinate of the click
     * @return {@code true} if the click is within a 5-pixel margin, {@code false} otherwise
     */
    @Override
    public boolean contains(int px, int py) {
        // Point is hard to click exactly, give it a 5-pixel margin
        return Math.abs(this.x - px) <= 5 && Math.abs(this.y - py) <= 5;
    }
}