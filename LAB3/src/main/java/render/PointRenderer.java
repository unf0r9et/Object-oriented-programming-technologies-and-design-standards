package render;
import shape.Point;
import java.awt.Graphics;

/**
 * Renderer for Point shapes.
 */
public class PointRenderer implements ShapeRenderer<Point> {
    @Override
    public void render(Point point, Graphics g) {
        g.setColor(point.getColor());
        g.drawLine(point.getX(), point.getY(), point.getX(), point.getY());
    }
}