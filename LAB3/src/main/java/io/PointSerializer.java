package io;
import shape.Point;

/**
 * JSON serializer for {@link Point} shapes.
 */
public class PointSerializer implements ShapeSerializer<Point> {
    /**
     * Serializes a point to a JSON properties fragment.
     *
     * @param p point to serialize
     * @return JSON string with point fields
     */
    @Override
    public String serialize(Point p) {
        return String.format("\"type\":\"Point\",\"x\":%d,\"y\":%d,\"color\":%d",
                p.getX(), p.getY(), p.getColor().getRGB());
    }
}