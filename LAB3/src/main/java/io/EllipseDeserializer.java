package io;
import shape.Ellipse;
import shape.Shape;
import java.awt.Color;
import java.util.Map;

public class EllipseDeserializer implements ShapeDeserializer {
    @Override
    public Shape deserialize(Map<String, String> props) {
        return new Ellipse(
                Integer.parseInt(props.get("x")), Integer.parseInt(props.get("y")),
                Integer.parseInt(props.get("width")), Integer.parseInt(props.get("height")),
                new Color(Integer.parseInt(props.get("color")))
        );
    }
}