package shape;
import java.awt.Color;

/**
 * Represents a Circle shape data. Extends Ellipse to reuse width/height logic.
 */
public class Circle extends Ellipse {
    public Circle(int x, int y, int diameter, Color color) {
        super(x, y, diameter, diameter, color);
    }
}