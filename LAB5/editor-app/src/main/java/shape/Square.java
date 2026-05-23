package shape;
import java.awt.Color;

/**
 * Square shape implemented as a specialized {@link Rectangle}
 * that keeps width and height equal.
 */
public class Square extends Rectangle {

    /**
     * Creates a new square.
     *
     * @param x     x-coordinate of the top-left corner
     * @param y     y-coordinate of the top-left corner
     * @param side  side length in pixels
     * @param color color used for rendering
     */
    public Square(int x, int y, int side, Color color) {
        super(x, y, side, side, color);
    }

    /**
     * Returns the side length of the square.
     *
     * @return side length in pixels
     */
    public int getSide() { return getWidth(); }

    /**
     * Sets the side length of the square, updating both width and height.
     *
     * @param side new side length in pixels
     */
    public void setSide(int side) {
        setWidth(side);
        setHeight(side);
    }
}