package plugin.xslt;

import plugin.FileProcessingPlugin;

import javax.swing.*;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.awt.*;
import java.io.StringReader;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Variant 4 plugin: transforms shape XML using a configurable XSLT stylesheet.
 */
public class XsltTransformPlugin implements FileProcessingPlugin {

  private volatile String xsltPath = "";
  private volatile boolean enabledOnSave = true;
  private volatile boolean enabledOnLoad = true;

  private static final String DEFAULT_XSLT =
      """
      <?xml version="1.0" encoding="UTF-8"?>
      <xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
        <xsl:output method="xml" indent="yes"/>
        <xsl:template match="@*|node()">
          <xsl:copy>
            <xsl:apply-templates select="@*|node()"/>
          </xsl:copy>
        </xsl:template>
        <xsl:template match="metadata">
          <metadata transformed="xslt" generated="plugin"/>
        </xsl:template>
      </xsl:stylesheet>
      """;

  @Override
  public String getPluginId() {
    return "xslt-transform-plugin";
  }

  @Override
  public String getDisplayName() {
    return "XSLT Transform (variant 4)";
  }

  @Override
  public int getPriority() {
    return 50;
  }

  @Override
  public boolean isEnabledOnSave() {
    return enabledOnSave;
  }

  @Override
  public boolean isEnabledOnLoad() {
    return enabledOnLoad;
  }

  @Override
  public String processBeforeSave(String xml) {
    return transform(xml);
  }

  @Override
  public String processAfterLoad(String xml) {
    return transform(xml);
  }

  /**
   * Applies configured XSLT or built-in identity+metadata template.
   */
  private String transform(String xml) {
    try {
      String xslt =
          (xsltPath != null && !xsltPath.isBlank() && Files.exists(Path.of(xsltPath)))
              ? Files.readString(Path.of(xsltPath))
              : DEFAULT_XSLT;
      TransformerFactory factory = TransformerFactory.newInstance();
      Transformer transformer =
          factory.newTransformer(new StreamSource(new StringReader(xslt)));
      StringWriter out = new StringWriter();
      transformer.transform(new StreamSource(new StringReader(xml)), new StreamResult(out));
      return out.toString();
    } catch (Exception ex) {
      throw new IllegalStateException("XSLT transformation failed", ex);
    }
  }

  @Override
  public JPanel createSettingsPanel() {
    JPanel panel = new JPanel(new GridLayout(0, 1, 4, 4));
    JTextField pathField = new JTextField(xsltPath);
    panel.add(new JLabel("XSLT file path (empty = built-in template):"));
    panel.add(pathField);
    JCheckBox saveBox = new JCheckBox("Enable on save", enabledOnSave);
    JCheckBox loadBox = new JCheckBox("Enable on load", enabledOnLoad);
    panel.add(saveBox);
    panel.add(loadBox);
    JButton browse = new JButton("Browse XSLT...");
    browse.addActionListener(
        e -> {
          JFileChooser chooser = new JFileChooser();
          chooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("XSLT", "xsl", "xslt"));
          if (chooser.showOpenDialog(panel) == JFileChooser.APPROVE_OPTION) {
            pathField.setText(chooser.getSelectedFile().getAbsolutePath());
          }
        });
  panel.add(browse);
    Runnable apply =
        () -> {
          xsltPath = pathField.getText().trim();
          enabledOnSave = saveBox.isSelected();
          enabledOnLoad = loadBox.isSelected();
        };
    pathField.addActionListener(e -> apply.run());
    saveBox.addActionListener(e -> apply.run());
    loadBox.addActionListener(e -> apply.run());
    return panel;
  }
}
