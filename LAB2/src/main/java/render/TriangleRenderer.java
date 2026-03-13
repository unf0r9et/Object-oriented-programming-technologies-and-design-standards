package render;
import shape.Triangle;
import java.awt.Graphics;

/**
 * Renderer for Triangle shapes.
 */
public class TriangleRenderer implements ShapeRenderer<Triangle> {
    @Override
    public void render(Triangle t, Graphics g) {
        g.setColor(t.getColor());
        g.drawLine(t.getX(), t.getY(), t.getX2(), t.getY2());
        g.drawLine(t.getX2(), t.getY2(), t.getX3(), t.getY3());
        g.drawLine(t.getX3(), t.getY3(), t.getX(), t.getY());
    }
}