package editor;
import shape.Circle;
import ui.UIUtils;
import javax.swing.*;
import java.awt.*;

/**
 * Editor for changing properties of a {@link Circle}.
 */
public class CircleEditor implements ShapeEditor<Circle> {
    /**
     * Builds a panel with fields to edit circle position and diameter.
     *
     * @param c        circle to edit
     * @param onUpdate callback to invoke after a change
     * @return panel containing circle controls
     */
    @Override
    public JPanel createEditorPanel(Circle c, Runnable onUpdate) {
        JPanel panel = new JPanel(new GridLayout(0, 2, 5, 5));
        panel.setBorder(BorderFactory.createTitledBorder("Edit Circle"));
        UIUtils.addIntRow(panel, "X:", c::getX, c::setX, onUpdate);
        UIUtils.addIntRow(panel, "Y:", c::getY, c::setY, onUpdate);
        UIUtils.addIntRow(panel, "Diameter:", c::getDiameter, c::setDiameter, onUpdate);
        return panel;
    }
}