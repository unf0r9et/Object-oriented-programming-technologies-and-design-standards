package render;
import shape.Line;
import java.awt.Graphics;

/**
 * Renderer for Line shapes.
 */
public class LineRenderer implements ShapeRenderer<Line> {
    /**
     * {@inheritDoc}
     */
    @Override
    public void render(Line line, Graphics g) {
        // Configure graphics with the line's color
        g.setColor(line.getColor());
        // Draw a line segment between its two endpoints
        g.drawLine(line.getX(), line.getY(), line.getX2(), line.getY2());
    }
}