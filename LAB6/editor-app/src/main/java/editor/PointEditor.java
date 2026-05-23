package editor;
import shape.Point;
import ui.UIUtils;
import javax.swing.*;
import java.awt.*;

/**
 * Editor for changing properties of a {@link Point}.
 */
public class PointEditor implements ShapeEditor<Point> {
    /**
     * Builds a panel with fields to edit point coordinates.
     *
     * @param p        point to edit
     * @param onUpdate callback to invoke after a change
     * @return panel containing point controls
     */
    @Override
    public JPanel createEditorPanel(Point p, Runnable onUpdate) {
        JPanel panel = new JPanel(new GridLayout(0, 2, 5, 5));
        panel.setBorder(BorderFactory.createTitledBorder("Edit Point"));
        UIUtils.addIntRow(panel, "X:", p::getX, p::setX, onUpdate);
        UIUtils.addIntRow(panel, "Y:", p::getY, p::setY, onUpdate);
        return panel;
    }
}