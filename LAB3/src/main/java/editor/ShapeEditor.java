package editor;
import shape.Shape;
import javax.swing.JPanel;

/**
 * Interface for generating a UI panel to edit shape properties.
 */
public interface ShapeEditor<T extends Shape> {
    /**
     * Creates a Swing panel populated with controls for editing the given shape.
     *
     * @param shape    shape instance whose properties will be edited
     * @param onUpdate callback invoked after a property change (e.g. to repaint the canvas)
     * @return configured editor panel
     */
    JPanel createEditorPanel(T shape, Runnable onUpdate);
}