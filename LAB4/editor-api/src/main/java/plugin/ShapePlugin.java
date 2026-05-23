package plugin;

import editor.ShapeEditor;
import factory.ShapeFactory;
import io.ShapeDeserializer;
import io.ShapeSerializer;
import render.ShapeRenderer;
import shape.Shape;

/**
 * Service Provider Interface for shape hierarchy plugins.
 * Each plugin JAR supplies a new shape type with factory, renderer, editor and IO handlers.
 */
public interface ShapePlugin {

    /**
     * Unique identifier of this plugin (used in logs and signature metadata).
     *
     * @return stable plugin id, e.g. {@code pentagon-plugin}
     */
    String getPluginId();

    /**
     * Human-readable name shown in the UI.
     *
     * @return display name
     */
    String getDisplayName();

    /**
     * Concrete shape class introduced by the plugin.
     *
     * @return shape class loaded from the plugin classloader
     */
    Class<? extends Shape> getShapeClass();

    /**
     * Type name stored in serialized files (JSON/XML).
     *
     * @return type discriminator string
     */
    String getTypeName();

    /**
     * Factory used by drawing tools to create the shape from mouse input.
     *
     * @return shape factory instance
     */
    ShapeFactory getFactory();

    /**
     * Renderer that paints the shape on the canvas.
     *
     * @return renderer instance
     */
    ShapeRenderer<? extends Shape> getRenderer();

    /**
     * Editor that builds the properties panel for the shape.
     *
     * @return editor instance
     */
    ShapeEditor<? extends Shape> getEditor();

    /**
     * Serializer that converts the shape to a property string.
     *
     * @return serializer instance
     */
    ShapeSerializer<? extends Shape> getSerializer();

    /**
     * Deserializer that rebuilds the shape from parsed properties.
     *
     * @return deserializer instance
     */
    ShapeDeserializer getDeserializer();

    /**
     * Label for the toolbar button that activates this shape's drawing tool.
     *
     * @return button text
     */
    default String getToolbarButtonLabel() {
        return getDisplayName();
    }
}
