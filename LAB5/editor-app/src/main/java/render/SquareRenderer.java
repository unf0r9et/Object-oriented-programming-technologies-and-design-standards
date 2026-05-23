package render;
import shape.Square;
import java.awt.Graphics;

/**
 * Renderer for Square shapes.
 */
public class SquareRenderer implements ShapeRenderer<Square> {
    /**
     * {@inheritDoc}
     */
    @Override
    public void render(Square square, Graphics g) {
        // Configure graphics with the square's color
        g.setColor(square.getColor());
        // Draw the square outline using its width/height (which are equal)
        g.drawRect(square.getX(), square.getY(), square.getWidth(), square.getHeight());
    }
}