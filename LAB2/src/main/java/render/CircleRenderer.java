package render;
import shape.Circle;
import java.awt.Graphics;

/**
 * Renderer for Circle shapes.
 */
public class CircleRenderer implements ShapeRenderer<Circle> {
    @Override
    public void render(Circle circle, Graphics g) {
        g.setColor(circle.getColor());
        // Since Circle extends Ellipse, it has getWidth() and getHeight()
        g.drawOval(circle.getX(), circle.getY(), circle.getWidth(), circle.getHeight());
    }
}