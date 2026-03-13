package editor;
import shape.Shape;
import javax.swing.JPanel;

/**
 * Interface for generating a UI panel to edit shape properties.
 */
public interface ShapeEditor<T extends Shape> {
    JPanel createEditorPanel(T shape, Runnable onUpdate);
}