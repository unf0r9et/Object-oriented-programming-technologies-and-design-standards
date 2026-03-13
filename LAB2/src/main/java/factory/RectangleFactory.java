package factory;
import shape.Rectangle;
import shape.Shape;
import java.awt.Color;

/**
 * Factory for creating Rectangle shapes based on mouse coordinates.
 */
public class RectangleFactory implements ShapeFactory {
    @Override
    public Shape createShape(int startX, int startY, int currentX, int currentY, Color color) {
        int x = Math.min(startX, currentX);
        int y = Math.min(startY, currentY);
        int width = Math.abs(currentX - startX);
        int height = Math.abs(currentY - startY);
        return new Rectangle(x, y, width, height, color);
    }
}