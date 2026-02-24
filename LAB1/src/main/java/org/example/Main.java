package org.example;

import ShapeList.ShapeList;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("shapes");
        frame.setSize(1280, 720);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ShapeList canvas = new ShapeList();

        JPanel toolbar = new JPanel();

        JButton circleBtn = new JButton("Circle");
        JButton ellipseBtn = new JButton("Ellipse");
        JButton lineBtn = new JButton("Line");
        JButton pointBtn = new JButton("Point");
        JButton rectBtn = new JButton("Rectangle");
        JButton squareBtn = new JButton("Square");
        JButton triangleBtn = new JButton("Triangle");

        JButton clearBtn = new JButton("CLEAR");

        circleBtn.addActionListener(e -> canvas.setTool(ShapeList.Shapes.Circle));
        ellipseBtn.addActionListener(e ->  canvas.setTool(ShapeList.Shapes.Ellipse));
        lineBtn.addActionListener(e ->  canvas.setTool(ShapeList.Shapes.Line));
        pointBtn.addActionListener(e ->  canvas.setTool(ShapeList.Shapes.Point));
        rectBtn.addActionListener(e -> canvas.setTool(ShapeList.Shapes.Rectangle));
        squareBtn.addActionListener(e -> canvas.setTool(ShapeList.Shapes.Square));
        triangleBtn.addActionListener(e -> canvas.setTool(ShapeList.Shapes.Triangle));

        clearBtn.addActionListener(e -> canvas.clearCanvas());


        toolbar.add(circleBtn);
        toolbar.add(ellipseBtn);
        toolbar.add(lineBtn);
        toolbar.add(pointBtn);
        toolbar.add(rectBtn);
        toolbar.add(squareBtn);
        toolbar.add(triangleBtn);

        frame.add(toolbar, BorderLayout.NORTH);
        frame.add(canvas, BorderLayout.CENTER);
        frame.add(clearBtn, BorderLayout.SOUTH);

        frame.setVisible(true);
    }
}