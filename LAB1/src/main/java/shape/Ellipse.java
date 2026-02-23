package shape;

import java.awt.*;

public class Ellipse extends Shape {

    private int radiusX, radiusY;

    public Ellipse(int x, int y, int radiusX, int radiusY, Color color) {
        super(x, y, color);
        this.radiusX = radiusX;
        this.radiusY = radiusY;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(this.color);
        g.drawOval(this.x, this.y, this.radiusX, this.radiusY);
    }
}
