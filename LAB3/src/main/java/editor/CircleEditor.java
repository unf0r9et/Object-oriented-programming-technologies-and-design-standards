package editor;
import shape.Circle;
import ui.UIUtils;
import javax.swing.*;
import java.awt.*;

public class CircleEditor implements ShapeEditor<Circle> {
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