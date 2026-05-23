package io;

import shape.Shape;

import java.io.IOException;
import java.util.List;

/**
 * Converts between in-memory shapes and an XML envelope that wraps JSON payload.
 * Processing plugins operate on the XML representation (variant 4 — XSLT).
 */
public final class XmlDocumentConverter {

  private final JsonFileHandler jsonHandler;

  public XmlDocumentConverter(JsonFileHandler jsonHandler) {
    this.jsonHandler = jsonHandler;
  }

  /**
   * Serializes shapes to XML document with embedded JSON array.
   *
   * @param shapes shapes on canvas
   * @return XML text
   */
  public String toXml(List<Shape> shapes) throws IOException {
    StringBuilder json = new StringBuilder();
    jsonHandler.writeJsonArray(json, shapes);
    return """
        <?xml version="1.0" encoding="UTF-8"?>
        <shapeDocument version="1">
          <metadata generated="host"/>
          <payload format="json"><![CDATA[
        """
        + json
        + """
        ]]></payload>
        </shapeDocument>
        """;
  }

  /**
   * Parses shapes from XML document produced by {@link #toXml(List)}.
   *
   * @param xml XML text, possibly transformed by plugins
   * @return restored shapes
   */
  public List<Shape> fromXml(String xml) throws IOException {
    int start = xml.indexOf("<![CDATA[");
    int end = xml.indexOf("]]>");
    if (start < 0 || end < 0) {
      throw new IOException("Invalid shape document: missing JSON CDATA payload");
    }
    String json = xml.substring(start + 9, end).trim();
    return jsonHandler.readJsonArray(json);
  }
}
