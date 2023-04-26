package cs3500.marblesolitaire.view;

import java.io.IOException;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

/**
 * A class representing the view of the marble solitaire game.
 */
public class MarbleSolitaireTextView extends AbstractSolitaireTextView
        implements MarbleSolitaireView {

  /**
   * Creates a marble solitaire text view class given a MarbleSolitaireModelState.
   *
   * @param gameState the state of the marble solitaire model.
   */
  public MarbleSolitaireTextView(MarbleSolitaireModelState gameState) {
    super(gameState);
  }

  /**
   * Creates a marble solitaire text view class with a MarbleSolitaireModelState, and an Appendable.
   * @param gameState the state of the marble solitaire model.
   * @param appendable an appendable object to be used as an output.
   */
  public MarbleSolitaireTextView(MarbleSolitaireModelState gameState, Appendable appendable) {
    super(gameState, appendable);
  }

  @Override
  public void renderBoard() throws IOException {
    String output = new String();
    int currRow = 0;
    boolean started = false;
    for (int x = 0; x < this.state.getBoardSize(); x++) {
      started = false;
      for (int y = 0; y < this.state.getBoardSize(); y++) {
        if (x != currRow) { // sets a new row every x iteration.
          output += "\n";
          currRow = x;
        }
        MarbleSolitaireModelState.SlotState currSlotState = this.state.getSlotAt(x, y);
        if (currSlotState == MarbleSolitaireModelState.SlotState.Marble) {
          output += "O";
          started = true;
        } else if (currSlotState == MarbleSolitaireModelState.SlotState.Empty) {
          output += "_";
          started = true;
        } else if (currSlotState == MarbleSolitaireModelState.SlotState.Invalid) {
          if (!started) {
            output += " ";
          }
        }
      }
      currRow = x; // keep track of which row is currently being written
    }

    StringBuilder sBuilder = new StringBuilder();
    for (int i = 0; i < output.length(); i++) {
      sBuilder.append(output.charAt(i));
      if (i < output.length() - 1) {
        if (String.valueOf(output.charAt(i)).matches("\n")) {
          continue;
        } else if (output.charAt(i + 1) == 'O'
                || output.charAt(i + 1) == '_'
                || output.charAt(i + 1) == ' ') {
          sBuilder.append(" ");
        }
      }
    }

    String boardState = sBuilder.toString();
    this.renderMessage(boardState + System.lineSeparator());
  }

  @Override
  public void renderMessage(String message) throws IOException {
    try {
      appendable.append(message);
    }
    catch (IOException e) {
      throw new IOException(e.getMessage());
    }
  }
}
