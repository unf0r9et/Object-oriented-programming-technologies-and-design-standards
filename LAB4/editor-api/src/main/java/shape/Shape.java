package shape;
import java.awt.Color;

/**
 * Base class for all drawable shapes on the canvas.
 * Stores the common position and color properties.
 */
public abstract class Shape {
    protected int x, y;
    protected Color color;

    /**
     * Creates a new shape with the given position and color.
     *
     * @param x     x-coordinate of the shape origin
     * @param y     y-coordinate of the shape origin
     * @param color color used for rendering the shape
     */
    public Shape(int x, int y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    /**
     * Returns the x-coordinate of the shape origin.
     *
     * @return x-coordinate
     */
    public int getX() { return x; }

    /**
     * Sets the x-coordinate of the shape origin.
     *
     * @param x new x-coordinate
     */
    public void setX(int x) { this.x = x; }

    /**
     * Returns the y-coordinate of the shape origin.
     *
     * @return y-coordinate
     */
    public int getY() { return y; }

    /**
     * Sets the y-coordinate of the shape origin.
     *
     * @param y new y-coordinate
     */
    public void setY(int y) { this.y = y; }

    /**
     * Returns the current color of the shape.
     *
     * @return shape color
     */
    public Color getColor() { return color; }

    /**
     * Updates the color of the shape.
     *
     * @param color new color to use when rendering
     */
    public void setColor(Color color) { this.color = color; }

    /**
     * Checks if a given point is inside the shape bounds. Used for selection.
     *
     * @param px x-coordinate of the point
     * @param py y-coordinate of the point
     * @return {@code true} if the point lies within the shape, {@code false} otherwise
     */
    public abstract boolean contains(int px, int py);
}