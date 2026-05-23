package io;

import shape.Shape;
import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Handles saving and loading JSON manually using Registries.
 * Strictly avoids if/switch statements.
 */
public class JsonFileHandler {
    private Map<Class<? extends Shape>, ShapeSerializer<Shape>> serializers = new HashMap<>();
    private Map<String, ShapeDeserializer> deserializers = new HashMap<>();

    /**
     * Registers a shape type along with its JSON serializer and deserializer.
     *
     * @param clazz        concrete shape class
     * @param typeName     type identifier stored in JSON
     * @param serializer   serializer for converting shapes to JSON fragments
     * @param deserializer deserializer for reconstructing shapes from properties
     * @param <T>          shape subtype
     */
    @SuppressWarnings("unchecked")
    public <T extends Shape> void register(Class<T> clazz, String typeName, ShapeSerializer<T> serializer, ShapeDeserializer deserializer) {
        serializers.put(clazz, (ShapeSerializer<Shape>) serializer);
        deserializers.put(typeName, deserializer);
    }

    /**
     * Saves a list of shapes to the given file in a simple JSON array format.
     *
     * @param file   destination JSON file
     * @param shapes shapes to serialize
     * @throws IOException if writing to the file fails
     */
    public void save(File file, List<Shape> shapes) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write("[\n");
            for (int i = 0; i < shapes.size(); i++) {
                Shape s = shapes.get(i);
                ShapeSerializer<Shape> serializer = serializers.get(s.getClass());
                if (serializer != null) {
                    writer.write("  {" + serializer.serialize(s) + "}");
                    if (i < shapes.size() - 1) writer.write(",");
                    writer.write("\n");
                }
            }
            writer.write("]\n");
        }
    }

    /**
     * Loads shapes from a JSON file previously written by {@link #save(File, List)}.
     * Uses simple pattern matching and the registered deserializers instead of a full JSON library.
     *
     * @param file source JSON file
     * @return list of deserialized shapes
     * @throws IOException if reading the file fails
     */
    public List<Shape> load(File file) throws IOException {
        List<Shape> loadedShapes = new ArrayList<>();
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) content.append(line);
        }

        // Parse JSON objects {...} manually
        Matcher objMatcher = Pattern.compile("\\{([^}]+)\\}").matcher(content.toString());
        while (objMatcher.find()) {
            String objContent = objMatcher.group(1);
            Map<String, String> properties = new HashMap<>();

            // Parse key-value pairs "key":"value" or "key":number
            Matcher pairMatcher = Pattern.compile("\"([^\"]+)\"\\s*:\\s*(?:\"([^\"]+)\"|([^,]+))").matcher(objContent);
            while (pairMatcher.find()) {
                String key = pairMatcher.group(1);
                String value = pairMatcher.group(2) != null ? pairMatcher.group(2) : pairMatcher.group(3);
                properties.put(key, value.trim());
            }

            // Factory Magic: No if statements!
            String type = properties.get("type");
            ShapeDeserializer deserializer = deserializers.get(type);
            if (deserializer != null) {
                loadedShapes.add(deserializer.deserialize(properties));
            }
        }
        return loadedShapes;
    }
}