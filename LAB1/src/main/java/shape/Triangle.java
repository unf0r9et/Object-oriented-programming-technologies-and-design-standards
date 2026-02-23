package shape;

import java.awt.*;

public class Triangle extends Shape {
    protected int x2, y2, x3, y3;

    public Triangle(int x, int y, int x2, int y2, int x3, int y3, Color color) {
        super(x, y, color);
        this.x2 = x2;
        this.y2 = y2;
        this.x3 = x3;
        this.y3 = y3;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(this.color);
        g.drawLine(this.x, this.y, this.x2, this.y2);
        g.drawLine(this.x2, this.y2, this.x3, this.y3);
        g.drawLine(this.x3, this.y3, this.x, this.y);
    }
}
