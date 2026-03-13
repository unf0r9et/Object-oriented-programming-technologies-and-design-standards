package render;
import shape.Ellipse;
import java.awt.Graphics;

/**
 * Renderer for Ellipse shapes.
 */
public class EllipseRenderer implements ShapeRenderer<Ellipse> {
    /**
     * {@inheritDoc}
     */
    @Override
    public void render(Ellipse ellipse, Graphics g) {
        // Configure graphics with the ellipse's color
        g.setColor(ellipse.getColor());
        // Draw the ellipse outline inside the bounding rectangle
        g.drawOval(ellipse.getX(), ellipse.getY(), ellipse.getWidth(), ellipse.getHeight());
    }
}