package io;
import shape.Shape;
import java.util.Map;

/**
 * Creates a shape from a Map of parsed JSON properties.
 */
public interface ShapeDeserializer {
    /**
     * Reconstructs a shape from a map of string properties parsed from JSON.
     *
     * @param properties key-value map containing serialized fields
     * @return deserialized shape instance
     */
    Shape deserialize(Map<String, String> properties);
}