package io;
import shape.Square;

/**
 * JSON serializer for {@link Square} shapes.
 */
public class SquareSerializer implements ShapeSerializer<Square> {
    /**
     * Serializes a square to a JSON properties fragment.
     *
     * @param s square to serialize
     * @return JSON string with square fields
     */
    @Override
    public String serialize(Square s) {
        return String.format("\"type\":\"Square\",\"x\":%d,\"y\":%d,\"side\":%d,\"color\":%d",
                s.getX(), s.getY(), s.getSide(), s.getColor().getRGB());
    }
}