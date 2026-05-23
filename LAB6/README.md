# LAB 6 — Patterns + classmate plugin

Extends LAB5 with **Adapter** for a classmate's processing plugin, plus **Observer** and **Singleton**.

See [PATTERNS.md](PATTERNS.md) for pattern justification.

## Build & run

```bash
cd LAB6
mvn package
java -cp "editor-app/target/editor-app-1.0-SNAPSHOT.jar:editor-api/target/editor-api-1.0-SNAPSHOT.jar" org.example.Main
```

Folders:

- `plugins-runtime/` — shape plugins (pentagon, star)
- `processing-plugins/` — XML processors including **classmate-adapter-plugin**

## Classmate exchange

| Module | Role |
|--------|------|
| `classmate-legacy-api` | Foreign API (`DocumentMutator`) |
| `classmate-json-plugin` | Classmate's implementation |
| `classmate-adapter-plugin` | Adapter → `FileProcessingPlugin` |

Give `classmate-json-plugin-*.jar` to a classmate; use their JAR with a new adapter class if interfaces differ.
