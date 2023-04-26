package cs3500.marblesolitaire.model.hw04;

/**
 * A class representing the operations offered by a european marble solitaire model.
 * One object of this model represents one instance of a european marble solitaire game.
 */
public class EuropeanSolitaireModel extends AbstractSolitaireModel {
  /**
   * Creates a default European Solitaire Model, with thickness 3
   * and the empty slot in the center of the board.
   */
  public EuropeanSolitaireModel() {
    super();
  }

  /**
   * Creates a European Solitaire Model, with a given side length
   * and the empty slot in the center of the board.
   */
  public EuropeanSolitaireModel(int sideLength) {
    super(sideLength);
  }

  /**
   * Creates a European Solitaire Model, with a side length of 3
   * and an empty slot in the given location (row, col).
   */
  public EuropeanSolitaireModel(int mtRow, int mtCol) {
    super(mtRow, mtCol);
  }

  /**
   * Creates a European Solitaire Model, with a given side length
   * and an empty slot in the given location (row, col).
   */
  public EuropeanSolitaireModel(int sideLength, int mtRow, int mtCol) {
    super(sideLength, mtRow, mtCol);
  }

  /**
   * Creates a European Solitaire Model (with default values 3 thickness, center empty),
   * with a provided arrangement of the board.
   *
   * @param board array of slot types representing board.
   */
  public EuropeanSolitaireModel(SlotState[][] board) {
    super(board);
  }

  @Override
  protected boolean checkValidSlot(int row, int col) {
    int edgeLength = this.sideLength - 1;
    int totalLength = this.sideLength + (edgeLength * 2);
    //System.out.println(row + " " + col);
    if (row > totalLength - 1 || col > totalLength - 1 || row < 0 || col < 0) {
      return false;
    } else if (row < edgeLength) { // top portion
      if (col >= edgeLength && col <= totalLength - 1 - edgeLength) { // center
        return true;
      } else if (col < edgeLength) { // top left

        return (row + col >= edgeLength);
      } else if (col > totalLength - 1 - edgeLength) { // top right
        return (col - row <= totalLength - edgeLength - 1);
      }
    } else if (row > totalLength - 1 - edgeLength) { // bottom portion
      if (col >= edgeLength && col <= totalLength - 1 - edgeLength) { // center
        return true;
      } else if (col < edgeLength) { // bottom left
        return (row - col <= edgeLength * 2);
      } else if (col > totalLength - 1 - edgeLength) { // bottom right
        return (row + col <= (totalLength - 1 - edgeLength) + (totalLength - 1));
      }
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
