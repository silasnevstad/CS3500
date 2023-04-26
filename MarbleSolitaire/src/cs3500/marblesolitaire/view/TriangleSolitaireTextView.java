package cs3500.marblesolitaire.view;

import java.io.IOException;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

/**
 * A class representing operations that should be offered by a view for a triangle version
 * of the Marble solitaire game.
 */
public class TriangleSolitaireTextView extends AbstractSolitaireTextView
        implements MarbleSolitaireView {

  /**
   * Creates a triangle version of marble solitaire text view class given a
   * TriangleSolitaireModelState.
   *
   * @param gameState the state of the marble solitaire model.
   */
  public TriangleSolitaireTextView(MarbleSolitaireModelState gameState) {
    super(gameState);
  }

  /**
   * Creates a triangle marble solitaire text view class with a TriangleSolitaireModelState,
   * and an Appendable.
   *
   * @param gameState  the state of the marble solitaire model.
   * @param appendable an appendable object to be used as an output.
   */
  public TriangleSolitaireTextView(MarbleSolitaireModelState gameState, Appendable appendable) {
    super(gameState, appendable);
  }

  /**
   * Creates a string of the triangle marble solitaire board.
   * @return a String of the board.
   */
  public String toString() {
    return this.addSpacesToBoard(this.createBoard());
  }

  @Override
  public void renderBoard() throws IOException {
    this.renderMessage(this.addSpacesToBoard(this.createBoard()) + System.lineSeparator());
  }

  @Override
  public void renderMessage(String message) throws IOException {
    try {
      appendable.append(message);
    } catch (IOException e) {
      throw new IOException(e.getMessage());
    }
  }

  /**
   * Creates a triangle marble solitaire board without spacing.
   *
   * @return a stringBuilder of the board.
   */
  private StringBuilder createBoard() {
    StringBuilder output = new StringBuilder();
    int currRow = 0;
    boolean started = false;
    for (int x = 0; x < this.state.getBoardSize(); x++) {
      started = false;
      if (x != currRow) {
        output.append("\n");
        currRow = x;
      }
      for (int sp = 1; sp < this.state.getBoardSize() - x; sp++) {
        output.append(" ");
      }
      for (int y = 0; y < x + 1; y++) {
        MarbleSolitaireModelState.SlotState currSlotState = this.state.getSlotAt(x, y);
        if (currSlotState == MarbleSolitaireModelState.SlotState.Marble) {
          output.append("O");
          started = true;
        } else if (currSlotState == MarbleSolitaireModelState.SlotState.Empty) {
          output.append("_");
          started = true;
        } else if (currSlotState == MarbleSolitaireModelState.SlotState.Invalid) {
          if (!started) {
            output.append(" ");
          }
        }
      }
      currRow = x;
    }
    return output;
  }
}
