package shape;


import java.awt.*;

public class Point extends Shape{

    public Point(int x, int y, Color color) {
        super(x, y, color);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(this.color);
        g.drawLine(this.x, this.y, this.x, this.y);
    }
}
