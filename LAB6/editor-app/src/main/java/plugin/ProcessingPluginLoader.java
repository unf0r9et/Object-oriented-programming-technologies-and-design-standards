package plugin;

import plugin.signature.PluginSignatureVerifier;

import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.ServiceLoader;
import java.util.Set;
import java.util.stream.Stream;

/**
 * Discovers {@link FileProcessingPlugin} implementations from JAR files in a directory.
 * Uses one classloader for all JARs in the folder so plugin dependencies (e.g. classmate API) resolve.
 */
public final class ProcessingPluginLoader {

  private final PluginSignatureVerifier verifier;
  private final boolean strictSignatures;

  public ProcessingPluginLoader(boolean strictSignatures) {
    this.strictSignatures = strictSignatures;
    this.verifier =
        new PluginSignatureVerifier(PluginLoader.loadEmbeddedPublicKey(), strictSignatures);
  }

  /**
   * Loads processing plugins from all JARs in the given directory (shared classpath).
   *
   * @param dir plugins directory (e.g. {@code processing-plugins})
   * @return sorted list of plugins
   */
  public List<FileProcessingPlugin> loadFromDirectory(Path dir) throws IOException {
    if (!Files.isDirectory(dir)) {
      return List.of();
    }
    List<Path> jars = listJarFiles(dir);
    if (jars.isEmpty()) {
      return List.of();
    }
    verifyAll(jars);
    return discoverPlugins(createSharedClassLoader(jars));
  }

  /**
   * Loads plugins using every JAR in the same folder as the selected file (dependencies included).
   */
  public List<FileProcessingPlugin> loadJar(Path jarPath) throws IOException {
    Path dir = jarPath.getParent() != null ? jarPath.getParent() : Path.of(".");
    return loadFromDirectory(dir);
  }

  private List<Path> listJarFiles(Path dir) throws IOException {
    try (Stream<Path> stream = Files.list(dir)) {
      return stream.filter(p -> p.toString().endsWith(".jar")).sorted().toList();
    }
  }

  private void verifyAll(List<Path> jars) throws IOException {
    for (Path jar : jars) {
      try {
        verifier.verify(jar);
      } catch (GeneralSecurityException ex) {
        if (strictSignatures) {
          throw new IOException("Processing plugin signature failed: " + jar.getFileName(), ex);
        }
        System.err.println("Warning: unsigned plugin JAR: " + jar.getFileName());
      }
    }
  }

  private URLClassLoader createSharedClassLoader(List<Path> jars) throws IOException {
    URL[] urls = new URL[jars.size()];
    for (int i = 0; i < jars.size(); i++) {
      urls[i] = jars.get(i).toUri().toURL();
    }
    return new URLClassLoader(urls, FileProcessingPlugin.class.getClassLoader());
  }

  private List<FileProcessingPlugin> discoverPlugins(URLClassLoader loader) {
    Set<String> seen = new LinkedHashSet<>();
    List<FileProcessingPlugin> plugins = new ArrayList<>();
    for (FileProcessingPlugin plugin : ServiceLoader.load(FileProcessingPlugin.class, loader)) {
      if (seen.add(plugin.getPluginId())) {
        plugins.add(plugin);
        System.out.println("Loaded processing plugin: " + plugin.getPluginId());
      }
    }
    plugins.sort(Comparator.comparingInt(FileProcessingPlugin::getPriority));
    return plugins;
  }
}
