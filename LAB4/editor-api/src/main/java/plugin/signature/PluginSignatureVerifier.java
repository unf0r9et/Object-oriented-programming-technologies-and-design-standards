package plugin.signature;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.X509EncodedKeySpec;
import java.time.Instant;
import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Verifies plugin JAR integrity and RSA signature with activation time window.
 * The host application embeds the public key; private key is used only by {@code plugin-signer}.
 */
public final class PluginSignatureVerifier {

  private static final Pattern FIELD =
      Pattern.compile("\"([^\"]+)\"\\s*:\\s*(?:\"([^\"]*)\"|(-?\\d+))");

  private final PublicKey publicKey;
  private final boolean strictMode;

  /**
   * Creates a verifier using the embedded RSA public key (X.509, Base64).
   *
   * @param publicKeyBase64 X.509 encoded public key
   * @param strictMode      when {@code true}, unsigned plugins are rejected
   */
  public PluginSignatureVerifier(String publicKeyBase64, boolean strictMode) {
    this.strictMode = strictMode;
    try {
      byte[] keyBytes = Base64.getDecoder().decode(publicKeyBase64);
      this.publicKey =
          KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(keyBytes));
    } catch (GeneralSecurityException e) {
      throw new IllegalStateException("Invalid embedded public key", e);
    }
  }

  /**
   * Verifies the plugin JAR against its optional {@code .sig} sidecar file.
   *
   * @param jarPath path to plugin JAR
   * @return parsed signature metadata when verification succeeds
   * @throws IOException              if IO fails
   * @throws GeneralSecurityException if signature or digest mismatch
   */
  public PluginSignature verify(Path jarPath)
      throws IOException, GeneralSecurityException {
    Path sigPath = Path.of(jarPath.toString() + ".sig");
    if (!Files.exists(sigPath)) {
      if (strictMode) {
        throw new GeneralSecurityException("Missing signature file: " + sigPath);
      }
      return null;
    }

    String json = Files.readString(sigPath, StandardCharsets.UTF_8);
    PluginSignature sig = parseSignatureJson(json);
    byte[] jarBytes = Files.readAllBytes(jarPath);
    String actualHash = sha256Hex(jarBytes);
    if (!actualHash.equalsIgnoreCase(sig.sha256Hex())) {
      throw new GeneralSecurityException("JAR digest mismatch for " + jarPath.getFileName());
    }

    long now = Instant.now().toEpochMilli();
    if (now < sig.notBeforeEpochMillis() || now > sig.notAfterEpochMillis()) {
      throw new GeneralSecurityException("Plugin signature is outside the activation window");
    }

    Signature rsa = Signature.getInstance("SHA256withRSA");
    rsa.initVerify(publicKey);
    rsa.update(sig.canonicalPayload());
    byte[] signatureBytes = Base64.getDecoder().decode(sig.signatureBase64());
    if (!rsa.verify(signatureBytes)) {
      throw new GeneralSecurityException("Invalid RSA signature for " + sig.pluginId());
    }
    return sig;
  }

  /**
   * Computes SHA-256 digest of the given bytes as lowercase hex.
   *
   * @param data input bytes
   * @return hex digest
   */
  public static String sha256Hex(byte[] data) throws GeneralSecurityException {
    MessageDigest digest = MessageDigest.getInstance("SHA-256");
    byte[] hash = digest.digest(data);
    StringBuilder sb = new StringBuilder(hash.length * 2);
    for (byte b : hash) {
      sb.append(String.format("%02x", b));
    }
    return sb.toString();
  }

  /**
   * Minimal JSON parser for signature sidecar files (avoids extra dependencies in API).
   *
   * @param json raw JSON text
   * @return parsed {@link PluginSignature}
   */
  static PluginSignature parseSignatureJson(String json) {
    java.util.Map<String, String> fields = new java.util.HashMap<>();
    Matcher matcher = FIELD.matcher(json);
    while (matcher.find()) {
      String key = matcher.group(1);
      String value = matcher.group(2) != null ? matcher.group(2) : matcher.group(3);
      fields.put(key, value);
    }
    return new PluginSignature(
        fields.get("pluginId"),
        fields.get("jarFileName"),
        fields.get("sha256Hex"),
        Long.parseLong(fields.get("notBeforeEpochMillis")),
        Long.parseLong(fields.get("notAfterEpochMillis")),
        fields.get("signatureBase64"));
  }
}
