package io;
import shape.Ellipse;

public class EllipseSerializer implements ShapeSerializer<Ellipse> {
    @Override
    public String serialize(Ellipse e) {
        return String.format("\"type\":\"Ellipse\",\"x\":%d,\"y\":%d,\"width\":%d,\"height\":%d,\"color\":%d",
                e.getX(), e.getY(), e.getWidth(), e.getHeight(), e.getColor().getRGB());
    }
}