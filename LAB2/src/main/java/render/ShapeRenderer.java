package render;
import shape.Shape;
import java.awt.Graphics;

/**
 * Interface for rendering a specific type of shape.
 * Separates the drawing logic from the shape data classes.
 *
 * @param <T> The specific Shape type this renderer can draw
 */
public interface ShapeRenderer<T extends Shape> {
    void render(T shape, Graphics g);
}