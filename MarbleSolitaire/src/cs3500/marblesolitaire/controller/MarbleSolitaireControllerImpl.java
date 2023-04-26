package cs3500.marblesolitaire.controller;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireView;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * A class representing a marble solitaire controller.
 */
public class MarbleSolitaireControllerImpl implements MarbleSolitaireController {
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
  public MarbleSolitaireControllerImpl(MarbleSolitaireModel model, MarbleSolitaireView view,
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
    boolean gameOver = this.model.isGameOver();

    while (!gameOver) {
      if (sc.hasNext()) {
        try {
          userInstruction = sc.next(); //take an instruction
        } catch (NoSuchElementException e) {
          throw new IllegalStateException(e.getMessage());
        }
      } else {
        throw new IllegalStateException("No input given.");
      }

      switch (userInstruction) {
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
          if (indexOfPos == 0) {
            try {
              this.view.renderBoard();
            } catch (IOException e) {
              throw new IllegalStateException("Failed to render board.");
            }
            this.writeMessage("Score: " + this.model.getScore() + System.lineSeparator());
          }
          if (isPosNumeric(userInstruction)) {
            if (indexOfPos < 3) {
              if (model instanceof TriangleSolitaireModel) {
                System.out.println("Fooo");
                positions[indexOfPos] = Integer.valueOf(userInstruction);
              } else {
                positions[indexOfPos] = Integer.valueOf(userInstruction) - 1; // starting at 1
              }
              indexOfPos++;
            } else if (indexOfPos == 3) {
              if (model instanceof TriangleSolitaireModel) {
                System.out.println("Fooo");
                positions[indexOfPos] = Integer.valueOf(userInstruction);
              } else {
                positions[indexOfPos] = Integer.valueOf(userInstruction) - 1; // starting at 1
              }
              try {
                this.model.move(positions[0], positions[1], positions[2], positions[3]);
                gameOver = this.model.isGameOver();
              } catch (IllegalArgumentException e) {
                //  throw new IllegalStateException(e.getMessage());
                writeMessage("Invalid move. Play Again." + System.lineSeparator());
              }
              indexOfPos = 0;
            }
          } else {
            writeMessage("Invalid move. Play Again." + System.lineSeparator());
          }
      }
    }

    if (gameOver) {
      writeMessage("Game over!" + System.lineSeparator());
      try {
        this.view.renderBoard();
      } catch (IOException e) {
        throw new IllegalStateException("Failed to render board.");
      }
      writeMessage("Score: " + this.model.getScore() + System.lineSeparator());
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
  public boolean isPosNumeric(String strNum) {
    if (strNum == null) {
      return false;
    }
    if (!pattern.matcher(strNum).matches()) {
      return false;
    }

    return Integer.valueOf(strNum) >= 0;
  }
}
