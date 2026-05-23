package plugin.star;

import editor.ShapeEditor;

import javax.swing.*;
import java.awt.*;

public class StarEditor implements ShapeEditor<Star> {

  @Override
  public JPanel createEditorPanel(Star shape, Runnable onUpdate) {
    JPanel panel = new JPanel(new GridLayout(0, 2, 5, 5));
    panel.setBorder(BorderFactory.createTitledBorder("Edit Star"));
    addIntRow(panel, "X:", shape::getX, shape::setX, onUpdate);
    addIntRow(panel, "Y:", shape::getY, shape::setY, onUpdate);
    addIntRow(panel, "Width:", shape::getWidth, shape::setWidth, onUpdate);
    addIntRow(panel, "Height:", shape::getHeight, shape::setHeight, onUpdate);
    return panel;
  }

  private static void addIntRow(
      JPanel panel,
      String label,
      java.util.function.Supplier<Integer> getter,
      java.util.function.IntConsumer setter,
      Runnable onUpdate) {
    panel.add(new JLabel(label));
    JTextField field = new JTextField(String.valueOf(getter.get()));
    field.addActionListener(
        e -> {
          try {
            setter.accept(Integer.parseInt(field.getText()));
            onUpdate.run();
          } catch (NumberFormatException ex) {
            field.setText(String.valueOf(getter.get()));
          }
        });
    panel.add(field);
  }
}
