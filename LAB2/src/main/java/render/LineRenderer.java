package render;
import shape.Line;
import java.awt.Graphics;

/**
 * Renderer for Line shapes.
 */
public class LineRenderer implements ShapeRenderer<Line> {
    @Override
    public void render(Line line, Graphics g) {
        g.setColor(line.getColor());
        g.drawLine(line.getX(), line.getY(), line.getX2(), line.getY2());
    }
}