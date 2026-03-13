package io;
import shape.Triangle;

/**
 * JSON serializer for {@link Triangle} shapes.
 */
public class TriangleSerializer implements ShapeSerializer<Triangle> {
    /**
     * Serializes a triangle to a JSON properties fragment.
     *
     * @param t triangle to serialize
     * @return JSON string with triangle fields
     */
    @Override
    public String serialize(Triangle t) {
        return String.format("\"type\":\"Triangle\",\"x\":%d,\"y\":%d,\"x2\":%d,\"y2\":%d,\"x3\":%d,\"y3\":%d,\"color\":%d",
                t.getX(), t.getY(), t.getX2(), t.getY2(), t.getX3(), t.getY3(), t.getColor().getRGB());
    }
}