package plugin;

import canvas.ShapeCanvas;
import editor.ShapeEditor;
import factory.ShapeFactory;
import io.JsonFileHandler;
import io.ShapeDeserializer;
import io.ShapeSerializer;
import render.ShapeRenderer;
import shape.Shape;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Central registry of dynamically loaded shape plugins.
 * Applies each plugin to the canvas and JSON handler without modifying host code.
 */
public final class PluginRegistry {

  private final List<ShapePlugin> shapePlugins = new ArrayList<>();

  /**
   * Registers a successfully loaded shape plugin.
   *
   * @param plugin plugin instance
   */
  public void register(ShapePlugin plugin) {
    shapePlugins.add(plugin);
  }

  /**
   * Returns an unmodifiable view of loaded shape plugins.
   *
   * @return loaded plugins
   */
  public List<ShapePlugin> getShapePlugins() {
    return Collections.unmodifiableList(shapePlugins);
  }

  /**
   * Wires all shape plugins into the canvas and serialization handler.
   *
   * @param canvas      drawing canvas
   * @param jsonHandler file IO registry
   * @param toolbar     left toolbar panel that receives extra buttons
   * @param toolActivator callback that installs a drawing mouse adapter for a factory
   */
  public void applyAll(
      ShapeCanvas canvas,
      JsonFileHandler jsonHandler,
      JPanel toolbar,
      java.util.function.Consumer<ShapeFactory> toolActivator) {

    for (ShapePlugin plugin : shapePlugins) {
      registerOnCanvas(canvas, plugin);
      registerOnJson(jsonHandler, plugin);
      addToolbarButton(toolbar, plugin, toolActivator);
    }
  }

  /**
   * Registers renderer and editor for the plugin shape on the canvas.
   * Unchecked casts are safe because each plugin bundles matching types.
   */
  @SuppressWarnings({"unchecked", "rawtypes"})
  private void registerOnCanvas(ShapeCanvas canvas, ShapePlugin plugin) {
    canvas.registerShapeType(
        (Class) plugin.getShapeClass(),
        (ShapeRenderer) plugin.getRenderer(),
        (ShapeEditor) plugin.getEditor());
  }

  /**
   * Registers serializer and deserializer for the plugin shape.
   */
  @SuppressWarnings({"unchecked", "rawtypes"})
  private void registerOnJson(JsonFileHandler jsonHandler, ShapePlugin plugin) {
    jsonHandler.register(
        (Class) plugin.getShapeClass(),
        plugin.getTypeName(),
        (ShapeSerializer) plugin.getSerializer(),
        plugin.getDeserializer());
  }

  /**
   * Adds a toolbar button that activates the plugin drawing tool.
   */
  private void addToolbarButton(
      JPanel toolbar, ShapePlugin plugin, java.util.function.Consumer<ShapeFactory> toolActivator) {
    JButton button = new JButton(plugin.getToolbarButtonLabel());
    button.addActionListener(e -> toolActivator.accept(plugin.getFactory()));
    toolbar.add(button);
  }
}
