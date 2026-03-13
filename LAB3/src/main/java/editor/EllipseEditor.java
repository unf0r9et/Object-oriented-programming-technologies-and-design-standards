package editor;
import shape.Ellipse;
import ui.UIUtils;
import javax.swing.*;
import java.awt.*;

public class EllipseEditor implements ShapeEditor<Ellipse> {
    @Override
    public JPanel createEditorPanel(Ellipse e, Runnable onUpdate) {
        JPanel panel = new JPanel(new GridLayout(0, 2, 5, 5));
        panel.setBorder(BorderFactory.createTitledBorder("Edit Ellipse"));
        UIUtils.addIntRow(panel, "X:", e::getX, e::setX, onUpdate);
        UIUtils.addIntRow(panel, "Y:", e::getY, e::setY, onUpdate);
        UIUtils.addIntRow(panel, "Width:", e::getWidth, e::setWidth, onUpdate);
        UIUtils.addIntRow(panel, "Height:", e::getHeight, e::setHeight, onUpdate);
        return panel;
    }
}