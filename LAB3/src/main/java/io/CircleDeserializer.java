package io;
import shape.Circle;
import shape.Shape;
import java.awt.Color;
import java.util.Map;

public class CircleDeserializer implements ShapeDeserializer {
    @Override
    public Shape deserialize(Map<String, String> props) {
        return new Circle(
                Integer.parseInt(props.get("x")), Integer.parseInt(props.get("y")),
                Integer.parseInt(props.get("diameter")), new Color(Integer.parseInt(props.get("color")))
        );
    }
}