package cs3500.marblesolitaire.view;

import java.io.IOException;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

/**
 * A class representing operations that should be offered by a view for a european version
 * of the Marble solitaire game.
 */
public class EuropeanSolitaireTextView extends AbstractSolitaireTextView
        implements MarbleSolitaireView {
  /**
   * Creates a european version of marble solitaire text view class given a
   * EuropeanSolitaireModelState.
   *
   * @param gameState the state of the marble solitaire model.
   */
  public EuropeanSolitaireTextView(MarbleSolitaireModelState gameState) {
    super(gameState);
  }

  /**
   * Creates a european marble solitaire text view class with a EuropeanSolitaireModelState,
   * and an Appendable.
   *
   * @param gameState  the state of the marble solitaire model.
   * @param appendable an appendable object to be used as an output.
   */
  public EuropeanSolitaireTextView(MarbleSolitaireModelState gameState, Appendable appendable) {
    super(gameState, appendable);
  }

  @Override
  public void renderBoard() throws IOException {
    this.renderMessage(this.toString() + System.lineSeparator());
  }

  @Override
  public void renderMessage(String message) throws IOException {
    try {
      appendable.append(message);
    } catch (IOException e) {
      throw new IOException(e.getMessage());
    }
  }
}
