package tools;

import plugin.signature.PluginSignature;
import plugin.signature.PluginSignatureVerifier;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.Signature;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;

/**
 * CLI utility that generates an RSA key pair (once) and signs plugin JAR files.
 * Usage: {@code java -jar plugin-signer.jar <pluginId> <path-to.jar> [daysValid]}
 */
public final class PluginSigner {

  private PluginSigner() {}

  public static void main(String[] args) throws Exception {
    if (args.length < 2) {
      System.err.println("Usage: PluginSigner <pluginId> <jarPath> [daysValid=365]");
      System.exit(1);
    }
    String pluginId = args[0];
    Path jarPath = Path.of(args[1]);
    int daysValid = args.length > 2 ? Integer.parseInt(args[2]) : 365;

    Path keysDir = Path.of("keys");
    Files.createDirectories(keysDir);
    Path privateKeyPath = keysDir.resolve("plugin-private.key");
    Path publicKeyPath = keysDir.resolve("plugin-public.key");

    if (!Files.exists(privateKeyPath)) {
      KeyPairGenerator gen = KeyPairGenerator.getInstance("RSA");
      gen.initialize(2048);
      KeyPair pair = gen.generateKeyPair();
      Files.write(privateKeyPath, pair.getPrivate().getEncoded());
      Files.write(publicKeyPath, pair.getPublic().getEncoded());
      System.out.println("Generated new RSA key pair in " + keysDir.toAbsolutePath());
      System.out.println("Public key (Base64) for editor-app:");
      System.out.println(Base64.getEncoder().encodeToString(pair.getPublic().getEncoded()));
    }

    byte[] privateBytes = Files.readAllBytes(privateKeyPath);
    PrivateKey privateKey =
        java.security.KeyFactory.getInstance("RSA")
            .generatePrivate(new java.security.spec.PKCS8EncodedKeySpec(privateBytes));

    byte[] jarBytes = Files.readAllBytes(jarPath);
    String sha256 = PluginSignatureVerifier.sha256Hex(jarBytes);
    long notBefore = Instant.now().minus(1, ChronoUnit.HOURS).toEpochMilli();
    long notAfter = Instant.now().plus(daysValid, ChronoUnit.DAYS).toEpochMilli();

    PluginSignature unsigned =
        new PluginSignature(pluginId, jarPath.getFileName().toString(), sha256, notBefore, notAfter, "");

    Signature rsa = Signature.getInstance("SHA256withRSA");
    rsa.initSign(privateKey);
    rsa.update(unsigned.canonicalPayload());
    String signatureBase64 = Base64.getEncoder().encodeToString(rsa.sign());

    PluginSignature signed =
        new PluginSignature(pluginId, jarPath.getFileName().toString(), sha256, notBefore, notAfter, signatureBase64);

    String json =
        "{\n"
            + "  \"pluginId\": \"" + signed.pluginId() + "\",\n"
            + "  \"jarFileName\": \"" + signed.jarFileName() + "\",\n"
            + "  \"sha256Hex\": \"" + signed.sha256Hex() + "\",\n"
            + "  \"notBeforeEpochMillis\": " + signed.notBeforeEpochMillis() + ",\n"
            + "  \"notAfterEpochMillis\": " + signed.notAfterEpochMillis() + ",\n"
            + "  \"signatureBase64\": \"" + signed.signatureBase64() + "\"\n"
            + "}\n";

    Path sigPath = Path.of(jarPath.toString() + ".sig");
    Files.writeString(sigPath, json, StandardCharsets.UTF_8);
    System.out.println("Wrote signature: " + sigPath.toAbsolutePath());
  }
}
