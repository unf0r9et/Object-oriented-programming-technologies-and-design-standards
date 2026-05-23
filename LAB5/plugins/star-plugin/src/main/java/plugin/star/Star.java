package plugin.star;

import shape.Shape;

import java.awt.Color;
import java.awt.Polygon;

/** Five-pointed star inscribed in a bounding box (plugin shape). */
public class Star extends Shape {

  private int width;
  private int height;

  public Star(int x, int y, int width, int height, Color color) {
    super(x, y, color);
    this.width = width;
    this.height = height;
  }

  public int getWidth() {
    return width;
  }

  public void setWidth(int width) {
    this.width = width;
  }

  public int getHeight() {
    return height;
  }

  public void setHeight(int height) {
    this.height = height;
  }

  /** Builds a 10-vertex star polygon (outer and inner points). */
  public Polygon toPolygon() {
    int cx = x + width / 2;
    int cy = y + height / 2;
    int outerRx = Math.max(1, width / 2);
    int outerRy = Math.max(1, height / 2);
    int innerRx = outerRx / 2;
    int innerRy = outerRy / 2;
    int[] xs = new int[10];
    int[] ys = new int[10];
    for (int i = 0; i < 10; i++) {
      double angle = Math.toRadians(-90 + i * 36);
      int rx = (i % 2 == 0) ? outerRx : innerRx;
      int ry = (i % 2 == 0) ? outerRy : innerRy;
      xs[i] = cx + (int) (rx * Math.cos(angle));
      ys[i] = cy + (int) (ry * Math.sin(angle));
    }
    return new Polygon(xs, ys, 10);
  }

  @Override
  public boolean contains(int px, int py) {
    return toPolygon().contains(px, py);
  }
}
