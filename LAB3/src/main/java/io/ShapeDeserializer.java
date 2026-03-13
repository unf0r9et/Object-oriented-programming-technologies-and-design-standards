package io;
import shape.Shape;
import java.util.Map;

/**
 * Creates a shape from a Map of parsed JSON properties.
 */
public interface ShapeDeserializer {
    Shape deserialize(Map<String, String> properties);
}