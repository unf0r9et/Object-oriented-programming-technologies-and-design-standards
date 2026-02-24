package ShapeList;

import shape.*;
import shape.Point;
import shape.Rectangle;
import shape.Shape;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class ShapeList extends JPanel {

    public enum Shapes {Circle, Ellipse, Line, Point, Rectangle, Square, Triangle}

    private ArrayList<Shape> shapes = new ArrayList<>();

    private Shape currentShape;
    private int startX, startY;


    private Shapes selectedTool = Shapes.Ellipse;

    public ShapeList() {

        setBackground(Color.WHITE);

        addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                startX = e.getX();
                startY = e.getY();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (currentShape != null) {
                    shapes.add(currentShape);
                    currentShape = null;
                    repaint();
                }
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {

            @Override
            public void mouseDragged(MouseEvent e) {

                int currentX = e.getX();
                int currentY = e.getY();

                int x = Math.min(startX, currentX);
                int y = Math.min(startY, currentY);

                int width = Math.abs(currentX - startX);
                int height = Math.abs(currentY - startY);


                switch (selectedTool) {
                    case Shapes.Circle:
                        currentShape = new Circle(x, y, height, Color.BLUE);
                        break;
                    case Shapes.Ellipse:
                        currentShape = new Ellipse(x, y, width, height, Color.BLUE);
                        break;
                    case Shapes.Line:
                        currentShape = new Line(startX, startY, currentX, currentY, Color.BLUE);
                        break;
                    case Shapes.Point:
                        currentShape = new Point(x, y, Color.BLUE);
                        break;
                    case Shapes.Rectangle:
                        currentShape = new Rectangle(x, y, width, height, Color.BLUE);
                        break;
                    case Shapes.Square:
                        currentShape = new Square(x, y, width, Color.BLUE);
                        break;
                    case Shapes.Triangle:

                        int baseHalf = Math.abs(currentX - startX);

                        int leftX = startX - baseHalf;

                        int rightX = startX + baseHalf;

                        currentShape = new Triangle(x, y, leftX, currentY, rightX, currentY, Color.BLUE);
                        break;
                }

                repaint();
            }
        });
    }

    public void setTool(Shapes tool) {
        this.selectedTool = tool;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (Shape s : shapes) {
            s.draw(g);
        }

        if (currentShape != null) {
            currentShape.draw(g);
        }
    }

    public void clearCanvas() {
        shapes.clear();
        currentShape = null;
        repaint();
    }
}