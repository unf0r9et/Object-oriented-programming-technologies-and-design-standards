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
    /**
     * Renders the given shape using the provided graphics context.
     *
     * @param shape shape instance to draw
     * @param g     AWT graphics context used for painting
     */
    void render(T shape, Graphics g);
}