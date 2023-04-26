package cs3500.marblesolitaire.view;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

/**
 * A class representing the view of the abstract marble solitaire game.
 */
public abstract class AbstractSolitaireTextView implements MarbleSolitaireView {
  MarbleSolitaireModelState state;
  Appendable appendable;

  /**
   * Creates an abstract marble solitaire text view class given a AbstractSolitaireModelState.
   *
   * @param gameState the state of the marble solitaire model.
   */
  public AbstractSolitaireTextView(MarbleSolitaireModelState gameState) {
    if (gameState == null) {
      throw new IllegalArgumentException("Game state cannot be null.");
    }
    this.state = gameState;
    this.appendable = System.out;
  }

  /**
   * Creates an abstract marble solitaire text view class with a AbstractSolitaireModelState,
   * and an Appendable.
   * @param gameState the state of the marble solitaire model.
   * @param appendable an appendable object to be used as an output.
   */
  public AbstractSolitaireTextView(MarbleSolitaireModelState gameState, Appendable appendable) {
    if ((gameState == null) || (appendable == null)) {
      throw new IllegalArgumentException("Game state or appendable cannot be null.");
    }
    this.state = gameState;
    this.appendable = appendable;
  }

  @Override
  public String toString() {
    StringBuilder output = new StringBuilder();
    int currRow = 0;
    boolean started = false;
    for (int x = 0; x < this.state.getBoardSize(); x++) {
      started = false;
      for (int y = 0; y < this.state.getBoardSize(); y++) {
        if (x != currRow) { // sets a new row every x iteration.
          output.append("\n");
          currRow = x;
        }
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
      currRow = x; // keep track of which row is currently being written
    }

    StringBuilder sBuilder = new StringBuilder();
    for (int i = 0; i < output.length(); i++) {
      sBuilder.append(output.charAt(i));
      if (i < output.length() - 1) {
        if (String.valueOf(output.charAt(i)).matches("\n")) {
          continue;
        } else if (output.charAt(i + 1) == 'O' || output.charAt(i + 1) == '_'
                || output.charAt(i + 1) == ' ') {
          sBuilder.append(" ");
          //output.insert(i, " ");
        }
      }
    }
    String boardState = sBuilder.toString();
    return boardState;
  }

  /**
   * Adds required spaces to the board (between all O and _).
   * @param input a StringBuilder object of the board.
   * @return a String of the board reformatted with spacing.
   */
  String addSpacesToBoard(StringBuilder input) {
    StringBuilder output = new StringBuilder();
    for (int i = 0; i < input.length(); i++) {
      output.append(input.charAt(i));
      if (i < input.length() - 1) {
        if (String.valueOf(input.charAt(i)).matches("\n")) {
          continue;
        } else if ((input.charAt(i) == 'O' || input.charAt(i) == '_')
                && (input.charAt(i + 1) == 'O' || input.charAt(i + 1) == '_')) {
          output.append(" ");
        }
      }
    }
    return output.toString();
  }
}
