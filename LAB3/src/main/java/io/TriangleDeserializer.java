package io;
import shape.Triangle;
import shape.Shape;
import java.awt.Color;
import java.util.Map;

public class TriangleDeserializer implements ShapeDeserializer {
    @Override
    public Shape deserialize(Map<String, String> props) {
        return new Triangle(
                Integer.parseInt(props.get("x")), Integer.parseInt(props.get("y")),
                Integer.parseInt(props.get("x2")), Integer.parseInt(props.get("y2")),
                Integer.parseInt(props.get("x3")), Integer.parseInt(props.get("y3")),
                new Color(Integer.parseInt(props.get("color")))
        );
    }
}