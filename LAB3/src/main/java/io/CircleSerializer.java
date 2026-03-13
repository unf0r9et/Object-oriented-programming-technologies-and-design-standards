package io;
import shape.Circle;

public class CircleSerializer implements ShapeSerializer<Circle> {
    @Override
    public String serialize(Circle c) {
        return String.format("\"type\":\"Circle\",\"x\":%d,\"y\":%d,\"diameter\":%d,\"color\":%d",
                c.getX(), c.getY(), c.getDiameter(), c.getColor().getRGB());
    }
}