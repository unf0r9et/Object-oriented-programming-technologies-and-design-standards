package plugin.adapter;

import com.classmate.plugin.JsonMinifyMutator;
import plugin.FileProcessingPlugin;

/**
 * Adapter plugin loaded by the host: exposes classmate functionality through {@link FileProcessingPlugin}.
 */
public class ClassmateAdapterPluginProvider implements FileProcessingPlugin {

  private final ClassmateMutatorAdapter adapter = new ClassmateMutatorAdapter(new JsonMinifyMutator());

  @Override
  public String getPluginId() {
    return adapter.getPluginId();
  }

  @Override
  public String getDisplayName() {
    return adapter.getDisplayName();
  }

  @Override
  public int getPriority() {
    return adapter.getPriority();
  }

  @Override
  public String processBeforeSave(String xml) {
    return adapter.processBeforeSave(xml);
  }

  @Override
  public String processAfterLoad(String xml) {
    return adapter.processAfterLoad(xml);
  }

  @Override
  public javax.swing.JPanel createSettingsPanel() {
    return adapter.createSettingsPanel();
  }
}
