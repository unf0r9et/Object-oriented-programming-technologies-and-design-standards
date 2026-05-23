package editor;
import shape.Triangle;
import ui.UIUtils;
import javax.swing.*;
import java.awt.*;

/**
 * Editor for changing properties of a {@link Triangle}.
 */
public class TriangleEditor implements ShapeEditor<Triangle> {
    /**
     * Builds a panel with fields to edit triangle vertices.
     *
     * @param t        triangle to edit
     * @param onUpdate callback to invoke after a change
     * @return panel containing triangle controls
     */
    @Override
    public JPanel createEditorPanel(Triangle t, Runnable onUpdate) {
        JPanel panel = new JPanel(new GridLayout(0, 2, 5, 5));
        panel.setBorder(BorderFactory.createTitledBorder("Edit Triangle"));
        UIUtils.addIntRow(panel, "X1:", t::getX, t::setX, onUpdate);
        UIUtils.addIntRow(panel, "Y1:", t::getY, t::setY, onUpdate);
        UIUtils.addIntRow(panel, "X2:", t::getX2, t::setX2, onUpdate);
        UIUtils.addIntRow(panel, "Y2:", t::getY2, t::setY2, onUpdate);
        UIUtils.addIntRow(panel, "X3:", t::getX3, t::setX3, onUpdate);
        UIUtils.addIntRow(panel, "Y3:", t::getY3, t::setY3, onUpdate);
        return panel;
    }
}