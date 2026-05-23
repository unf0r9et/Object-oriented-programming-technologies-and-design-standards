package io;
import shape.Circle;

/**
 * JSON serializer for {@link Circle} shapes.
 */
public class CircleSerializer implements ShapeSerializer<Circle> {
    /**
     * Serializes a circle to a JSON properties fragment.
     *
     * @param c circle to serialize
     * @return JSON string with circle fields
     */
    @Override
    public String serialize(Circle c) {
        return String.format("\"type\":\"Circle\",\"x\":%d,\"y\":%d,\"diameter\":%d,\"color\":%d",
                c.getX(), c.getY(), c.getDiameter(), c.getColor().getRGB());
    }
}