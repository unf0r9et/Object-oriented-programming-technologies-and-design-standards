package plugin.star;

import factory.ShapeFactory;
import shape.Shape;

import java.awt.Color;

public class StarFactory implements ShapeFactory {

  @Override
  public Shape createShape(int startX, int startY, int currentX, int currentY, Color color) {
    int x = Math.min(startX, currentX);
    int y = Math.min(startY, currentY);
    return new Star(x, y, Math.abs(currentX - startX), Math.abs(currentY - startY), color);
  }
}
