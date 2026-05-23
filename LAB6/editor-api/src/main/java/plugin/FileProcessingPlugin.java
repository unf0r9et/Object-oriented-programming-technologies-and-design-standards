package plugin;

import javax.swing.JPanel;

/**
 * SPI for plugins that transform document XML before save and after load.
 * Used by lab 5 (variant 4: XSLT and related XML processors).
 */
public interface FileProcessingPlugin {

  /**
   * @return stable plugin identifier
   */
  String getPluginId();

  /**
   * @return name shown in Settings menu
   */
  String getDisplayName();

  /**
   * Lower values run earlier in the processing chain.
   *
   * @return ordering priority
   */
  default int getPriority() {
    return 100;
  }

  /**
   * Whether this plugin participates in the save pipeline.
   *
   * @return true when enabled for save
   */
  default boolean isEnabledOnSave() {
    return true;
  }

  /**
   * Whether this plugin participates in the load pipeline.
   *
   * @return true when enabled for load
   */
  default boolean isEnabledOnLoad() {
    return true;
  }

  /**
   * Transforms XML payload before writing to disk.
   *
   * @param xml input XML produced by the host application
   * @return transformed XML
   */
  String processBeforeSave(String xml);

  /**
   * Transforms XML payload after reading from disk.
   *
   * @param xml XML read from file
   * @return XML understood by the host loader
   */
  String processAfterLoad(String xml);

  /**
   * Optional settings panel for plugin-specific parameters (10-point requirement).
   *
   * @return settings panel or empty panel when not configurable
   */
  default JPanel createSettingsPanel() {
    return new JPanel();
  }
}
