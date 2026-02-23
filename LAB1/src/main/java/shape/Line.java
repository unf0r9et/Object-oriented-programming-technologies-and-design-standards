package shape;

import java.awt.*;

public class Line extends Shape{

    private int x2,y2;

    public Line(int x, int y, int x2, int y2, Color color) {
        super(x, y, color);
        this.x2 = x2;
        this.y2 = y2;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(this.color);
        g.drawLine(this.x, this.y, this.x2, this.y2);
    }
}
