package ShapeList;

import shape.Shape;

import javax.swing.*;
import java.util.ArrayList;
import java.awt.*;

public class ShapeList extends JPanel {
    private ArrayList<Shape> shapeList = new ArrayList<>();

    public void addShape(Shape sp) {
        shapeList.add(sp);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (Shape s : shapeList) {
            s.draw(g);
        }
    }
}
