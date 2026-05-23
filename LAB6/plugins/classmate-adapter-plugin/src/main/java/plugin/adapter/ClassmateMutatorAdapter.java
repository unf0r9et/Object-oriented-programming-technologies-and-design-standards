package plugin.adapter;

import com.classmate.legacy.DocumentMutator;
import plugin.FileProcessingPlugin;

import javax.swing.*;
import java.awt.*;

/**
 * Adapter pattern: bridges classmate's {@link DocumentMutator} to host {@link FileProcessingPlugin}.
 */
public final class ClassmateMutatorAdapter implements FileProcessingPlugin {

  private final DocumentMutator delegate;

  public ClassmateMutatorAdapter(DocumentMutator delegate) {
    this.delegate = delegate;
  }

  @Override
  public String getPluginId() {
    return "classmate-adapter";
  }

  @Override
  public String getDisplayName() {
    return "Adapter: " + delegate.label();
  }

  @Override
  public int getPriority() {
    return 150;
  }

  @Override
  public String processBeforeSave(String xml) {
    return delegate.mutateBeforePersist(xml);
  }

  @Override
  public String processAfterLoad(String xml) {
    return delegate.mutateAfterRestore(xml);
  }

  @Override
  public JPanel createSettingsPanel() {
    JPanel panel = new JPanel(new BorderLayout());
    panel.add(new JLabel("Wraps classmate plugin: " + delegate.label()), BorderLayout.CENTER);
    return panel;
  }
}
