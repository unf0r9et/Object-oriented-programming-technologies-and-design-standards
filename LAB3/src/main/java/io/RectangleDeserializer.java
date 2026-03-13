package io;
import shape.Rectangle;
import shape.Shape;
import java.awt.Color;
import java.util.Map;

public class RectangleDeserializer implements ShapeDeserializer {
    @Override
    public Shape deserialize(Map<String, String> props) {
        int x = Integer.parseInt(props.get("x"));
        int y = Integer.parseInt(props.get("y"));
        int w = Integer.parseInt(props.get("width"));
        int h = Integer.parseInt(props.get("height"));
        Color c = new Color(Integer.parseInt(props.get("color")));
        return new Rectangle(x, y, w, h, c);
    }
}