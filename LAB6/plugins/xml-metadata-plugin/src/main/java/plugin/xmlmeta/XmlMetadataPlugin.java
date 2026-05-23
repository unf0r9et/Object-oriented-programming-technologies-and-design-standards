package plugin.xmlmeta;

import plugin.FileProcessingPlugin;

import javax.swing.*;
import java.awt.*;
import java.time.Instant;

/** Adds timestamp comment into metadata block before save. */
public class XmlMetadataPlugin implements FileProcessingPlugin {

  private volatile String author = "student";

  @Override
  public String getPluginId() {
    return "xml-metadata-plugin";
  }

  @Override
  public String getDisplayName() {
    return "XML Metadata";
  }

  @Override
  public int getPriority() {
    return 10;
  }

  @Override
  public String processBeforeSave(String xml) {
    String stamp = Instant.now().toString();
    String meta =
        "<metadata generated=\"host\" author=\"" + author + "\" savedAt=\"" + stamp + "\"/>";
    return xml.replaceFirst("<metadata[^>]*/>", meta);
  }

  @Override
  public String processAfterLoad(String xml) {
    return xml;
  }

  @Override
  public JPanel createSettingsPanel() {
    JPanel panel = new JPanel(new GridLayout(0, 1));
    panel.add(new JLabel("Author attribute:"));
    JTextField field = new JTextField(author);
    field.addActionListener(e -> author = field.getText().trim());
    panel.add(field);
    return panel;
  }
}
