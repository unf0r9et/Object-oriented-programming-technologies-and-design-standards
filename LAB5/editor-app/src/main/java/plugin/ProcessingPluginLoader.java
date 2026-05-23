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
import java.util.List;
import java.util.ServiceLoader;
import java.util.stream.Stream;

/**
 * Discovers {@link FileProcessingPlugin} implementations from JAR files in a directory.
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
   * Loads processing plugins from all JARs in the given directory.
   *
   * @param dir plugins directory (e.g. {@code processing-plugins})
   * @return sorted list of plugins
   */
  public List<FileProcessingPlugin> loadFromDirectory(Path dir) throws IOException {
    List<FileProcessingPlugin> plugins = new ArrayList<>();
    if (!Files.isDirectory(dir)) {
      return plugins;
    }
    try (Stream<Path> stream = Files.list(dir)) {
      List<Path> jars = stream.filter(p -> p.toString().endsWith(".jar")).sorted().toList();
      for (Path jar : jars) {
        plugins.addAll(loadJar(jar));
      }
    }
    plugins.sort(Comparator.comparingInt(FileProcessingPlugin::getPriority));
    return plugins;
  }

  /**
   * Loads a single processing plugin JAR (also used when user picks a file in the UI).
   */
  public List<FileProcessingPlugin> loadJar(Path jarPath) throws IOException {
    try {
      verifier.verify(jarPath);
    } catch (GeneralSecurityException ex) {
      if (strictSignatures) {
        throw new IOException("Processing plugin signature failed: " + jarPath.getFileName(), ex);
      }
      System.err.println("Warning: unsigned processing plugin: " + jarPath.getFileName());
    }

    try {
      URLClassLoader loader =
          new URLClassLoader(new URL[] {jarPath.toUri().toURL()}, FileProcessingPlugin.class.getClassLoader());
      List<FileProcessingPlugin> list = new ArrayList<>();
      for (FileProcessingPlugin plugin : ServiceLoader.load(FileProcessingPlugin.class, loader)) {
        list.add(plugin);
        System.out.println("Loaded processing plugin: " + plugin.getPluginId());
      }
      return list;
    } catch (Exception ex) {
      throw new IOException("Cannot load processing plugin JAR: " + jarPath, ex);
    }
  }
}
