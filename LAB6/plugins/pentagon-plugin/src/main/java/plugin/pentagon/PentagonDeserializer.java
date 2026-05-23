package plugin.pentagon;

import io.ShapeDeserializer;
import shape.Shape;

import java.awt.Color;
import java.util.Map;

/** Restores a pentagon from parsed JSON properties. */
public class PentagonDeserializer implements ShapeDeserializer {

  @Override
  public Shape deserialize(Map<String, String> props) {
    return new Pentagon(
        Integer.parseInt(props.get("x")),
        Integer.parseInt(props.get("y")),
        Integer.parseInt(props.get("width")),
        Integer.parseInt(props.get("height")),
        new Color(Integer.parseInt(props.get("color"))));
  }
}
