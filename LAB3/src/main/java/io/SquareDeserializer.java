package io;
import shape.Square;
import shape.Shape;
import java.awt.Color;
import java.util.Map;

/**
 * JSON deserializer for {@link Square} shapes.
 */
public class SquareDeserializer implements ShapeDeserializer {
    /**
     * Deserializes a square from a map of JSON properties.
     *
     * @param props property map containing square fields
     * @return reconstructed {@link Square}
     */
    @Override
    public Shape deserialize(Map<String, String> props) {
        return new Square(
                Integer.parseInt(props.get("x")), Integer.parseInt(props.get("y")),
                Integer.parseInt(props.get("side")), new Color(Integer.parseInt(props.get("color")))
        );
    }
}