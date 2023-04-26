package cs3500.marblesolitaire.model.hw04;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

/**
 * This class represents the European solitaire model's state.
 * So it can be monitored but not changed.
 */
public class EuropeanSolitaireModelState extends AbstractSolitaireModelState
        implements MarbleSolitaireModelState {
  public EuropeanSolitaireModelState() {
    super(new EuropeanSolitaireModel());
  }

  public EuropeanSolitaireModelState(int thickness) {
    super(new EuropeanSolitaireModel(thickness));
  }

  /**
   * Creates a european solitaire model state with a given model.
   *
   * @param model the european model to be used.
   */
  public EuropeanSolitaireModelState(EuropeanSolitaireModel model) {
    super(model);
  }
}
