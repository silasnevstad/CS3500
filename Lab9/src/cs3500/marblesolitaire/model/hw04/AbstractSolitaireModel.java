package cs3500.marblesolitaire.model.hw04;

import java.text.MessageFormat;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;

import static java.lang.Math.abs;

/**
 * This interface represents the operations offered by an abstract solitaire
 * model.
 */
public abstract class AbstractSolitaireModel implements MarbleSolitaireModel {
  protected int sideLength;
  int mtRow;
  int mtCol;
  protected SlotState[][] board;

  /**
   * Creates a default Abstract Solitaire Model, with thickness 3
   * and the empty slot in the center of the board.
   */
  public AbstractSolitaireModel() {
    this.sideLength = 3;
    this.mtRow = 3;
    this.mtCol = 3;
    this.board = this.initBoard();
  }

  /**
   * Creates an Abstract Solitaire Model, with a given side length
   * and the empty slot in the center of the board.
   */
  public AbstractSolitaireModel(int sideLength) {
    if (checkValidThickness(sideLength)) {
      this.sideLength = sideLength;
    } else {
      throw new IllegalArgumentException("Arm thickness must be valid.");
    }
    this.mtRow = this.getCenterSlot();
    this.mtCol = this.mtRow;
    this.board = this.initBoard();
  }


  /**
   * Creates an Abstract Solitaire Model, with a side length of 3
   * and an empty slot in the given location (row, col).
   */
  public AbstractSolitaireModel(int mtRow, int mtCol) {
    this.sideLength = 3;
    if (checkValidSlot(mtRow, mtCol)) {
      this.mtRow = mtRow;
      this.mtCol = mtCol;
    } else {
      throw new IllegalArgumentException(MessageFormat.format(
              "Invalid empty cell position ({mtRow},{mtCol})", mtRow, mtCol));
    }
    this.board = this.initBoard();
  }

  /**
   * Creates an Abstract Solitaire Model, with a given side length
   * and an empty slot in the given location (row, col).
   */
  public AbstractSolitaireModel(int sideLength, int mtRow, int mtCol) {
    if (checkValidThickness(sideLength)) {
      this.sideLength = sideLength;
    } else {
      throw new IllegalArgumentException("Arm thickness must be a positive odd integer.");
    }
    if (checkValidSlot(mtRow, mtCol)) {
      this.mtRow = mtRow;
      this.mtCol = mtCol;
    } else {
      throw new IllegalArgumentException(String.format(
              "Invalid empty cell position (%d, %d).", mtRow, mtCol));
    }
    this.board = this.initBoard();
  }

  /**
   * Creates an Abstract Solitaire Model (with default values 3 thickness, center empty),
   * with a provided arrangement of the board.
   *
   * @param board array of slot types representing board.
   */
  public AbstractSolitaireModel(SlotState[][] board) {
    if (board == null) {
      throw new IllegalArgumentException("Board cannot be null.");
    }
    this.sideLength = 3;
    this.mtRow = 3;
    this.mtCol = 3;
    this.board = board;
  }

  @Override
  public void move(int fromRow, int fromCol, int toRow, int toCol) throws IllegalArgumentException {
    int vertGap = abs(fromCol - toCol);
    int horGap = abs(fromRow - toRow);
    int middleRow = (fromRow + toRow) / 2;
    int middleCol = (fromCol + toCol) / 2;

    if (fromRow == toRow && fromCol == toCol) {
      throw new IllegalArgumentException("Choose a spot to move to.");
    } else if (vertGap > 0 && horGap > 0) {
      throw new IllegalArgumentException("Cannot make diagonal moves.");
    } else if ((vertGap == 0 && horGap > 2) || (horGap == 0 && vertGap > 2)) {
      throw new IllegalArgumentException("Move is too far.");
    } else if ((vertGap == 0 && horGap <= 1) || (horGap == 0 && vertGap <= 1)) {
      throw new IllegalArgumentException("Move is too short.");
    } else if (!checkValidSlot(fromRow, fromCol)) {
      throw new IllegalArgumentException("From spot is invalid.");
    } else if (!checkValidSlot(toRow, toCol)) {
      throw new IllegalArgumentException("To spot is invalid.");
    } else if (this.board[fromRow][fromCol] != SlotState.Marble) {
      throw new IllegalArgumentException("No marble at from spot.");
    } else if (this.board[toRow][toCol] != SlotState.Empty) {
      throw new IllegalArgumentException("To spot has to be empty.");
    } else if (this.board[middleRow][middleCol] != SlotState.Marble) {
      throw new IllegalArgumentException("There is no marble between the spots.");
    } else {
      this.board[fromRow][fromCol] = SlotState.Empty;
      this.board[middleRow][middleCol] = SlotState.Empty;
      this.board[toRow][toCol] = SlotState.Marble;
    }
  }

  @Override
  public int getBoardSize() {
    return this.sideLength + ((this.sideLength - 1) * 2);
  }

  @Override
  public SlotState getSlotAt(int row, int col) throws IllegalArgumentException {
    return this.board[row][col];
  }

  @Override
  public boolean isGameOver() {
    if (this.getScore() == 1) {
      return true;
    }
    for (int x = 0; x < this.getBoardSize(); x++) {
      for (int y = 0; y < this.getBoardSize(); y++) {
        //        if (this.checkValidSlot(x, y)) {
        if (this.getSlotAt(x, y) == SlotState.Marble) {
          if (this.anyPossibleMoves(x, y)) {
            return false;
          }
        }
        // }
      }
    }
    return true;
  }

  /**
   * Initializes the board for a new game.
   *
   * @return returns an array of type SlotState.
   */
  protected SlotState[][] initBoard() {
    SlotState[][] newBoard = new SlotState[this.getBoardSize()][this.getBoardSize()];
    for (int x = 0; x < this.getBoardSize(); x++) {
      for (int y = 0; y < this.getBoardSize(); y++) {
        if (!this.checkValidSlot(x, y)) {
          newBoard[x][y] = SlotState.Invalid;
        } else if (!(x == this.mtRow && y == this.mtCol)) {
          newBoard[x][y] = SlotState.Marble;
        } else {
          newBoard[x][y] = SlotState.Empty;
        }
      }
    }
    return newBoard;
  }

  /**
   * Checks whether thickness provided is valid for the board's dimensions.
   *
   * @param thickness the arm thickness of the board.
   * @return returns whether the thickness is valid (positive odd integer returns true, else false)
   */
  public boolean checkValidThickness(int thickness) {
    if (thickness <= 1) {
      return false;
    } else {
      return thickness % 2 != 0;
    }
  }

  /**
   * Finds the center slot of the board.
   *
   * @return returns the center slot of the board.
   */
  public int getCenterSlot() {
    int x = this.sideLength - 1;
    int y = (x * 2) + this.sideLength;
    int z = y - 1;
    return z / 2; // will always be whole, no worries with rounding
  }

  /**
   * Determines if marble has a possible move (given its a valid marble).
   *
   * @param row the row of the marble.
   * @param col the column of the marble.
   * @return boolean whether the marble has possible moves or not.
   */
  boolean anyPossibleMoves(int row, int col) {
    if (row - 1 > 0) {
      if (this.getSlotAt(row - 1, col) == SlotState.Marble) { // above
        if (this.getSlotAt(row - 2, col) == SlotState.Empty) {
          return true;
        }
      }
    }
    if (row + 1 < this.getBoardSize() - 1) {
      if (this.getSlotAt(row + 1, col) == SlotState.Marble) { // below
        if (this.getSlotAt(row + 2, col) == SlotState.Empty) {
          return true;
        }
      }
    }
    if (col - 1 > 0) {
      if (this.getSlotAt(row, col - 1) == SlotState.Marble) { // left
        if (this.getSlotAt(row, col - 2) == SlotState.Empty) {
          return true;
        }
      }
    }
    if (col + 1 < this.getBoardSize() - 1) {
      if (this.getSlotAt(row, col + 1) == SlotState.Marble) { // right
        return this.getSlotAt(row, col + 2) == SlotState.Empty;
      }
    }
    return false;
  }

  /**
   * Return the number of marbles currently on the board.
   *
   * @return the number of marbles currently on the board
   */
  public int getScore() {
    int score = 0;
    for (int x = 0; x < this.getBoardSize(); x++) {
      for (int y = 0; y < this.getBoardSize(); y++) {
        if (this.getSlotAt(x, y) == SlotState.Marble) {
          score += 1;
        }
      }
    }
    return score;
  }

  /**
   * Checks if the given slot works with the current board.
   *
   * @param row row of the given slot.
   * @param col column of the given slot.
   * @return whether the provided slot is valid with the current board.
   */
  protected abstract boolean checkValidSlot(int row, int col);
}
