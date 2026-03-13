package shape;
import java.awt.Color;

public class Circle extends Ellipse {
    public Circle(int x, int y, int diameter, Color color) {
        super(x, y, diameter, diameter, color);
    }

    public int getDiameter() { return width; }
    public void setDiameter(int diameter) {
        this.width = diameter;
        this.height = diameter;
    }
}