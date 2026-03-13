package editor;
import shape.Square;
import ui.UIUtils;
import javax.swing.*;
import java.awt.*;

/**
 * Editor for changing properties of a {@link Square}.
 */
public class SquareEditor implements ShapeEditor<Square> {
    /**
     * Builds a panel with fields to edit square position and side length.
     *
     * @param s        square to edit
     * @param onUpdate callback to invoke after a change
     * @return panel containing square controls
     */
    @Override
    public JPanel createEditorPanel(Square s, Runnable onUpdate) {
        JPanel panel = new JPanel(new GridLayout(0, 2, 5, 5));
        panel.setBorder(BorderFactory.createTitledBorder("Edit Square"));
        UIUtils.addIntRow(panel, "X:", s::getX, s::setX, onUpdate);
        UIUtils.addIntRow(panel, "Y:", s::getY, s::setY, onUpdate);
        UIUtils.addIntRow(panel, "Side:", s::getSide, s::setSide, onUpdate);
        return panel;
    }
}