package editor;
import shape.Rectangle;
import ui.UIUtils;
import javax.swing.*;
import java.awt.*;

/**
 * Editor for changing properties of a {@link Rectangle}.
 */
public class RectangleEditor implements ShapeEditor<Rectangle> {
    /**
     * Builds a panel with fields to edit rectangle position and size.
     *
     * @param r        rectangle to edit
     * @param onUpdate callback to invoke after a change
     * @return panel containing rectangle controls
     */
    @Override
    public JPanel createEditorPanel(Rectangle r, Runnable onUpdate) {
        JPanel panel = new JPanel(new GridLayout(0, 2, 5, 5));
        panel.setBorder(BorderFactory.createTitledBorder("Edit Rectangle"));

        UIUtils.addIntRow(panel, "X:", r::getX, r::setX, onUpdate);
        UIUtils.addIntRow(panel, "Y:", r::getY, r::setY, onUpdate);
        UIUtils.addIntRow(panel, "Width:", r::getWidth, r::setWidth, onUpdate);
        UIUtils.addIntRow(panel, "Height:", r::getHeight, r::setHeight, onUpdate);

        return panel;
    }
}