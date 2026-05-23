package editor;
import shape.Line;
import ui.UIUtils;
import javax.swing.*;
import java.awt.*;

/**
 * Editor for changing properties of a {@link Line}.
 */
public class LineEditor implements ShapeEditor<Line> {
    /**
     * Builds a panel with fields to edit line endpoints.
     *
     * @param l        line to edit
     * @param onUpdate callback to invoke after a change
     * @return panel containing line controls
     */
    @Override
    public JPanel createEditorPanel(Line l, Runnable onUpdate) {
        JPanel panel = new JPanel(new GridLayout(0, 2, 5, 5));
        panel.setBorder(BorderFactory.createTitledBorder("Edit Line"));
        UIUtils.addIntRow(panel, "X1:", l::getX, l::setX, onUpdate);
        UIUtils.addIntRow(panel, "Y1:", l::getY, l::setY, onUpdate);
        UIUtils.addIntRow(panel, "X2:", l::getX2, l::setX2, onUpdate);
        UIUtils.addIntRow(panel, "Y2:", l::getY2, l::setY2, onUpdate);
        return panel;
    }
}