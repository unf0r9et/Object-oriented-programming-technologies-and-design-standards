package render;
import shape.Point;
import java.awt.Graphics;

/**
 * Renderer for Point shapes.
 */
public class PointRenderer implements ShapeRenderer<Point> {
    /**
     * {@inheritDoc}
     */
    @Override
    public void render(Point point, Graphics g) {
        // Configure graphics with the point's color
        g.setColor(point.getColor());
        // Draw the point as a 1-pixel line from the point to itself
        g.drawLine(point.getX(), point.getY(), point.getX(), point.getY());
    }
}