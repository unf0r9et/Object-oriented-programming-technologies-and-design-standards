package io;
import shape.Point;
import shape.Shape;
import java.awt.Color;
import java.util.Map;

public class PointDeserializer implements ShapeDeserializer {
    @Override
    public Shape deserialize(Map<String, String> props) {
        return new Point(
                Integer.parseInt(props.get("x")), Integer.parseInt(props.get("y")),
                new Color(Integer.parseInt(props.get("color")))
        );
    }
}