package render;
import shape.Circle;
import java.awt.Graphics;

/**
 * Renderer for Circle shapes.
 */
public class CircleRenderer implements ShapeRenderer<Circle> {
    /**
     * {@inheritDoc}
     */
    @Override
    public void render(Circle circle, Graphics g) {
        // Configure graphics with the circle's color
        g.setColor(circle.getColor());
        // Draw the circle as an oval with equal width and height
        g.drawOval(circle.getX(), circle.getY(), circle.getWidth(), circle.getHeight());
    }
}