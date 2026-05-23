package factory;
import shape.Circle;
import shape.Shape;
import java.awt.Color;

/**
 * Factory for creating Circle shapes. Uses the largest drag distance for diameter.
 */
public class CircleFactory implements ShapeFactory {
    /**
     * {@inheritDoc}
     */
    @Override
    public Shape createShape(int startX, int startY, int currentX, int currentY, Color color) {
        int x = Math.min(startX, currentX);
        int y = Math.min(startY, currentY);
        // Using max difference to ensure it's a perfect circle
        int diameter = Math.max(Math.abs(currentX - startX), Math.abs(currentY - startY));
        return new Circle(x, y, diameter, color);
    }
}