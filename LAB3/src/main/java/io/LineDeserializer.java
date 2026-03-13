package io;
import shape.Line;
import shape.Shape;
import java.awt.Color;
import java.util.Map;

public class LineDeserializer implements ShapeDeserializer {
    @Override
    public Shape deserialize(Map<String, String> props) {
        return new Line(
                Integer.parseInt(props.get("x")), Integer.parseInt(props.get("y")),
                Integer.parseInt(props.get("x2")), Integer.parseInt(props.get("y2")),
                new Color(Integer.parseInt(props.get("color")))
        );
    }
}