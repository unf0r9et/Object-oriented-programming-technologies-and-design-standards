package render;
import shape.Rectangle;
import java.awt.Graphics;

/**
 * Handles the drawing logic for Rectangle shapes.
 */
public class RectangleRenderer implements ShapeRenderer<Rectangle> {
    /**
     * {@inheritDoc}
     */
    @Override
    public void render(Rectangle rect, Graphics g) {
        // Configure graphics with the rectangle's color
        g.setColor(rect.getColor());
        // Draw the rectangle outline at its position and size
        g.drawRect(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
    }
}