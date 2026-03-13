package canvas;

import factory.ShapeFactory;
import render.ShapeRenderer;
import shape.Shape;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Custom JPanel that acts as the drawing canvas.
 * Manages mouse events, stores drawn shapes, and handles rendering.
 */
public class ShapeCanvas extends JPanel {

    private ArrayList<Shape> shapes = new ArrayList<>();
    private Shape currentShape;
    private int startX, startY;

    // Current tool selected by the user (replaces the Enum and Switch)
    private ShapeFactory currentFactory;

    // Registry of renderers for different shape types
    private Map<Class<? extends Shape>, ShapeRenderer<Shape>> renderers = new HashMap<>();

    public ShapeCanvas() {
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
                if (currentFactory != null) {
                    // Create the shape using the currently selected factory
                    // NO SWITCH STATEMENTS NEEDED
                    currentShape = currentFactory.createShape(
                            startX, startY, e.getX(), e.getY(), Color.BLUE
                    );
                    repaint();
                }
            }
        });
    }

    /**
     * Sets the current drawing tool (factory).
     * @param factory The factory that creates the desired shape.
     */
    public void setFactory(ShapeFactory factory) {
        this.currentFactory = factory;
    }

    /**
     * Registers a renderer for a specific shape class.
     * @param shapeClass The class of the shape.
     * @param renderer The renderer implementation for that shape.
     */
    @SuppressWarnings("unchecked")
    public <T extends Shape> void registerRenderer(Class<T> shapeClass, ShapeRenderer<T> renderer) {
        // Safe cast due to generic constraints in the method signature
        renderers.put(shapeClass, (ShapeRenderer<Shape>) renderer);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw all saved shapes
        for (Shape s : shapes) {
            drawShape(s, g);
        }

        // Draw the shape currently being dragged
        if (currentShape != null) {
            drawShape(currentShape, g);
        }
    }

    /**
     * Helper method to find the correct renderer and draw the shape.
     */
    private void drawShape(Shape shape, Graphics g) {
        ShapeRenderer<Shape> renderer = renderers.get(shape.getClass());
        if (renderer != null) {
            renderer.render(shape, g);
        } else {
            System.err.println("No renderer registered for: " + shape.getClass().getSimpleName());
        }
    }

    /**
     * Clears the canvas.
     */
    public void clearCanvas() {
        shapes.clear();
        currentShape = null;
        repaint();
    }
}