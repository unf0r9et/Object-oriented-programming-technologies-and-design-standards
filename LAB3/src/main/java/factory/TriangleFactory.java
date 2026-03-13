package factory;
import shape.Shape;
import shape.Triangle;
import java.awt.Color;

/**
 * Factory for creating Triangle shapes based on drag distance.
 */
public class TriangleFactory implements ShapeFactory {
    /**
     * {@inheritDoc}
     */
    @Override
    public Shape createShape(int startX, int startY, int currentX, int currentY, Color color) {
        int topX = Math.min(startX, currentX);
        int topY = Math.min(startY, currentY);

        int baseHalf = Math.abs(currentX - startX);
        int leftX = startX - baseHalf;
        int rightX = startX + baseHalf;

        return new Triangle(topX, topY, leftX, currentY, rightX, currentY, color);
    }
}