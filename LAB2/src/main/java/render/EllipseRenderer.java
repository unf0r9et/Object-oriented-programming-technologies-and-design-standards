package render;
import shape.Ellipse;
import java.awt.Graphics;

/**
 * Renderer for Ellipse shapes.
 */
public class EllipseRenderer implements ShapeRenderer<Ellipse> {
    @Override
    public void render(Ellipse ellipse, Graphics g) {
        g.setColor(ellipse.getColor());
        g.drawOval(ellipse.getX(), ellipse.getY(), ellipse.getWidth(), ellipse.getHeight());
    }
}