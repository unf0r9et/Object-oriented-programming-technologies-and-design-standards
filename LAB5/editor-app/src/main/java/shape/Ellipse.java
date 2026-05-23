package shape;
import java.awt.Color;

/**
 * Axis-aligned ellipse defined by its top-left corner and width/height.
 */
public class Ellipse extends Shape {
    protected int width, height;

    /**
     * Creates a new ellipse.
     *
     * @param x      x-coordinate of the top-left corner
     * @param y      y-coordinate of the top-left corner
     * @param width  ellipse width in pixels
     * @param height ellipse height in pixels
     * @param color  color used for rendering
     */
    public Ellipse(int x, int y, int width, int height, Color color) {
        super(x, y, color);
        this.width = width;
        this.height = height;
    }

    /**
     * Returns the ellipse width.
     *
     * @return width in pixels
     */
    public int getWidth() { return width; }

    /**
     * Sets the ellipse width.
     *
     * @param width new width in pixels
     */
    public void setWidth(int width) { this.width = width; }

    /**
     * Returns the ellipse height.
     *
     * @return height in pixels
     */
    public int getHeight() { return height; }

    /**
     * Sets the ellipse height.
     *
     * @param height new height in pixels
     */
    public void setHeight(int height) { this.height = height; }

    /**
     * Performs a simple bounding-box check to determine whether the point lies
     * within the ellipse area (sufficient for selection purposes).
     *
     * @param px x-coordinate of the point
     * @param py y-coordinate of the point
     * @return {@code true} if the point is inside the bounding box, {@code false} otherwise
     */
    @Override
    public boolean contains(int px, int py) {
        // Simple bounding box check for selection
        return px >= x && px <= x + width && py >= y && py <= y + height;
    }
}