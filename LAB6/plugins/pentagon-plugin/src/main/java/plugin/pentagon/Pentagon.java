package plugin.pentagon;

import shape.Shape;

import java.awt.Color;
import java.awt.Polygon;

/**
 * Regular pentagon inscribed in a bounding box (plugin-provided shape class).
 */
public class Pentagon extends Shape {

  private int width;
  private int height;

  /**
   * @param x      top-left of bounding box
   * @param y      top-left of bounding box
   * @param width  bounding box width
   * @param height bounding box height
   * @param color  stroke color
   */
  public Pentagon(int x, int y, int width, int height, Color color) {
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

  /**
   * Builds AWT polygon for five vertices around the bounding box center.
   *
   * @return pentagon polygon in screen coordinates
   */
  public Polygon toPolygon() {
    int cx = x + width / 2;
    int cy = y + height / 2;
    int rx = Math.max(1, width / 2);
    int ry = Math.max(1, height / 2);
    int[] xs = new int[5];
    int[] ys = new int[5];
    for (int i = 0; i < 5; i++) {
      double angle = Math.toRadians(-90 + i * 72);
      xs[i] = cx + (int) (rx * Math.cos(angle));
      ys[i] = cy + (int) (ry * Math.sin(angle));
    }
    return new Polygon(xs, ys, 5);
  }

  @Override
  public boolean contains(int px, int py) {
    return toPolygon().contains(px, py);
  }
}
