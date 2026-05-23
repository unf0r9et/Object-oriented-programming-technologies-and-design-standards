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

/**
 * Canvas component responsible for storing, rendering and interacting with shapes.
 * Supports drawing, selecting, deleting, and live preview of the shape being created.
 */
public class ShapeCanvas extends JPanel {
    private List<Shape> shapes = new ArrayList<>();

    // Callbacks for UI updates
    private Consumer<JPanel> onShapeSelected;

    // Registries
    private Map<Class<? extends Shape>, ShapeRenderer<Shape>> renderers = new HashMap<>();
    private Map<Class<? extends Shape>, ShapeEditor<Shape>> editors = new HashMap<>();

    // Shape that is currently being drawn (preview)
    private Shape currentShape = null;

    /**
     * Sets the current preview shape and repaints the canvas.
     *
     * @param shape preview shape to display, or {@code null} to clear preview
     */
    public void setCurrentShape(Shape shape) {
        this.currentShape = shape;
        repaint();
    }

    /**
     * Commits the current preview shape into the shapes list and clears preview.
     * Does nothing if there is no current preview shape.
     */
    public void commitCurrentShape() {
        if (currentShape != null) {
            shapes.add(currentShape);
            currentShape = null;
            repaint();
        }
    }

    // State
    private Shape selectedShape = null;

    /**
     * Constructs a new shape canvas with white background.
     */
    public ShapeCanvas() {
        setBackground(Color.WHITE);
    }

    /**
     * Returns the list of shapes currently on the canvas.
     *
     * @return mutable list of shapes
     */
    public List<Shape> getShapes() { return shapes; }

    /**
     * Replaces the current list of shapes and clears the selection.
     *
     * @param newShapes new collection of shapes to display
     */
    public void setShapes(List<Shape> newShapes) {
        this.shapes = newShapes;
        this.selectedShape = null;
        repaint();
    }

    /**
     * Registers a callback that will be invoked whenever the selected shape changes.
     * The callback receives a panel containing property editors for the selected shape.
     *
     * @param callback consumer that receives the editor panel
     */
    public void setOnShapeSelected(Consumer<JPanel> callback) {
        this.onShapeSelected = callback;
    }

    /**
     * Changes the current mouse interaction mode by swapping mouse listeners.
     * Can be used to toggle between drawing tools and selection mode.
     *
     * @param adapter mouse adapter implementing the desired behavior
     */
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

    /**
     * Registers a shape type along with its renderer and editor in the internal registries.
     *
     * @param clazz    concrete shape class
     * @param renderer renderer responsible for drawing that shape
     * @param editor   editor responsible for producing UI controls for that shape
     * @param <T>      shape subtype
     */
    @SuppressWarnings("unchecked")
    public <T extends Shape> void registerShapeType(Class<T> clazz, ShapeRenderer<T> renderer, ShapeEditor<T> editor) {
        renderers.put(clazz, (ShapeRenderer<Shape>) renderer);
        editors.put(clazz, (ShapeEditor<Shape>) editor);
    }

    /**
     * Adds a new shape to the canvas and repaints it.
     *
     * @param shape shape to add
     */
    public void addShape(Shape shape) {
        shapes.add(shape);
        repaint();
    }

    /**
     * Removes the currently selected shape from the canvas (if any)
     * and clears the associated editor UI.
     */
    public void removeSelectedShape() {
        if (selectedShape != null) {
            shapes.remove(selectedShape);
            selectedShape = null;
            if (onShapeSelected != null) onShapeSelected.accept(new JPanel()); // clear editor UI
            repaint();
        }
    }

    /**
     * Selects the top-most shape at the given coordinates, if any,
     * and generates the corresponding editor UI using the registry.
     *
     * @param x x-coordinate in canvas space
     * @param y y-coordinate in canvas space
     */
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

    /**
     * Paints all shapes and the current selection/preview on the canvas.
     *
     * @param g graphics context
     */
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

        // Draw preview of the shape currently being created
        if (currentShape != null) {
            ShapeRenderer<Shape> renderer = renderers.get(currentShape.getClass());
            if (renderer != null) renderer.render(currentShape, g);
        }
    }
}