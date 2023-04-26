package cs3500.marblesolitaire.controller;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.regex.Pattern;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireView;

public class MarbleSolitaireControllerGUI implements MarbleSolitaireController {
  private MarbleSolitaireModel model;
  private MarbleSolitaireView view;
  private Readable readable;
  private Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");

  /**
   * Creates a controller with a specified model, view and readable object (input).
   *
   * @param model    the provided model to be used.
   * @param view     the provided view to be used.
   * @param readable the provided readable to be used.
   */
  public MarbleSolitaireControllerGUI(MarbleSolitaireModel model, MarbleSolitaireView view,
                                      Readable readable) {
    if ((model == null) || (readable == null) || (view == null)) {
      throw new IllegalArgumentException("Model, view, and readable cannot be null.");
    }
    this.model = model;
    this.view = view;
    this.readable = readable;
  }

  @Override
  public void playGame() throws IllegalStateException {
    Scanner sc = new Scanner(readable);
    int[] positions = new int[4];
    int indexOfPos = 0;
    String userInstruction = new String();
    String[] tokens;
    boolean gameOver = this.model.isGameOver();

    while (!gameOver) {
      if (sc.hasNext()) {
        try {
          userInstruction = sc.nextLine(); //take an instruction
          tokens = userInstruction.split(" ");
        } catch (NoSuchElementException e) {
          throw new IllegalStateException(e.getMessage());
        }
      } else {
        throw new IllegalStateException("No input given.");
      }
      switch (tokens[0]) {
        case "quit":
        case "Q":
        case "q":
          this.writeMessage("Game quit!" + System.lineSeparator());
          this.writeMessage("State of game when quit:" + System.lineSeparator());
          try {
            this.view.renderBoard();
          } catch (IOException e) {
            throw new IllegalStateException("Failed to render board.");
          }
          this.writeMessage("Score: " + this.model.getScore() + System.lineSeparator());
          return;
        default:
          try {
            this.view.renderBoard();
          } catch (IOException e) {
            throw new IllegalStateException("Failed to render board.");
          }
          this.writeMessage("Score: " + this.model.getScore() + System.lineSeparator());
          if (isPosNumeric(tokens[0])) {
            if (model instanceof TriangleSolitaireModel) {
              positions[0] = Integer.valueOf(tokens[0]);
              positions[1] = Integer.valueOf(tokens[1]);
              positions[2] = Integer.valueOf(tokens[2]);
              positions[3] = Integer.valueOf(tokens[3]);
            } else {
              positions[0] = Integer.valueOf(tokens[0]) - 1;
              positions[1] = Integer.valueOf(tokens[1]) - 1;
              positions[2] = Integer.valueOf(tokens[2]) - 1;
              positions[3] = Integer.valueOf(tokens[3]) - 1; // starting at 1
            }
            try {
              this.model.move(positions[0], positions[1], positions[2], positions[3]);
              gameOver = this.model.isGameOver();
            } catch (IllegalArgumentException e) {
              //  throw new IllegalStateException(e.getMessage());
              writeMessage("Invalid move. Play Again." + System.lineSeparator());
            }
          } else {
            writeMessage("Invalid move. Play Again." + System.lineSeparator());
          }
      }
    }
  }


  /**
   * Writes a message to the output.
   *
   * @param message the String wanted to output.
   * @throws IllegalStateException if IOException.
   */
  public void writeMessage(String message) throws IllegalStateException {
    try {
      this.view.renderMessage(message);
    } catch (IOException e) {
      throw new IllegalStateException(e.getMessage());
    }
  }

  /**
   * Returns whether String is numeric and positive.
   *
   * @param strNum string to perform on.
   * @return boolean whether strNum is a positive numeric (true if positive numeric, else false).
   */
  private boolean isPosNumeric(String strNum) {
    if (strNum == null) {
      return false;
    }
    if (!pattern.matcher(strNum).matches()) {
      return false;
    }

    return Integer.valueOf(strNum) >= 0;
  }
}
