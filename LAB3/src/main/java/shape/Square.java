package shape;
import java.awt.Color;

public class Square extends Rectangle {
    public Square(int x, int y, int side, Color color) {
        super(x, y, side, side, color);
    }

    public int getSide() { return getWidth(); }
    public void setSide(int side) {
        setWidth(side);
        setHeight(side);
    }
}