package pattern.singleton;

import plugin.ProcessingPipeline;
import plugin.PluginRegistry;

/**
 * Singleton that holds application-wide registries (Singleton pattern).
 * Ensures a single shared processing pipeline and shape plugin registry.
 */
public final class EditorApplicationContext {

  private static final EditorApplicationContext INSTANCE = new EditorApplicationContext();

  private final PluginRegistry shapePluginRegistry = new PluginRegistry();
  private final ProcessingPipeline processingPipeline = new ProcessingPipeline();

  private EditorApplicationContext() {}

  /**
   * @return global application context
   */
  public static EditorApplicationContext getInstance() {
    return INSTANCE;
  }

  public PluginRegistry getShapePluginRegistry() {
    return shapePluginRegistry;
  }

  public ProcessingPipeline getProcessingPipeline() {
    return processingPipeline;
  }
}
