package io;
import shape.Shape;

/**
 * Serializes a shape into a JSON string of properties.
 */
public interface ShapeSerializer<T extends Shape> {
    String serialize(T shape);
}