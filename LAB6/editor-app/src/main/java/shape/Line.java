package shape;
import java.awt.Color;

/**
 * Line segment between two points.
 */
public class Line extends Shape {
    private int x2, y2;

    /**
     * Creates a new line segment.
     *
     * @param x     x-coordinate of the start point
     * @param y     y-coordinate of the start point
     * @param x2    x-coordinate of the end point
     * @param y2    y-coordinate of the end point
     * @param color color used for rendering
     */
    public Line(int x, int y, int x2, int y2, Color color) {
        super(x, y, color);
        this.x2 = x2;
        this.y2 = y2;
    }

    /**
     * Returns the x-coordinate of the end point.
     *
     * @return end x-coordinate
     */
    public int getX2() { return x2; }

    /**
     * Sets the x-coordinate of the end point.
     *
     * @param x2 new end x-coordinate
     */
    public void setX2(int x2) { this.x2 = x2; }

    /**
     * Returns the y-coordinate of the end point.
     *
     * @return end y-coordinate
     */
    public int getY2() { return y2; }

    /**
     * Sets the y-coordinate of the end point.
     *
     * @param y2 new end y-coordinate
     */
    public void setY2(int y2) { this.y2 = y2; }

    /**
     * Checks whether the given point lies near the line segment, using
     * a small bounding box margin to make selection easier.
     *
     * @param px x-coordinate of the point
     * @param py y-coordinate of the point
     * @return {@code true} if the point is within the padded bounding box, {@code false} otherwise
     */
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