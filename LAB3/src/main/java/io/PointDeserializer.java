package io;
import shape.Point;
import shape.Shape;
import java.awt.Color;
import java.util.Map;

/**
 * JSON deserializer for {@link Point} shapes.
 */
public class PointDeserializer implements ShapeDeserializer {
    /**
     * Deserializes a point from a map of JSON properties.
     *
     * @param props property map containing point fields
     * @return reconstructed {@link Point}
     */
    @Override
    public Shape deserialize(Map<String, String> props) {
        return new Point(
                Integer.parseInt(props.get("x")), Integer.parseInt(props.get("y")),
                new Color(Integer.parseInt(props.get("color")))
        );
    }
}