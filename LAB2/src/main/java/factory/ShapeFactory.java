package factory;
import shape.Shape;
import java.awt.Color;

/**
 * Interface for shape creation strategy.
 * This eliminates the need for switch/case blocks when selecting a tool.
 */
public interface ShapeFactory {
    /**
     * Creates a shape based on mouse drag coordinates.
     * @param startX Initial mouse press X
     * @param startY Initial mouse press Y
     * @param currentX Current mouse drag X
     * @param currentY Current mouse drag Y
     * @param color Selected color
     * @return A new instance of a specific Shape
     */
    Shape createShape(int startX, int startY, int currentX, int currentY, Color color);
}