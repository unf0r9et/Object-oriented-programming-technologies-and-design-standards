package editor;
import shape.Point;
import ui.UIUtils;
import javax.swing.*;
import java.awt.*;

public class PointEditor implements ShapeEditor<Point> {
    @Override
    public JPanel createEditorPanel(Point p, Runnable onUpdate) {
        JPanel panel = new JPanel(new GridLayout(0, 2, 5, 5));
        panel.setBorder(BorderFactory.createTitledBorder("Edit Point"));
        UIUtils.addIntRow(panel, "X:", p::getX, p::setX, onUpdate);
        UIUtils.addIntRow(panel, "Y:", p::getY, p::setY, onUpdate);
        return panel;
    }
}