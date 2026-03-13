package io;
import shape.Rectangle;

/**
 * JSON serializer for {@link Rectangle} shapes.
 */
public class RectangleSerializer implements ShapeSerializer<Rectangle> {
    /**
     * Serializes a rectangle to a JSON properties fragment.
     *
     * @param r rectangle to serialize
     * @return JSON string with rectangle fields
     */
    @Override
    public String serialize(Rectangle r) {
        return String.format("\"type\":\"Rectangle\",\"x\":%d,\"y\":%d,\"width\":%d,\"height\":%d,\"color\":%d",
                r.getX(), r.getY(), r.getWidth(), r.getHeight(), r.getColor().getRGB());
    }
}