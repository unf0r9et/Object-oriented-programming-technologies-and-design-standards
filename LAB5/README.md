# LAB 5 — Processing plugins (variant 4: XSLT)

Based on LAB4. Saves/loads **shapes.xml** through a chain of processing plugins.

## Variant 4

**XSLT transformation** (`xslt-transform-plugin`) — configurable XSLT path or built-in template that updates `<metadata>`.

Additional plugins:

- `xml-metadata-plugin` — author and timestamp in metadata (settings panel)
- `xml-pretty-plugin` — pretty-print on save

## Build & run

```bash
cd LAB5
mvn package
java -cp "editor-app/target/editor-app-1.0-SNAPSHOT.jar:editor-api/target/editor-api-1.0-SNAPSHOT.jar" org.example.Main
```

Processing plugins load from `processing-plugins/` automatically.

## Settings

Menu **Settings → Processing plugins…** — per-plugin options and **Load processing plugin…** to pick a JAR file.
