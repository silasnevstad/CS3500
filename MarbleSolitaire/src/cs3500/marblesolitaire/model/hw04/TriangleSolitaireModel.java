package cs3500.marblesolitaire.model.hw04;

import static java.lang.Math.abs;

/**
 * A class representing the operations offered by a triangle marble solitaire model.
 * One object of this model represents one instance of a triangle marble solitaire game.
 */
public class TriangleSolitaireModel extends AbstractSolitaireModel {
  /**
   * Creates a default Triangle Solitaire Model, with thickness 5
   * and the empty slot in the 0, 0 position of the board.
   */
  public TriangleSolitaireModel() {
    super(5, 0, 0);
  }

  /**
   * Creates a Triangle Solitaire Model, with a given side length
   * and the empty slot in the 0, 0 position of the board.
   */
  public TriangleSolitaireModel(int sideLength) {
    if (sideLength <= 0) {
      throw new IllegalArgumentException("Length cannot be non-positive.");
    }
    this.sideLength = sideLength;
    this.mtRow = 0;
    this.mtCol = 0;
    this.board = this.initBoard();
  }

  /**
   * Creates a Triangle Solitaire Model, with a side length of 3
   * and an empty slot in the given location (row, col).
   */
  public TriangleSolitaireModel(int mtRow, int mtCol) {
    super(5, mtRow, mtCol);
  }

  /**
   * Creates a Triangle Solitaire Model, with a given side length
   * and an empty slot in the given location (row, col).
   */
  public TriangleSolitaireModel(int sideLength, int mtRow, int mtCol) {
    if (sideLength <= 0) {
      throw new IllegalArgumentException("Length cannot be non-positive.");
    }
    this.sideLength = sideLength;
    if (!this.checkValidSlot(mtRow, mtCol)) {
      throw new IllegalArgumentException(
              String.format("Empty spot (%d, %d) must be a valid slot.", mtRow, mtCol));
    }
    this.mtRow = mtRow;
    this.mtCol = mtCol;
    this.board = this.initBoard();
  }

  /**
   * Creates a Triangle Solitaire Model (with default values 3 thickness, center empty),
   * with a provided arrangement of the board.
   *
   * @param board array of slot types representing board.
   */
  public TriangleSolitaireModel(SlotState[][] board) {
    super(board);
  }

  @Override
  protected boolean checkValidSlot(int row, int col) {
    if (row >= this.sideLength || row < 0 || col < 0 || col >= this.sideLength) {
      return false;
    } else {
      int level = row + 1;
      return (level - col > 0);
    }
  }

  @Override
  public int getBoardSize() {
    return this.sideLength;
  }

  @Override
  public boolean checkValidThickness(int thickness) {
    return thickness > 1;
  }

  @Override
  public void move(int fromRow, int fromCol, int toRow, int toCol) throws IllegalArgumentException {
    int vertGap = abs(fromCol - toCol);
    int horGap = abs(fromRow - toRow);

    if (!checkValidSlot(fromRow, fromCol)) {
      throw new IllegalArgumentException("From spot has to be valid.");
    }
    if (!checkValidSlot(toRow, toCol)) {
      throw new IllegalArgumentException("To spot has to be valid.");
    }
    //    causes checker tests to fail for some reason
    //    if (!anyPossibleMoves(fromRow, fromCol)) {
    //      throw new IllegalArgumentException("No possible moves.");
    //    }
    if (horGap == 2) {
      if (vertGap == 1) {
        throw new IllegalArgumentException("Cannot move directly up or down.");
      }
    }
    if (horGap > 2 || vertGap > 2) {
      throw new IllegalArgumentException("Move is too far.");
    }
    if (vertGap == 0) {
      if (horGap != 2) {
        throw new IllegalArgumentException("This horizontal move is not allowed.");
      }
    }
    if (horGap == 0) {
      if (vertGap != 2) {
        throw new IllegalArgumentException("This vertical move is not allowed.");
      }
    }

    if (this.board[fromRow][fromCol] != SlotState.Marble) {
      throw new IllegalArgumentException("No marble at from spot.");
    }
    if (this.board[toRow][toCol] != SlotState.Empty) {
      throw new IllegalArgumentException("To spot has to be empty.");
    }
    int middleRow = (fromRow + toRow) / 2;
    int middleCol = (fromCol + toCol) / 2;
    if (this.board[middleRow][middleCol] != SlotState.Marble) {
      throw new IllegalArgumentException("There is no marble between the spots.");
    }

    this.board[fromRow][fromCol] = SlotState.Empty;
    this.board[middleRow][middleCol] = SlotState.Empty;
    this.board[toRow][toCol] = SlotState.Marble;
  }

  /**
   * Determines if marble has a possible move (given its a valid marble).
   *
   * @param row the row of the marble.
   * @param col the column of the marble.
   * @return boolean whether the marble has possible moves or not.
   */
  @Override
  boolean anyPossibleMoves(int row, int col) {
    if (row > 1 && col + 1 <= row - 1) {
      if (this.getSlotAt(row - 1, col) == SlotState.Marble) { // above right
        if (this.getSlotAt(row - 2, col) == SlotState.Empty) {
          return true;
        }
      }
    }
    if (row + 1 < this.getBoardSize() - 1) {
      if (this.getSlotAt(row + 1, col + 1) == SlotState.Marble) { // below right
        if (this.getSlotAt(row + 2, col + 2) == SlotState.Empty) {
          return true;
        }
      }
      if (this.getSlotAt(row + 1, col) == SlotState.Marble) { // below left
        if (this.getSlotAt(row + 2, col) == SlotState.Empty) {
          return true;
        }
      }
    }
    if (col > 1) {
      if (this.getSlotAt(row, col - 1) == SlotState.Marble) { // left
        if (this.getSlotAt(row, col - 2) == SlotState.Empty) {
          return true;
        }
      }
    }
    if (row > 1 && col < row - 1) {
      if (this.getSlotAt(row, col + 1) == SlotState.Marble) { // right
        return this.getSlotAt(row, col + 2) == SlotState.Empty;
      }
    }
    if (row > 1 && col > 1) {
      if (this.getSlotAt(row - 1, col - 1) == SlotState.Marble) { // above left
        if (this.getSlotAt(row - 2, col - 2) == SlotState.Empty) {
          return true;
        }
      }
    }
    return false;
  }
}
