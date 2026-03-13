package ui;

import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Utility class for creating property editor UI components.
 */
public class UIUtils {
    /**
     * Creates a row with a label and a text field to edit an integer property.
     */
    public static void addIntRow(JPanel panel, String labelText, Supplier<Integer> getter, Consumer<Integer> setter, Runnable onUpdate) {
        panel.add(new JLabel(labelText));
        JTextField textField = new JTextField(String.valueOf(getter.get()));
        textField.addActionListener(e -> {
            try {
                int value = Integer.parseInt(textField.getText());
                setter.accept(value);
                onUpdate.run(); // Repaint canvas
            } catch (NumberFormatException ex) {
                textField.setText(String.valueOf(getter.get())); // Revert on error
            }
        });
        panel.add(textField);
    }
}