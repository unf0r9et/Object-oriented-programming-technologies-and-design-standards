package org.example;

import canvas.ShapeCanvas;

// Импорт фабрик
import factory.*;

// Импорт рендереров
import render.*;

// Импорт данных фигур
import shape.*;
import shape.Point; // Уточняем, чтобы не путать с java.awt.Point
import shape.Rectangle;

import javax.swing.*;
import java.awt.*;

/**
 * Main application class. Sets up the UI, registers factories and renderers.
 */
public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Primitive Graphic Editor");
        frame.setSize(1280, 720);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ShapeCanvas canvas = new ShapeCanvas();

        // 1. Register Renderers for each shape class
        // This fully links data to drawing logic without violating Open/Closed Principle
        canvas.registerRenderer(Rectangle.class, new RectangleRenderer());
        canvas.registerRenderer(Square.class, new SquareRenderer());
        canvas.registerRenderer(Ellipse.class, new EllipseRenderer());
        canvas.registerRenderer(Circle.class, new CircleRenderer());
        canvas.registerRenderer(Line.class, new LineRenderer());
        canvas.registerRenderer(Point.class, new PointRenderer());
        canvas.registerRenderer(Triangle.class, new TriangleRenderer());

        JPanel toolbar = new JPanel();

        // 2. Create UI Buttons
        JButton rectBtn = new JButton("Rectangle");
        JButton squareBtn = new JButton("Square");
        JButton ellipseBtn = new JButton("Ellipse");
        JButton circleBtn = new JButton("Circle");
        JButton lineBtn = new JButton("Line");
        JButton pointBtn = new JButton("Point");
        JButton triangleBtn = new JButton("Triangle");
        JButton clearBtn = new JButton("CLEAR");

        // 3. Bind Buttons to Shape Factories
        // Replaces the old switch-case logic completely!
        rectBtn.addActionListener(e -> canvas.setFactory(new RectangleFactory()));
        squareBtn.addActionListener(e -> canvas.setFactory(new SquareFactory()));
        ellipseBtn.addActionListener(e -> canvas.setFactory(new EllipseFactory()));
        circleBtn.addActionListener(e -> canvas.setFactory(new CircleFactory()));
        lineBtn.addActionListener(e -> canvas.setFactory(new LineFactory()));
        pointBtn.addActionListener(e -> canvas.setFactory(new PointFactory()));
        triangleBtn.addActionListener(e -> canvas.setFactory(new TriangleFactory()));

        clearBtn.addActionListener(e -> canvas.clearCanvas());

        // 4. Setup UI layout
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

        // Set a default active tool
        canvas.setFactory(new RectangleFactory());

        frame.setVisible(true);
    }
}