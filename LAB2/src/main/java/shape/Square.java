package shape;
import java.awt.Color;

/**
 * Represents a Square shape data. Extends Rectangle.
 */
public class Square extends Rectangle {
    public Square(int x, int y, int side, Color color) {
        super(x, y, side, side, color);
    }
}