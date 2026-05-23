package plugin.xmlpretty;

import plugin.FileProcessingPlugin;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;
import java.io.StringWriter;

/** Indents XML for readable storage (secondary processing plugin). */
public class XmlPrettyPlugin implements FileProcessingPlugin {

  @Override
  public String getPluginId() {
    return "xml-pretty-plugin";
  }

  @Override
  public String getDisplayName() {
    return "XML Pretty Print";
  }

  @Override
  public int getPriority() {
    return 200;
  }

  @Override
  public String processBeforeSave(String xml) {
    return indent(xml);
  }

  @Override
  public String processAfterLoad(String xml) {
    return xml;
  }

  private String indent(String xml) {
    try {
      Transformer transformer = TransformerFactory.newInstance().newTransformer();
      transformer.setOutputProperty(OutputKeys.INDENT, "yes");
      transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
      StringWriter writer = new StringWriter();
      transformer.transform(new StreamSource(new StringReader(xml)), new StreamResult(writer));
      return writer.toString();
    } catch (Exception ex) {
      return xml;
    }
  }
}
