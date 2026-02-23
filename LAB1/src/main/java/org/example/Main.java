package org.example;

import javax.swing.JFrame;
import java.awt.Color;

import ShapeList.ShapeList;
import shape.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("shapes");
        frame.setSize(1280, 720);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ShapeList canvas = new ShapeList();
        canvas.setBackground(Color.BLACK);

        canvas.addShape(new Ellipse(100, 90, 200, 500, Color.ORANGE));
        canvas.addShape(new Ellipse(110, 100, 180, 480, Color.ORANGE));
        canvas.addShape(new Ellipse(1000, 90, 200, 500, Color.BLUE));
        canvas.addShape(new Ellipse(1010, 100, 180, 480, Color.BLUE));
        canvas.addShape(new Circle(220, 400, 40, Color.RED));
        canvas.addShape(new Square(320, 330, 50, Color.GREEN));
        canvas.addShape(new Triangle(420, 300, 470, 310, 520, 360, Color.YELLOW));
        canvas.addShape(new Rectangle(560, 340, 80, 40, Color.PINK));
        canvas.addShape(new Ellipse(720, 300, 80, 40, Color.green));
        canvas.addShape(new Square(840, 250, 60, Color.MAGENTA));
        canvas.addShape(new Circle(960, 360, 45, Color.ORANGE));

        frame.add(canvas);
        frame.setVisible(true);
    }
}