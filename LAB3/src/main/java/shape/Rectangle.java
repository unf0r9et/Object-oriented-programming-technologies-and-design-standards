package shape;
import java.awt.Color;

/**
 * Axis-aligned rectangle shape defined by origin, width and height.
 */
public class Rectangle extends Shape {
    private int width, height;

    /**
     * Creates a new rectangle.
     *
     * @param x      x-coordinate of the top-left corner
     * @param y      y-coordinate of the top-left corner
     * @param width  rectangle width in pixels
     * @param height rectangle height in pixels
     * @param color  fill or stroke color
     */
    public Rectangle(int x, int y, int width, int height, Color color) {
        super(x, y, color);
        this.width = width;
        this.height = height;
    }

    /**
     * Returns the rectangle width.
     *
     * @return width in pixels
     */
    public int getWidth() { return width; }

    /**
     * Sets the rectangle width.
     *
     * @param width new width in pixels
     */
    public void setWidth(int width) { this.width = width; }

    /**
     * Returns the rectangle height.
     *
     * @return height in pixels
     */
    public int getHeight() { return height; }

    /**
     * Sets the rectangle height.
     *
     * @param height new height in pixels
     */
    public void setHeight(int height) { this.height = height; }

    /**
     * Checks whether the given point lies inside the rectangle's bounds.
     *
     * @param px x-coordinate of the point
     * @param py y-coordinate of the point
     * @return {@code true} if the point is within the rectangle, {@code false} otherwise
     */
    @Override
    public boolean contains(int px, int py) {
        // Simple bounding box check
        return px >= x && px <= x + width && py >= y && py <= y + height;
    }
}