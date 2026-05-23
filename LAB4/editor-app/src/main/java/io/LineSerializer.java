package io;
import shape.Line;

/**
 * JSON serializer for {@link Line} shapes.
 */
public class LineSerializer implements ShapeSerializer<Line> {
    /**
     * Serializes a line to a JSON properties fragment.
     *
     * @param l line to serialize
     * @return JSON string with line fields
     */
    @Override
    public String serialize(Line l) {
        return String.format("\"type\":\"Line\",\"x\":%d,\"y\":%d,\"x2\":%d,\"y2\":%d,\"color\":%d",
                l.getX(), l.getY(), l.getX2(), l.getY2(), l.getColor().getRGB());
    }
}