package factory;
import shape.Shape;
import shape.Square;
import java.awt.Color;

/**
 * Factory for creating Square shapes.
 */
public class SquareFactory implements ShapeFactory {
    /**
     * {@inheritDoc}
     */
    @Override
    public Shape createShape(int startX, int startY, int currentX, int currentY, Color color) {
        int x = Math.min(startX, currentX);
        int y = Math.min(startY, currentY);
        int side = Math.max(Math.abs(currentX - startX), Math.abs(currentY - startY));
        return new Square(x, y, side, color);
    }
}