package com.classmate.legacy;

/**
 * Foreign API from a classmate's lab (simulated exchange).
 * Uses different method names than our {@link plugin.FileProcessingPlugin}.
 */
public interface DocumentMutator {

  /** Classmate's pre-save hook. */
  String mutateBeforePersist(String documentText);

  /** Classmate's post-load hook. */
  String mutateAfterRestore(String documentText);

  /** Display label in classmate's application. */
  String label();
}
