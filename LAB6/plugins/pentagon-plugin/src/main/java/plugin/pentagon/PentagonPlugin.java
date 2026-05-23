package plugin.pentagon;

import editor.ShapeEditor;
import factory.ShapeFactory;
import io.ShapeDeserializer;
import io.ShapeSerializer;
import plugin.ShapePlugin;
import render.ShapeRenderer;

/** Shape plugin exposing the Pentagon type to the host application. */
public class PentagonPlugin implements ShapePlugin {

  @Override
  public String getPluginId() {
    return "pentagon-plugin";
  }

  @Override
  public String getDisplayName() {
    return "Pentagon (plugin)";
  }

  @Override
  public Class<? extends shape.Shape> getShapeClass() {
    return Pentagon.class;
  }

  @Override
  public String getTypeName() {
    return "Pentagon";
  }

  @Override
  public ShapeFactory getFactory() {
    return new PentagonFactory();
  }

  @Override
  public ShapeRenderer<Pentagon> getRenderer() {
    return new PentagonRenderer();
  }

  @Override
  public ShapeEditor<Pentagon> getEditor() {
    return new PentagonEditor();
  }

  @Override
  public ShapeSerializer<Pentagon> getSerializer() {
    return new PentagonSerializer();
  }

  @Override
  public ShapeDeserializer getDeserializer() {
    return new PentagonDeserializer();
  }
}
