package io;
import shape.Shape;

/**
 * Serializes a shape into a JSON string of properties.
 *
 * @param <T> concrete shape type being serialized
 */
public interface ShapeSerializer<T extends Shape> {
    /**
     * Converts the given shape into a JSON fragment containing its properties.
     *
     * @param shape shape instance to serialize
     * @return JSON string representing the shape properties (without surrounding braces)
     */
    String serialize(T shape);
}