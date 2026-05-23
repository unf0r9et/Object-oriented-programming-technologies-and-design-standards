package com.classmate.plugin;

import com.classmate.legacy.DocumentMutator;

/**
 * Classmate's functional plugin: compacts whitespace outside CDATA in XML documents.
 */
public class JsonMinifyMutator implements DocumentMutator {

  @Override
  public String mutateBeforePersist(String documentText) {
    return compactOutsideCdata(documentText);
  }

  @Override
  public String mutateAfterRestore(String documentText) {
    return documentText;
  }

  @Override
  public String label() {
    return "Classmate JSON/CDATA compactor";
  }

  private String compactOutsideCdata(String xml) {
    int cdataStart = xml.indexOf("<![CDATA[");
    int cdataEnd = xml.indexOf("]]>");
    if (cdataStart < 0 || cdataEnd < 0) {
      return xml.replaceAll(">\\s+<", "><").trim();
    }
    String head = xml.substring(0, cdataStart).replaceAll(">\\s+<", "><").trim();
    String payload = xml.substring(cdataStart, cdataEnd + 3);
    String tail = xml.substring(cdataEnd + 3).replaceAll(">\\s+<", "><").trim();
    return head + payload + tail;
  }
}
