package render;
import shape.Triangle;
import java.awt.Graphics;

/**
 * Renderer for Triangle shapes.
 */
public class TriangleRenderer implements ShapeRenderer<Triangle> {
    /**
     * {@inheritDoc}
     */
    @Override
    public void render(Triangle t, Graphics g) {
        // Configure graphics with the triangle's color
        g.setColor(t.getColor());
        // Draw the three edges connecting the triangle's vertices
        g.drawLine(t.getX(), t.getY(), t.getX2(), t.getY2());
        g.drawLine(t.getX2(), t.getY2(), t.getX3(), t.getY3());
        g.drawLine(t.getX3(), t.getY3(), t.getX(), t.getY());
    }
}