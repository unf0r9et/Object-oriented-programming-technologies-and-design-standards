package factory;
import shape.Line;
import shape.Shape;
import java.awt.Color;

/**
 * Factory for creating Line shapes.
 */
public class LineFactory implements ShapeFactory {
    /**
     * {@inheritDoc}
     */
    @Override
    public Shape createShape(int startX, int startY, int currentX, int currentY, Color color) {
        return new Line(startX, startY, currentX, currentY, color);
    }
}