package editor;
import shape.Square;
import ui.UIUtils;
import javax.swing.*;
import java.awt.*;

public class SquareEditor implements ShapeEditor<Square> {
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