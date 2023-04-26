import org.junit.Test;

import java.io.StringReader;

import cs3500.marblesolitaire.controller.MarbleSolitaireControllerImpl;
import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModelState;
import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireModel;
import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireModelState;
import cs3500.marblesolitaire.view.EuropeanSolitaireTextView;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * This represents a class for testing the Marble Solitaire controller.
 */
public class MarbleSolitaireControllerTest {
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidModelInputConstructor() {
    EnglishSolitaireModel model = new EnglishSolitaireModel();
    EnglishSolitaireModelState state = new EnglishSolitaireModelState(model);
    Appendable ap = new StringBuilder();
    MarbleSolitaireTextView view = new MarbleSolitaireTextView(state, ap);
    Readable rd = new StringReader("q");
    MarbleSolitaireControllerImpl controller = new MarbleSolitaireControllerImpl(null, view, rd);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidViewInputConstructor() {
    EnglishSolitaireModel model = new EnglishSolitaireModel();
    EnglishSolitaireModelState state = new EnglishSolitaireModelState(model);
    Appendable ap = new StringBuilder();
    MarbleSolitaireTextView view = new MarbleSolitaireTextView(state, ap);
    Readable rd = new StringReader("q");
    MarbleSolitaireControllerImpl controller = new MarbleSolitaireControllerImpl(model, null, rd);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidReadableInputConstructor() {
    EnglishSolitaireModel model = new EnglishSolitaireModel();
    EnglishSolitaireModelState state = new EnglishSolitaireModelState(model);
    Appendable ap = new StringBuilder();
    MarbleSolitaireTextView view = new MarbleSolitaireTextView(state, ap);
    Readable rd = new StringReader("q");
    MarbleSolitaireControllerImpl controller = new MarbleSolitaireControllerImpl(model, view, null);
  }

  @Test
  public void testQuit() {
    EnglishSolitaireModel model = new EnglishSolitaireModel();
    EnglishSolitaireModelState state = new EnglishSolitaireModelState(model);
    Appendable ap = new StringBuilder();
    MarbleSolitaireTextView view = new MarbleSolitaireTextView(state, ap);
    Readable rd = new StringReader("q");
    MarbleSolitaireControllerImpl controller = new MarbleSolitaireControllerImpl(model, view, rd);

    String actualBoard = "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n";
    String gameOverMessage = "Game quit!\n"
            + "State of game when quit:\n"
            + actualBoard
            + "Score: 32\n";

    controller.playGame();
    assertEquals(gameOverMessage, ap.toString());
  }

  @Test
  public void testQuitsCorrectlyAfterInvalidMovesAndInputs() {
    EnglishSolitaireModel model = new EnglishSolitaireModel();
    EnglishSolitaireModelState state = new EnglishSolitaireModelState(model);
    Appendable ap = new StringBuilder();
    MarbleSolitaireTextView view = new MarbleSolitaireTextView(state, ap);
    String side1 = " 2 5 4 5   3 3 3 3   3 7 3 5 5 7 3 asd 7 3 4 3 6 3 7 3 5 ";
    String side2 = " 3 2 3 4 1 3 3 3 1 5 1 3 4 3 2 3 1 3 3 3 ";
    String side3 = " 6 3 4 3 5 foo 1 5 3 3 1 5 1 5 4 5 2 5 1 5 3 ";
    String side4 = " 5 6 5 4 7 5 5 5  0 0 6 6  7 3 7 5 -12 4 5 6 5 7 5 5 5 ";
    String finish = " 4 3 2 3 2 3 2 5 asdi 2 5 4 5 4 5 6 5  hi 6 5 6 3 6 3 4 3 ";
    String t = " 4 4 2 4 4 2 4 4 5 4 3 4 2 4 4 4 ";
    Readable rd = new StringReader(" 4 6 4 4 " + side1 + side2 + side3 + side4 + finish
            + " q");
    MarbleSolitaireControllerImpl controller = new MarbleSolitaireControllerImpl(model, view, rd);
    controller.playGame();

    String expectedOutput = "State of game when quit:\n"
            + "    _ _ _\n"
            + "    _ _ _\n"
            + "_ _ _ O _ _ _\n"
            + "_ O O O _ _ _\n"
            + "_ _ _ O _ _ _\n"
            + "    _ _ _\n"
            + "    _ _ _\n"
            + "Score: 5\n";

    String[] output = ap.toString().split("Game quit!\n");

    assertEquals(expectedOutput, output[1]);
  }

  @Test
  public void testQuitsCorrectlyAfterInvalidMoves() {
    EnglishSolitaireModel model = new EnglishSolitaireModel();
    EnglishSolitaireModelState state = new EnglishSolitaireModelState(model);
    Appendable ap = new StringBuilder();
    MarbleSolitaireTextView view = new MarbleSolitaireTextView(state, ap);
    String side1 = " 2 5 4 5   3 3 3 3   3 7 3 5 5 7 3 7 3 4 3 6 3 7 3 5 ";
    String side2 = " 3 2 3 4 1 3 3 3 1 5 1 3 4 3 2 3 1 3 3 3 ";
    String side3 = " 6 3 4 3 5 1 5 3 3 1 5 1 5 4 5 2 5 1 5 3 ";
    String side4 = " 5 6 5 4 7 5 5 5  0 0 6 6  7 3 7 5 4 5 6 5 7 5 5 5 ";
    String finish = " 4 3 2 3 2 3 2 5 2 5 4 5 4 5 6 5 6 5 6 3 6 3 4 3 ";
    String t = " 4 4 2 4 4 2 4 4 5 4 3 4 2 4 4 4 ";
    Readable rd = new StringReader(" 4 6 4 4 " + side1 + side2 + side3 + side4 + finish + " q");
    MarbleSolitaireControllerImpl controller = new MarbleSolitaireControllerImpl(model, view, rd);
    controller.playGame();

    String expectedOutput = "State of game when quit:\n"
            + "    _ _ _\n"
            + "    _ _ _\n"
            + "_ _ _ O _ _ _\n"
            + "_ O O O _ _ _\n"
            + "_ _ _ O _ _ _\n"
            + "    _ _ _\n"
            + "    _ _ _\n"
            + "Score: 5\n";

    String[] output = ap.toString().split("Game quit!\n");

    assertEquals(expectedOutput, output[1]);
  }

  @Test
  public void testPlayGameWorksCorrectly() {
    EnglishSolitaireModel model = new EnglishSolitaireModel();
    EnglishSolitaireModelState state = new EnglishSolitaireModelState(model);
    Appendable ap = new StringBuilder();
    MarbleSolitaireTextView view = new MarbleSolitaireTextView(state, ap);
    String side1 = " 2 5 4 5 3 7 3 5 5 7 3 7 3 4 3 6 3 7 3 5 ";
    String side2 = " 3 2 3 4 1 3 3 3 1 5 1 3 4 3 2 3 1 3 3 3 ";
    String side3 = " 6 3 4 3 5 1 5 3 3 1 5 1 5 4 5 2 5 1 5 3 ";
    String side4 = " 5 6 5 4 7 5 5 5 7 3 7 5 4 5 6 5 7 5 5 5 ";
    Readable rd = new StringReader(" 4 6 4 4 " + side1 + side2 + side3 + side4 + " q");
    MarbleSolitaireControllerImpl controller = new MarbleSolitaireControllerImpl(model, view, rd);
    controller.playGame();
    String expectedOutput = "State of game when quit:\n"
            + "    _ _ _\n"
            + "    _ O _\n"
            + "_ _ O O O _ _\n"
            + "_ O O O _ _ _\n"
            + "_ _ O O O _ _\n"
            + "    _ O _\n"
            + "    _ _ _\n"
            + "Score: 11\n";

    String[] output = ap.toString().split("Game quit!\n");

    assertEquals(expectedOutput, output[1]);
  }

  @Test
  public void testPlayGameWithInvalidInputs() {
    EnglishSolitaireModel model = new EnglishSolitaireModel();
    EnglishSolitaireModelState state = new EnglishSolitaireModelState(model);
    Appendable ap = new StringBuilder();
    MarbleSolitaireTextView view = new MarbleSolitaireTextView(state, ap);
    String side1 = " 2 5 4 5 3 7 3 5 5 7 3 asd 7 3 4 3 6 3 7 3 5 ";
    String side2 = " 3 2 3 4 1 3 3 3 1 5 1 3 4 3 2 3 1 3 3 3 ";
    String side3 = " 6 3 4 3 5 foo 1 5 3 3 1 5 1 5 4 5 2 5 1 5 3 ";
    String side4 = " 5 6 5 4 7 5 5 5 7 3 7 5 -12 4 5 6 5 7 5 5 5 ";
    String finish = " 4 3 2 3 2 3 2 5 asdi 2 5 4 5 4 5 6 5  hi 6 5 6 3 6 3 4 3 ";
    String t = " 4 4 2 4 4 2 4 4 5 4 3 4 2 4 4 4 ";
    Readable rd = new StringReader(" 4 6 4 4 " + side1 + side2 + side3 + side4 + finish + t
            + " q");
    MarbleSolitaireControllerImpl controller = new MarbleSolitaireControllerImpl(model, view, rd);
    controller.playGame();

    String expectedOutput = "    _ _ _\n"
            + "    _ _ _\n"
            + "_ _ _ _ _ _ _\n"
            + "_ _ _ O _ _ _\n"
            + "_ _ _ _ _ _ _\n"
            + "    _ _ _\n"
            + "    _ _ _\n"
            + "Score: 1\n";

    String[] output = ap.toString().split("Game over!\n");

    assertEquals(expectedOutput, output[1]);
  }

  @Test
  public void testPlayGameWithInvalidMoves() {
    EnglishSolitaireModel model = new EnglishSolitaireModel();
    EnglishSolitaireModelState state = new EnglishSolitaireModelState(model);
    Appendable ap = new StringBuilder();
    MarbleSolitaireTextView view = new MarbleSolitaireTextView(state, ap);
    String side1 = " 2 5 4 5   3 3 3 3   3 7 3 5 5 7 3 7 3 4 3 6 3 7 3 5 ";
    String side2 = " 3 2 3 4 1 3 3 3 1 5 1 3 4 3 2 3 1 3 3 3 ";
    String side3 = " 6 3 4 3 5 1 5 3 3 1 5 1 5 4 5 2 5 1 5 3 ";
    String side4 = " 5 6 5 4 7 5 5 5 7 3 7 5   0 0 7 7   4 5 6 5 7 5 5 5 ";
    String finish = " 4 3 2 3 2 3 2 5 2 5 4 5 4 5 6 5  6 5 6 3 6 3 4 3 ";
    String t = " 4 4 2 4 4 2 4 4 5 4 3 4 2 4 4 4 ";
    Readable rd = new StringReader(" 4 6 4 4 " + side1 + side2 + side3 + side4 + finish + t
            + " q");
    MarbleSolitaireControllerImpl controller = new MarbleSolitaireControllerImpl(model, view, rd);
    controller.playGame();

    String expectedOutput = "    _ _ _\n"
            + "    _ _ _\n"
            + "_ _ _ _ _ _ _\n"
            + "_ _ _ O _ _ _\n"
            + "_ _ _ _ _ _ _\n"
            + "    _ _ _\n"
            + "    _ _ _\n"
            + "Score: 1\n";

    String[] output = ap.toString().split("Game over!\n");

    assertEquals(expectedOutput, output[1]);
  }

  @Test
  public void testIsGameOverMessageCorrect() {
    EnglishSolitaireModel model = new EnglishSolitaireModel();
    EnglishSolitaireModelState state = new EnglishSolitaireModelState(model);
    Appendable ap = new StringBuilder();
    MarbleSolitaireTextView view = new MarbleSolitaireTextView(state, ap);
    String side1 = " 2 5 4 5 3 7 3 5 5 7 3 7 3 4 3 6 3 7 3 5 ";
    String side2 = " 3 2 3 4 1 3 3 3 1 5 1 3 4 3 2 3 1 3 3 3 ";
    String side3 = " 6 3 4 3 5 1 5 3 3 1 5 1 5 4 5 2 5 1 5 3 ";
    String side4 = " 5 6 5 4 7 5 5 5 7 3 7 5 4 5 6 5 7 5 5 5 ";
    String finish = " 4 3 2 3 2 3 2 5 2 5 4 5 4 5 6 5 6 5 6 3 6 3 4 3 ";
    String t = " 4 4 2 4 4 2 4 4 5 4 3 4 2 4 4 4 ";
    Readable rd = new StringReader(" 4 6 4 4 " + side1 + side2 + side3 + side4 + finish + t
            + " q");
    MarbleSolitaireControllerImpl controller = new MarbleSolitaireControllerImpl(model, view, rd);
    controller.playGame();

    String expectedOutput = "    _ _ _\n"
            + "    _ _ _\n"
            + "_ _ _ _ _ _ _\n"
            + "_ _ _ O _ _ _\n"
            + "_ _ _ _ _ _ _\n"
            + "    _ _ _\n"
            + "    _ _ _\n"
            + "Score: 1\n";

    String[] output = ap.toString().split("Game over!\n");
    assertEquals(expectedOutput, output[1]);
  }

  @Test
  public void testNoMoreInputsError() {
    EnglishSolitaireModel model = new EnglishSolitaireModel();
    EnglishSolitaireModelState state = new EnglishSolitaireModelState(model);
    Appendable ap = new StringBuilder();
    MarbleSolitaireTextView view = new MarbleSolitaireTextView(state, ap);
    Readable rd = new StringReader(" 6 4 4 4  2 4 4 4 ");
    MarbleSolitaireControllerImpl controller = new MarbleSolitaireControllerImpl(model, view, rd);
    try {
      controller.playGame();
    } catch (IllegalStateException e) {
      assertEquals("No input given.", e.getMessage());
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidModelInputConstructorEuropean() {
    EuropeanSolitaireModel model = new EuropeanSolitaireModel();
    EuropeanSolitaireModelState state = new EuropeanSolitaireModelState(model);
    Appendable ap = new StringBuilder();
    EuropeanSolitaireTextView view = new EuropeanSolitaireTextView(state, ap);
    Readable rd = new StringReader("q");
    MarbleSolitaireControllerImpl controller = new MarbleSolitaireControllerImpl(null, view, rd);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidViewInputConstructorEuropean() {
    EuropeanSolitaireModel model = new EuropeanSolitaireModel();
    EuropeanSolitaireModelState state = new EuropeanSolitaireModelState(model);
    Appendable ap = new StringBuilder();
    EuropeanSolitaireTextView view = new EuropeanSolitaireTextView(state, ap);
    Readable rd = new StringReader("q");
    MarbleSolitaireControllerImpl controller = new MarbleSolitaireControllerImpl(model, null, rd);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidReadableInputConstructorEuropean() {
    EuropeanSolitaireModel model = new EuropeanSolitaireModel();
    EuropeanSolitaireModelState state = new EuropeanSolitaireModelState(model);
    Appendable ap = new StringBuilder();
    EuropeanSolitaireTextView view = new EuropeanSolitaireTextView(state, ap);
    Readable rd = new StringReader("q");
    MarbleSolitaireControllerImpl controller = new MarbleSolitaireControllerImpl(model, view, null);
  }

  @Test
  public void testQuitEuropean() {
    EuropeanSolitaireModel model = new EuropeanSolitaireModel();
    EuropeanSolitaireModelState state = new EuropeanSolitaireModelState(model);
    Appendable ap = new StringBuilder();
    EuropeanSolitaireTextView view = new EuropeanSolitaireTextView(state, ap);
    Readable rd = new StringReader("q");
    MarbleSolitaireControllerImpl controller = new MarbleSolitaireControllerImpl(model, view, rd);

    String actualBoard = "    O O O\n"
            + "  O O O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "  O O O O O\n"
            + "    O O O\n";
    String gameOverMessage = "Game quit!\n"
            + "State of game when quit:\n"
            + actualBoard
            + "Score: 36\n";

    controller.playGame();
    assertEquals(gameOverMessage, ap.toString());
  }

  @Test
  public void testOneMoveEuropean() {
    EuropeanSolitaireModel model = new EuropeanSolitaireModel();
    EuropeanSolitaireModelState state = new EuropeanSolitaireModelState(model);
    Appendable ap = new StringBuilder();
    EuropeanSolitaireTextView view = new EuropeanSolitaireTextView(state, ap);
    Readable rd = new StringReader(" 6 4 4 4 q");
    MarbleSolitaireControllerImpl controller = new MarbleSolitaireControllerImpl(model, view, rd);

    String actualBoard = "    O O O\n"
            + "  O O O O O\n"
            + "O O O O O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "  O O _ O O\n"
            + "    O O O\n";
    String gameOverMessage = "State of game when quit:\n"
            + actualBoard
            + "Score: 35\n";

    controller.playGame();
    String[] output = ap.toString().split("Game quit!\n");
    assertEquals(gameOverMessage, output[1]);
  }

  @Test
  public void testErrorNoMarbleInFromSpotEuropean() {
    EuropeanSolitaireModel model = new EuropeanSolitaireModel();
    EuropeanSolitaireModelState state = new EuropeanSolitaireModelState(model);
    Appendable ap = new StringBuilder();
    EuropeanSolitaireTextView view = new EuropeanSolitaireTextView(state, ap);
    Readable rd = new StringReader(" 6 4 4 4  5 4 7 4 q");
    MarbleSolitaireControllerImpl controller = new MarbleSolitaireControllerImpl(model, view, rd);

    String actualBoard = "    O O O\n"
            + "  O O O O O\n"
            + "O O O O O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "  O O _ O O\n"
            + "    O O O\n";
    String gameOverMessage = "Invalid move. Play Again.\nGame quit!\nState of game when quit:\n"
            + actualBoard;

    controller.playGame();
    String[] output = ap.toString().split("Score: 35\n");
    assertEquals(gameOverMessage, output[1]);
  }

  @Test
  public void testErrorNoEmptySpotInToPositionEuropean() {
    EuropeanSolitaireModel model = new EuropeanSolitaireModel();
    EuropeanSolitaireModelState state = new EuropeanSolitaireModelState(model);
    Appendable ap = new StringBuilder();
    EuropeanSolitaireTextView view = new EuropeanSolitaireTextView(state, ap);
    Readable rd = new StringReader(" 6 4 4 4  2 4 4 4 q");
    MarbleSolitaireControllerImpl controller = new MarbleSolitaireControllerImpl(model, view, rd);

    String actualBoard = "    O O O\n"
            + "  O O O O O\n"
            + "O O O O O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "  O O _ O O\n"
            + "    O O O\n";
    String gameOverMessage = "Invalid move. Play Again.\nGame quit!\nState of game when quit:\n"
            + actualBoard;

    controller.playGame();
    String[] output = ap.toString().split("Score: 35\n");
    assertEquals(gameOverMessage, output[1]);
  }

  @Test
  public void testNoMoreInputsErrorEuropean() {
    EuropeanSolitaireModel model = new EuropeanSolitaireModel();
    EuropeanSolitaireModelState state = new EuropeanSolitaireModelState(model);
    Appendable ap = new StringBuilder();
    EuropeanSolitaireTextView view = new EuropeanSolitaireTextView(state, ap);
    Readable rd = new StringReader(" 6 4 4 4  2 4 4 4 ");
    MarbleSolitaireControllerImpl controller = new MarbleSolitaireControllerImpl(model, view, rd);
    try {
      controller.playGame();
    } catch (IllegalStateException e) {
      assertEquals("No input given.", e.getMessage());
    }
  }

  @Test
  public void testIsPosNumeric() {
    EnglishSolitaireModel model = new EnglishSolitaireModel();
    EnglishSolitaireModelState state = new EnglishSolitaireModelState(model);
    Appendable ap = new StringBuilder();
    MarbleSolitaireTextView view = new MarbleSolitaireTextView(state, ap);
    Readable rd = new StringReader("q");
    MarbleSolitaireControllerImpl controller = new MarbleSolitaireControllerImpl(model, view, rd);
    assertTrue(controller.isPosNumeric("1"));
    assertTrue(controller.isPosNumeric("5"));
    assertFalse(controller.isPosNumeric("-1"));
    assertFalse(controller.isPosNumeric("ijad"));
    assertFalse(controller.isPosNumeric("O")); // <- not a zero
    assertTrue(controller.isPosNumeric("12"));
  }
}
