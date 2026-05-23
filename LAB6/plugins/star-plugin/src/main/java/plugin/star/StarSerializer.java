package plugin.star;

import io.ShapeSerializer;

public class StarSerializer implements ShapeSerializer<Star> {

  @Override
  public String serialize(Star s) {
    return String.format(
        "\"type\":\"Star\",\"x\":%d,\"y\":%d,\"width\":%d,\"height\":%d,\"color\":%d",
        s.getX(), s.getY(), s.getWidth(), s.getHeight(), s.getColor().getRGB());
  }
}
