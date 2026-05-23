package plugin.pentagon;

import factory.ShapeFactory;
import shape.Shape;

import java.awt.Color;

/** Creates {@link Pentagon} instances from mouse drag rectangle. */
public class PentagonFactory implements ShapeFactory {

  @Override
  public Shape createShape(int startX, int startY, int currentX, int currentY, Color color) {
    int x = Math.min(startX, currentX);
    int y = Math.min(startY, currentY);
    int w = Math.abs(currentX - startX);
    int h = Math.abs(currentY - startY);
    return new Pentagon(x, y, w, h, color);
  }
}
