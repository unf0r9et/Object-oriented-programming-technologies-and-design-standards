package plugin;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Strategy-style pipeline that applies enabled {@link FileProcessingPlugin} instances in priority order.
 */
public final class ProcessingPipeline {

  private final List<FileProcessingPlugin> plugins = new CopyOnWriteArrayList<>();

  /**
   * Replaces the current plugin list (e.g. after reload from disk).
   *
   * @param loaded plugins to use
   */
  public void setPlugins(List<FileProcessingPlugin> loaded) {
    plugins.clear();
    List<FileProcessingPlugin> sorted = new ArrayList<>(loaded);
    sorted.sort(Comparator.comparingInt(FileProcessingPlugin::getPriority));
    plugins.addAll(sorted);
  }

  /**
   * @return live view of registered processing plugins
   */
  public List<FileProcessingPlugin> getPlugins() {
    return List.copyOf(plugins);
  }

  /**
   * Runs all save-enabled plugins on XML before persisting to file.
   *
   * @param xml host-produced XML
   * @return transformed XML
   */
  public String processBeforeSave(String xml) {
    String result = xml;
    for (FileProcessingPlugin plugin : plugins) {
      if (plugin.isEnabledOnSave()) {
        result = plugin.processBeforeSave(result);
      }
    }
    return result;
  }

  /**
   * Runs all load-enabled plugins in reverse priority after reading a file.
   *
   * @param xml raw XML from disk
   * @return XML suitable for host parsing
   */
  public String processAfterLoad(String xml) {
    String result = xml;
    List<FileProcessingPlugin> reversed = new ArrayList<>(plugins);
    reversed.sort(Comparator.comparingInt(FileProcessingPlugin::getPriority).reversed());
    for (FileProcessingPlugin plugin : reversed) {
      if (plugin.isEnabledOnLoad()) {
        result = plugin.processAfterLoad(result);
      }
    }
    return result;
  }
}
