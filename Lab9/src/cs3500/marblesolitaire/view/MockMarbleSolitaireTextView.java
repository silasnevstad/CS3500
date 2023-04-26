package cs3500.marblesolitaire.view;

import java.io.IOException;

import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModelState;

/**
 * A class representing a mock marble solitaire text view.
 */
public class MockMarbleSolitaireTextView extends MarbleSolitaireTextView {
  StringBuilder log;

  /**
   * Creates a mock marble solitaire text view.
   * @param log the StringBuilder used to log inputs.
   */
  public MockMarbleSolitaireTextView(StringBuilder log) {
    super(new EnglishSolitaireModelState());
    this.log = log;
  }

  /**
   * Creates a mock marble solitaire text view with given marble solitaire state.
   * @param gameState the marble solitaire model state of game.
   * @param log the StringBuilder to to log inputs.
   */
  public MockMarbleSolitaireTextView(EnglishSolitaireModelState gameState, StringBuilder log) {
    super(gameState);
    this.log = log;
  }

  @Override
  public void renderMessage(String message) throws IOException {
    try {
      appendable.append(message);
    }
    catch (IOException e) {
      throw new IOException(e.getMessage());
    }
    log.append(message);
  }
}
