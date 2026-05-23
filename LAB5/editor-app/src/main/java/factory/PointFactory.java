package factory;
import shape.Point;
import shape.Shape;
import java.awt.Color;

/**
 * Factory for creating Point shapes.
 */
public class PointFactory implements ShapeFactory {
    /**
     * {@inheritDoc}
     */
    @Override
    public Shape createShape(int startX, int startY, int currentX, int currentY, Color color) {
        return new Point(startX, startY, color); // Point only needs start coordinates
    }
}