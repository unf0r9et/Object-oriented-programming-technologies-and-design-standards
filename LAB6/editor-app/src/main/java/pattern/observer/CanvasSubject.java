package pattern.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Subject in the Observer pattern: notifies listeners about canvas state changes.
 */
public final class CanvasSubject {

  private final List<CanvasObserver> observers = new ArrayList<>();

  /**
   * Registers a new observer.
   */
  public void addObserver(CanvasObserver observer) {
    observers.add(observer);
  }

  /**
   * Broadcasts updated shape count to all observers.
   */
  public void notifyShapeCount(int count) {
    for (CanvasObserver observer : observers) {
      observer.onShapeCountChanged(count);
    }
  }
}
