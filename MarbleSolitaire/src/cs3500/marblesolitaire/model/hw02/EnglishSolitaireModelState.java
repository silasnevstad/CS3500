package cs3500.marblesolitaire.model.hw02;

import cs3500.marblesolitaire.model.hw04.AbstractSolitaireModelState;

/**
 * This class represents the English solitaire model's state.
 * So it can be monitored but not changed.
 */
public class EnglishSolitaireModelState extends AbstractSolitaireModelState {
  /**
   * Creates an english solitaire model state with a default model.
   */
  public EnglishSolitaireModelState() {
    super(new EnglishSolitaireModel());
  }

  /**
   * Creates an english solitaire model state with provided thickness.
   * @param thickness the specified thickness of the model.
   */
  public EnglishSolitaireModelState(int thickness) {
    super(new EnglishSolitaireModel(thickness));
  }

  /**
   * Creates an english solitaire model state with a given model.
   *
   * @param model the abstract model to be used.
   */
  public EnglishSolitaireModelState(EnglishSolitaireModel model) {
    super(model);
  }
}
