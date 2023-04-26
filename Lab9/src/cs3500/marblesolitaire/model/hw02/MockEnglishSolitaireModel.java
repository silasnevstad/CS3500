package cs3500.marblesolitaire.model.hw02;

/**
 * A class representing a mock english solitaire model,
 * its purpose it for testing the inputs received.
 */
public class MockEnglishSolitaireModel extends EnglishSolitaireModel {
  StringBuilder log;

  public MockEnglishSolitaireModel(StringBuilder log) {
    this.log = log;
  }

  @Override
  public void move(int fromRow, int fromCol, int toRow, int toCol) throws IllegalArgumentException {
    log.append(String.format("from row = %d, from col = %d, to row = %d, to col = %d\n",
            fromRow + 1, fromCol + 1, toRow + 1, toCol + 1)); // user input starts at 1, board 0
  }
}
