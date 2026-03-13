package io;
import shape.Line;

public class LineSerializer implements ShapeSerializer<Line> {
    @Override
    public String serialize(Line l) {
        return String.format("\"type\":\"Line\",\"x\":%d,\"y\":%d,\"x2\":%d,\"y2\":%d,\"color\":%d",
                l.getX(), l.getY(), l.getX2(), l.getY2(), l.getColor().getRGB());
    }
}