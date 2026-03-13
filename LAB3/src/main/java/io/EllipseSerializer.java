package io;
import shape.Ellipse;

/**
 * JSON serializer for {@link Ellipse} shapes.
 */
public class EllipseSerializer implements ShapeSerializer<Ellipse> {
    /**
     * Serializes an ellipse to a JSON properties fragment.
     *
     * @param e ellipse to serialize
     * @return JSON string with ellipse fields
     */
    @Override
    public String serialize(Ellipse e) {
        return String.format("\"type\":\"Ellipse\",\"x\":%d,\"y\":%d,\"width\":%d,\"height\":%d,\"color\":%d",
                e.getX(), e.getY(), e.getWidth(), e.getHeight(), e.getColor().getRGB());
    }
}