package shape;
import java.awt.Color;

/**
 * Circle shape implemented as an {@link Ellipse} with equal width and height.
 */
public class Circle extends Ellipse {

    /**
     * Creates a new circle.
     *
     * @param x        x-coordinate of the top-left corner
     * @param y        y-coordinate of the top-left corner
     * @param diameter circle diameter in pixels
     * @param color    color used for rendering
     */
    public Circle(int x, int y, int diameter, Color color) {
        super(x, y, diameter, diameter, color);
    }

    /**
     * Returns the circle diameter.
     *
     * @return diameter in pixels
     */
    public int getDiameter() { return width; }

    /**
     * Sets the circle diameter, updating both width and height.
     *
     * @param diameter new diameter in pixels
     */
    public void setDiameter(int diameter) {
        this.width = diameter;
        this.height = diameter;
    }
}