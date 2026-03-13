package io;
import shape.Line;
import shape.Shape;
import java.awt.Color;
import java.util.Map;

/**
 * JSON deserializer for {@link Line} shapes.
 */
public class LineDeserializer implements ShapeDeserializer {
    /**
     * Deserializes a line from a map of JSON properties.
     *
     * @param props property map containing line fields
     * @return reconstructed {@link Line}
     */
    @Override
    public Shape deserialize(Map<String, String> props) {
        return new Line(
                Integer.parseInt(props.get("x")), Integer.parseInt(props.get("y")),
                Integer.parseInt(props.get("x2")), Integer.parseInt(props.get("y2")),
                new Color(Integer.parseInt(props.get("color")))
        );
    }
}