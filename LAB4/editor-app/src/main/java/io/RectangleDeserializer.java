package io;
import shape.Rectangle;
import shape.Shape;
import java.awt.Color;
import java.util.Map;

/**
 * JSON deserializer for {@link Rectangle} shapes.
 */
public class RectangleDeserializer implements ShapeDeserializer {
    /**
     * Deserializes a rectangle from a map of JSON properties.
     *
     * @param props property map containing rectangle fields
     * @return reconstructed {@link Rectangle}
     */
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