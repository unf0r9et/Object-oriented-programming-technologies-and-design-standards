package plugin.signature;

/**
 * Immutable description of a signed plugin artifact.
 * Stored alongside the plugin JAR as a {@code .sig} JSON file.
 *
 * @param pluginId     plugin identifier
 * @param jarFileName  name of the signed JAR file
 * @param sha256Hex    SHA-256 digest of the JAR bytes (hex)
 * @param notBeforeEpochMillis  earliest valid activation time (UTC epoch ms)
 * @param notAfterEpochMillis   expiration time (UTC epoch ms)
 * @param signatureBase64 RSA signature over canonical payload bytes
 */
public record PluginSignature(
        String pluginId,
        String jarFileName,
        String sha256Hex,
        long notBeforeEpochMillis,
        long notAfterEpochMillis,
        String signatureBase64
) {
    /**
     * Builds the canonical byte payload that is signed and verified.
     *
     * @return UTF-8 bytes of {@code pluginId|jarFileName|sha256|notBefore|notAfter}
     */
    public byte[] canonicalPayload() {
        String payload = pluginId + "|" + jarFileName + "|" + sha256Hex + "|"
                + notBeforeEpochMillis + "|" + notAfterEpochMillis;
        return payload.getBytes(java.nio.charset.StandardCharsets.UTF_8);
    }
}
