package plugin.star;

import editor.ShapeEditor;
import factory.ShapeFactory;
import io.ShapeDeserializer;
import io.ShapeSerializer;
import plugin.ShapePlugin;
import render.ShapeRenderer;

public class StarPlugin implements ShapePlugin {

  @Override
  public String getPluginId() {
    return "star-plugin";
  }

  @Override
  public String getDisplayName() {
    return "Star (plugin)";
  }

  @Override
  public Class<? extends shape.Shape> getShapeClass() {
    return Star.class;
  }

  @Override
  public String getTypeName() {
    return "Star";
  }

  @Override
  public ShapeFactory getFactory() {
    return new StarFactory();
  }

  @Override
  public ShapeRenderer<Star> getRenderer() {
    return new StarRenderer();
  }

  @Override
  public ShapeEditor<Star> getEditor() {
    return new StarEditor();
  }

  @Override
  public ShapeSerializer<Star> getSerializer() {
    return new StarSerializer();
  }

  @Override
  public ShapeDeserializer getDeserializer() {
    return new StarDeserializer();
  }
}
