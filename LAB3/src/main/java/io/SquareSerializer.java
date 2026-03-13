package io;
import shape.Square;

public class SquareSerializer implements ShapeSerializer<Square> {
    @Override
    public String serialize(Square s) {
        return String.format("\"type\":\"Square\",\"x\":%d,\"y\":%d,\"side\":%d,\"color\":%d",
                s.getX(), s.getY(), s.getSide(), s.getColor().getRGB());
    }
}