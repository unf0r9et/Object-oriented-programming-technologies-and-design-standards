package io;
import shape.Rectangle;

public class RectangleSerializer implements ShapeSerializer<Rectangle> {
    @Override
    public String serialize(Rectangle r) {
        return String.format("\"type\":\"Rectangle\",\"x\":%d,\"y\":%d,\"width\":%d,\"height\":%d,\"color\":%d",
                r.getX(), r.getY(), r.getWidth(), r.getHeight(), r.getColor().getRGB());
    }
}