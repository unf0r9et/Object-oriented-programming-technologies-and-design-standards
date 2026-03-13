package io;
import shape.Square;
import shape.Shape;
import java.awt.Color;
import java.util.Map;

public class SquareDeserializer implements ShapeDeserializer {
    @Override
    public Shape deserialize(Map<String, String> props) {
        return new Square(
                Integer.parseInt(props.get("x")), Integer.parseInt(props.get("y")),
                Integer.parseInt(props.get("side")), new Color(Integer.parseInt(props.get("color")))
        );
    }
}