package shape;

import java.awt.*;

public class Circle extends Ellipse{
    public Circle(int x, int y, int radius, Color color) {
        super(x, y, radius, radius, color);
    }
}
