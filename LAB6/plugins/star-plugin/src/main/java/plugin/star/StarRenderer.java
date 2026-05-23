package plugin.star;

import render.ShapeRenderer;

import java.awt.Graphics;

public class StarRenderer implements ShapeRenderer<Star> {

  @Override
  public void render(Star shape, Graphics g) {
    g.setColor(shape.getColor());
    g.drawPolygon(shape.toPolygon());
  }
}
