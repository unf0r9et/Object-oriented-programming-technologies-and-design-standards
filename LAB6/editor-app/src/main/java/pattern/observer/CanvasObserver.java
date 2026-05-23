package pattern.observer;

/**
 * Observer for canvas model changes (Observer pattern).
 */
public interface CanvasObserver {

  /**
   * Called when the number of shapes on the canvas changes.
   *
   * @param shapeCount current count
   */
  void onShapeCountChanged(int shapeCount);
}
