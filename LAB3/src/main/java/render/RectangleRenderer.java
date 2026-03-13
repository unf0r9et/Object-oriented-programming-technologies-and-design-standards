package render;
import shape.Rectangle;
import java.awt.Graphics;

/**
 * Handles the drawing logic for Rectangle shapes.
 */
public class RectangleRenderer implements ShapeRenderer<Rectangle> {
    @Override
    public void render(Rectangle rect, Graphics g) {
        g.setColor(rect.getColor());
        g.drawRect(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
    }
}