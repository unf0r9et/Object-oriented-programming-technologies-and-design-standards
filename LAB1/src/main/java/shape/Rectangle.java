package shape;

import java.awt.*;

public class Rectangle extends Shape {

    private int width, height;

    public Rectangle(int x, int y, int width, int height, Color color) {
        super(x, y, color);
        this.width = width;
        this.height = height;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(this.color);
        g.drawRect(this.x, this.y, this.width, this.height);
    }
}
