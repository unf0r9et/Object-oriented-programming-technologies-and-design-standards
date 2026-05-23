# LAB 4 — Shape plugins (10 points)

Graphic editor from LAB3 extended with **dynamic JAR plugins** and **RSA plugin signatures**.

## Build

```bash
cd LAB4
mvn package
```

Plugins are copied to `plugins-runtime/`. Run the app from `LAB4` so that directory is on the classpath search path:

```bash
java -cp "editor-app/target/editor-app-1.0-SNAPSHOT.jar:editor-api/target/editor-api-1.0-SNAPSHOT.jar" org.example.Main
```

## Shape plugins

| Plugin | Shape |
|--------|--------|
| `pentagon-plugin` | Regular pentagon |
| `star-plugin` | Five-pointed star |

## Sign plugins (10 pts)

```bash
java -cp tools/plugin-signer/target/plugin-signer-1.0-SNAPSHOT.jar:editor-api/target/editor-api-1.0-SNAPSHOT.jar \
  tools.PluginSigner pentagon-plugin plugins/pentagon-plugin/target/pentagon-plugin-1.0-SNAPSHOT.jar
```

Copy printed public key to `editor-app/src/main/resources/plugin-public.key` when keys are regenerated.

Strict mode: `--strict-signatures`

## CLI

- `--plugins-dir=<path>` — folder with `*.jar`
- `--plugin=<path-to.jar>` — load one JAR
- `--strict-signatures` — require valid `.sig` files
