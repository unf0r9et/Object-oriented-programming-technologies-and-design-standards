package ui;

import plugin.FileProcessingPlugin;
import plugin.ProcessingPipeline;

import javax.swing.*;
import java.awt.*;
import java.nio.file.Path;

/**
 * Settings window listing loaded processing plugins and their configurable panels.
 */
public final class PluginSettingsDialog extends JDialog {

  /**
   * Shows modal settings for processing plugins and allows loading an extra plugin JAR.
   *
   * @param owner    parent frame
   * @param pipeline active processing pipeline
   * @param onReload callback when user loads a new plugin file
   */
  public PluginSettingsDialog(
      Frame owner, ProcessingPipeline pipeline, java.util.function.Consumer<Path> onReload) {
    super(owner, "Plugin Settings", true);
    setLayout(new BorderLayout(8, 8));
    JTabbedPane tabs = new JTabbedPane();
    for (FileProcessingPlugin plugin : pipeline.getPlugins()) {
      JPanel tab = new JPanel(new BorderLayout());
      tab.add(new JLabel(plugin.getDisplayName() + " (" + plugin.getPluginId() + ")"), BorderLayout.NORTH);
      tab.add(plugin.createSettingsPanel(), BorderLayout.CENTER);
      tabs.addTab(plugin.getDisplayName(), tab);
    }
    add(tabs, BorderLayout.CENTER);

    JPanel bottom = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    JButton loadJar = new JButton("Load processing plugin...");
    loadJar.addActionListener(
        e -> {
          JFileChooser chooser = new JFileChooser();
          chooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Plugin JAR", "jar"));
          if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            onReload.accept(chooser.getSelectedFile().toPath());
          }
        });
    JButton close = new JButton("Close");
    close.addActionListener(e -> dispose());
    bottom.add(loadJar);
    bottom.add(close);
    add(bottom, BorderLayout.SOUTH);
    setSize(520, 420);
    setLocationRelativeTo(owner);
  }
}
