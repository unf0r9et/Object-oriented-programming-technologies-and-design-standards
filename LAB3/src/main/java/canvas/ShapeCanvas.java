package canvas;

import editor.ShapeEditor;
import render.ShapeRenderer;
import shape.Shape;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class ShapeCanvas extends JPanel {
    private List<Shape> shapes = new ArrayList<>();

    // Callbacks for UI updates
    private Consumer<JPanel> onShapeSelected;

    // Registries
    private Map<Class<? extends Shape>, ShapeRenderer<Shape>> renderers = new HashMap<>();
    private Map<Class<? extends Shape>, ShapeEditor<Shape>> editors = new HashMap<>();

    // Добавь это поле к остальным (рядом с selectedShape)
    private Shape currentShape = null;

    // Добавь эти два метода
    public void setCurrentShape(Shape shape) {
        this.currentShape = shape;
        repaint();
    }

    public void commitCurrentShape() {
        if (currentShape != null) {
            shapes.add(currentShape);
            currentShape = null;
            repaint();
        }
    }

    // State
    private Shape selectedShape = null;

    public ShapeCanvas() {
        setBackground(Color.WHITE);
    }

    public List<Shape> getShapes() { return shapes; }

    public void setShapes(List<Shape> newShapes) {
        this.shapes = newShapes;
        this.selectedShape = null;
        repaint();
    }

    public void setOnShapeSelected(Consumer<JPanel> callback) {
        this.onShapeSelected = callback;
    }

    // Changing interaction mode (Drawing vs Selecting) by swapping listeners
    public void setMouseMode(MouseAdapter adapter) {
        for (MouseListener ml : getMouseListeners()) removeMouseListener(ml);
        for (MouseMotionListener mml : getMouseMotionListeners()) removeMouseMotionListener(mml);

        addMouseListener(adapter);
        if (adapter instanceof MouseMotionListener) {
            addMouseMotionListener((MouseMotionListener) adapter);
        }
        selectedShape = null;
        repaint();
    }

    @SuppressWarnings("unchecked")
    public <T extends Shape> void registerShapeType(Class<T> clazz, ShapeRenderer<T> renderer, ShapeEditor<T> editor) {
        renderers.put(clazz, (ShapeRenderer<Shape>) renderer);
        editors.put(clazz, (ShapeEditor<Shape>) editor);
    }

    public void addShape(Shape shape) {
        shapes.add(shape);
        repaint();
    }

    public void removeSelectedShape() {
        if (selectedShape != null) {
            shapes.remove(selectedShape);
            selectedShape = null;
            if (onShapeSelected != null) onShapeSelected.accept(new JPanel()); // clear editor UI
            repaint();
        }
    }

    public void selectShapeAt(int x, int y) {
        selectedShape = null;
        // Search in reverse order (top to bottom)
        for (int i = shapes.size() - 1; i >= 0; i--) {
            if (shapes.get(i).contains(x, y)) {
                selectedShape = shapes.get(i);
                break;
            }
        }

        // Generate UI using the Registry, NO IF/SWITCH!
        if (selectedShape != null && onShapeSelected != null) {
            ShapeEditor<Shape> editor = editors.get(selectedShape.getClass());
            if (editor != null) {
                onShapeSelected.accept(editor.createEditorPanel(selectedShape, this::repaint));
            }
        } else if (onShapeSelected != null) {
            onShapeSelected.accept(new JPanel()); // empty panel
        }
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Shape s : shapes) {
            ShapeRenderer<Shape> renderer = renderers.get(s.getClass());
            if (renderer != null) renderer.render(s, g);

            if (s == selectedShape) {
                g.setColor(Color.RED);
                g.drawRect(s.getX() - 2, s.getY() - 2, 4, 4);
            }
        }

        // --- ДОБАВИТЬ ЭТО ДЛЯ ПРЕДПРОСМОТРА ---
        if (currentShape != null) {
            ShapeRenderer<Shape> renderer = renderers.get(currentShape.getClass());
            if (renderer != null) renderer.render(currentShape, g);
        }
    }
}