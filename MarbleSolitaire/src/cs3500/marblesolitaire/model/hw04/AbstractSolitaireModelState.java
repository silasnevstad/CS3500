package cs3500.marblesolitaire.model.hw04;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

/**
 * This class represents operations that can be used to monitor the state of an abstract
 * marble solitaire model, without changing it.
 */
public abstract class AbstractSolitaireModelState implements MarbleSolitaireModelState {
  private AbstractSolitaireModel model;

  /**
   * Creates an abstract solitaire model state with a given model.
   * @param model the abstract model to be used.
   */
  public AbstractSolitaireModelState(AbstractSolitaireModel model) {
    this.model = model;
  }

  @Override
  public int getBoardSize() {
    return this.model.getBoardSize();
  }

  @Override
  public SlotState getSlotAt(int row, int col) throws IllegalArgumentException {
    return this.model.getSlotAt(row, col);
  }

  @Override
  public int getScore() {
    return this.model.getScore();
  }
}
