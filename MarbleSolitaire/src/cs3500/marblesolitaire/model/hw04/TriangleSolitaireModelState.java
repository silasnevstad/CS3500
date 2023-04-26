package cs3500.marblesolitaire.model.hw04;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

/**
 * This class represents the triangle solitaire model's state.
 * So it can be monitored but not changed.
 */
public class TriangleSolitaireModelState extends AbstractSolitaireModelState
        implements MarbleSolitaireModelState {
  /**
   * Creates a triangle solitaire model state with a default model.
   */
  public TriangleSolitaireModelState() {
    super(new TriangleSolitaireModel());
  }

  /**
   * Creates a triangle solitaire model state with a given model.
   *
   * @param model the abstract model to be used.
   */
  public TriangleSolitaireModelState(AbstractSolitaireModel model) {
    super(model);
  }

  /**
   * Creates a triangle solitaire model state with provided thickness.
   * @param thickness the specified length of the bottom most row.
   */
  public TriangleSolitaireModelState(int thickness) {
    super(new TriangleSolitaireModel(thickness));
  }
}
