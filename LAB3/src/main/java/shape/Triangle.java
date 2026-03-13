package shape;
import java.awt.Color;

/**
 * Triangle defined by three vertices.
 */
public class Triangle extends Shape {
    private int x2, y2, x3, y3;

    /**
     * Creates a new triangle.
     *
     * @param x   x-coordinate of the first vertex
     * @param y   y-coordinate of the first vertex
     * @param x2  x-coordinate of the second vertex
     * @param y2  y-coordinate of the second vertex
     * @param x3  x-coordinate of the third vertex
     * @param y3  y-coordinate of the third vertex
     * @param color color used for rendering
     */
    public Triangle(int x, int y, int x2, int y2, int x3, int y3, Color color) {
        super(x, y, color);
        this.x2 = x2; this.y2 = y2; this.x3 = x3; this.y3 = y3;
    }

    /**
     * @return x-coordinate of the second vertex
     */
    public int getX2() { return x2; }

    /**
     * Sets the x-coordinate of the second vertex.
     *
     * @param x2 new x-coordinate
     */
    public void setX2(int x2) { this.x2 = x2; }

    /**
     * @return y-coordinate of the second vertex
     */
    public int getY2() { return y2; }

    /**
     * Sets the y-coordinate of the second vertex.
     *
     * @param y2 new y-coordinate
     */
    public void setY2(int y2) { this.y2 = y2; }

    /**
     * @return x-coordinate of the third vertex
     */
    public int getX3() { return x3; }

    /**
     * Sets the x-coordinate of the third vertex.
     *
     * @param x3 new x-coordinate
     */
    public void setX3(int x3) { this.x3 = x3; }

    /**
     * @return y-coordinate of the third vertex
     */
    public int getY3() { return y3; }

    /**
     * Sets the y-coordinate of the third vertex.
     *
     * @param y3 new y-coordinate
     */
    public void setY3(int y3) { this.y3 = y3; }

    /**
     * Uses a bounding-box approximation to decide whether the given point
     * lies within the triangle (good enough for selection behavior).
     *
     * @param px x-coordinate of the point
     * @param py y-coordinate of the point
     * @return {@code true} if the point is within the bounding box, {@code false} otherwise
     */
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