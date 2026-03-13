package io;
import shape.Point;

public class PointSerializer implements ShapeSerializer<Point> {
    @Override
    public String serialize(Point p) {
        return String.format("\"type\":\"Point\",\"x\":%d,\"y\":%d,\"color\":%d",
                p.getX(), p.getY(), p.getColor().getRGB());
    }
}