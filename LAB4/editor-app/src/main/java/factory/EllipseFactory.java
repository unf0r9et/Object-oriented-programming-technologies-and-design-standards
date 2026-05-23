package factory;
import shape.Ellipse;
import shape.Shape;
import java.awt.Color;

/**
 * Factory for creating Ellipse shapes.
 */
public class EllipseFactory implements ShapeFactory {
    /**
     * {@inheritDoc}
     */
    @Override
    public Shape createShape(int startX, int startY, int currentX, int currentY, Color color) {
        int x = Math.min(startX, currentX);
        int y = Math.min(startY, currentY);
        int width = Math.abs(currentX - startX);
        int height = Math.abs(currentY - startY);
        return new Ellipse(x, y, width, height, color);
    }
}