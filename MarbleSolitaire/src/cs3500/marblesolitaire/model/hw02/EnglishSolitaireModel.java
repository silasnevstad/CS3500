package cs3500.marblesolitaire.model.hw02;

import cs3500.marblesolitaire.model.hw04.AbstractSolitaireModel;

/**
 * A class representing the operations offered by an english marble solitaire model.
 * One object of this model represents one instance of an english marble solitaire game.
 */
public class EnglishSolitaireModel extends AbstractSolitaireModel {
  /**
   * Creates a default English Solitaire Model, with thickness 3
   * and the empty spot in the center.
   */
  public EnglishSolitaireModel() {
    super();
    this.board = this.initBoard();
  }

  /**
   * Creates an English Solitaire Model (with default values 3 thickness, center empty),
   * with a provided arrangement of the board.
   *
   * @param board array of slot types representing board.
   */
  public EnglishSolitaireModel(SlotState[][] board) {
    super(board);
  }

  /**
   * Creates an English Solitaire Model with a default empty spot (center),
   * given a thickness for the board.
   *
   * @param thickness the thickness of the board (size).
   */
  public EnglishSolitaireModel(int thickness) {
    super(thickness);
  }

  /**
   * Creates an English Solitaire Model with default thickness 3, given
   * a placement for the empty cell.
   *
   * @param sRow the row for the empty cell.
   * @param sCol the column for the empty cell.
   */
  public EnglishSolitaireModel(int sRow, int sCol) {
    super(sRow, sCol);
  }

  /**
   * Creates an English Solitaire Model with given thickness,
   * and placement of empty cell.
   *
   * @param thickness thickness of the board.
   * @param sRow      the row of the empty cell.
   * @param sCol      the column of the empty cell.
   */
  public EnglishSolitaireModel(int thickness, int sRow, int sCol) {
    super(thickness, sRow, sCol);
  }

  @Override
  protected boolean checkValidSlot(int row, int col) {
    int edgeSize = this.sideLength - 1;
    int totalSize = this.sideLength + (edgeSize * 2);
    if (row > totalSize - 1 || col > totalSize - 1 || row < 0 || col < 0) {
      return false;
    } else if (row < edgeSize) {
      return !(col < edgeSize || col > totalSize - edgeSize - 1);
    } else if (row > totalSize - edgeSize - 1) {
      return !(col < edgeSize || col > totalSize - edgeSize - 1);
    }
    return true;
  }

  @Override
  public boolean checkValidThickness(int thickness) {
    if (thickness <= 1) {
      return false;
    } else {
      return thickness % 2 != 0;
    }
  }
}
