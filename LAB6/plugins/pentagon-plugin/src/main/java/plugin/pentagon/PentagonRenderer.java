package plugin.pentagon;

import render.ShapeRenderer;

import java.awt.Graphics;

/** Draws a pentagon outline using {@link Pentagon#toPolygon()}. */
public class PentagonRenderer implements ShapeRenderer<Pentagon> {

  @Override
  public void render(Pentagon shape, Graphics g) {
    g.setColor(shape.getColor());
    g.drawPolygon(shape.toPolygon());
  }
}
