package plugin.pentagon;

import io.ShapeSerializer;

/** Serializes pentagon fields to JSON fragment. */
public class PentagonSerializer implements ShapeSerializer<Pentagon> {

  @Override
  public String serialize(Pentagon p) {
    return String.format(
        "\"type\":\"Pentagon\",\"x\":%d,\"y\":%d,\"width\":%d,\"height\":%d,\"color\":%d",
        p.getX(), p.getY(), p.getWidth(), p.getHeight(), p.getColor().getRGB());
  }
}
