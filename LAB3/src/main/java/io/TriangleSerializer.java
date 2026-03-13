package io;
import shape.Triangle;

public class TriangleSerializer implements ShapeSerializer<Triangle> {
    @Override
    public String serialize(Triangle t) {
        return String.format("\"type\":\"Triangle\",\"x\":%d,\"y\":%d,\"x2\":%d,\"y2\":%d,\"x3\":%d,\"y3\":%d,\"color\":%d",
                t.getX(), t.getY(), t.getX2(), t.getY2(), t.getX3(), t.getY3(), t.getColor().getRGB());
    }
}