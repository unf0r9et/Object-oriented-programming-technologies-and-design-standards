package io;
import shape.Circle;
import shape.Shape;
import java.awt.Color;
import java.util.Map;

/**
 * JSON deserializer for {@link Circle} shapes.
 */
public class CircleDeserializer implements ShapeDeserializer {
    /**
     * Deserializes a circle from a map of JSON properties.
     *
     * @param props property map containing circle fields
     * @return reconstructed {@link Circle}
     */
    @Override
    public Shape deserialize(Map<String, String> props) {
        return new Circle(
                Integer.parseInt(props.get("x")), Integer.parseInt(props.get("y")),
                Integer.parseInt(props.get("diameter")), new Color(Integer.parseInt(props.get("color")))
        );
    }
}