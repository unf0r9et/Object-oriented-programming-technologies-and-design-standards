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
     *
     * @param panel    container panel where the row will be added
     * @param labelText text displayed next to the field
     * @param getter   supplies the current integer value
     * @param setter   consumes the new integer value when the user edits it
     * @param onUpdate callback invoked after a successful update (e.g. to repaint)
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