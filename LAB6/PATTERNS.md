# LAB 6 — Design patterns

## 1. Adapter (required)

**Where:** `plugin.adapter.ClassmateMutatorAdapter`

**Why:** A classmate's plugin uses `com.classmate.legacy.DocumentMutator` (`mutateBeforePersist` / `mutateAfterRestore`). Our host expects `plugin.FileProcessingPlugin`. The adapter translates method names and integrates the foreign JAR without changing host code.

**Classmate plugin:** `plugins/classmate-json-plugin` (whitespace compactor outside CDATA).

**Loaded via:** `classmate-adapter-plugin` registered as `FileProcessingPlugin`.

## 2. Observer

**Where:** `pattern.observer.CanvasSubject`, `CanvasObserver`, wired in `ShapeCanvas`.

**Why:** The status bar must update when shapes are added, removed, or loaded without polling. The canvas notifies observers when the shape count changes.

## 3. Singleton

**Where:** `pattern.singleton.EditorApplicationContext`

**Why:** One global `PluginRegistry` and `ProcessingPipeline` for the whole application; avoids duplicate registries if UI or loaders are refactored.

## 4. Strategy (processing pipeline)

**Where:** `plugin.ProcessingPipeline`

**Why:** Multiple `FileProcessingPlugin` implementations are interchangeable steps applied in priority order on save/load.
