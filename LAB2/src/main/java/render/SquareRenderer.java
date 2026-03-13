package render;
import shape.Square;
import java.awt.Graphics;

/**
 * Renderer for Square shapes.
 */
public class SquareRenderer implements ShapeRenderer<Square> {
    @Override
    public void render(Square square, Graphics g) {
        g.setColor(square.getColor());
        g.drawRect(square.getX(), square.getY(), square.getWidth(), square.getHeight());
    }
}