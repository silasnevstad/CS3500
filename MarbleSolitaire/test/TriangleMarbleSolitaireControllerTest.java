import org.junit.Test;

import java.io.StringReader;

import cs3500.marblesolitaire.controller.MarbleSolitaireControllerImpl;
import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModel;
import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModelState;
import cs3500.marblesolitaire.view.AbstractSolitaireTextView;
import cs3500.marblesolitaire.view.TriangleSolitaireTextView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * This represents a class for testing the Marble Solitaire controller.
 */
public class TriangleMarbleSolitaireControllerTest {
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidModelInputConstructor() {
    TriangleSolitaireModel model = new TriangleSolitaireModel();
    TriangleSolitaireModelState state = new TriangleSolitaireModelState(model);
    Appendable ap = new StringBuilder();
    AbstractSolitaireTextView view = new TriangleSolitaireTextView(state, ap);
    Readable rd = new StringReader("q");
    MarbleSolitaireControllerImpl controller = new MarbleSolitaireControllerImpl(null, view, rd);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidViewInputConstructor() {
    TriangleSolitaireModel model = new TriangleSolitaireModel();
    TriangleSolitaireModelState state = new TriangleSolitaireModelState(model);
    Appendable ap = new StringBuilder();
    TriangleSolitaireTextView view = new TriangleSolitaireTextView(state, ap);
    Readable rd = new StringReader("q");
    MarbleSolitaireControllerImpl controller = new MarbleSolitaireControllerImpl(model, null, rd);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidReadableInputConstructor() {
    TriangleSolitaireModel model = new TriangleSolitaireModel();
    TriangleSolitaireModelState state = new TriangleSolitaireModelState(model);
    Appendable ap = new StringBuilder();
    TriangleSolitaireTextView view = new TriangleSolitaireTextView(state, ap);
    Readable rd = new StringReader("q");
    MarbleSolitaireControllerImpl controller = new MarbleSolitaireControllerImpl(model, view, null);
  }

  @Test
  public void testQuit() {
    TriangleSolitaireModel model = new TriangleSolitaireModel();
    TriangleSolitaireModelState state = new TriangleSolitaireModelState(model);
    Appendable ap = new StringBuilder();
    TriangleSolitaireTextView view = new TriangleSolitaireTextView(state, ap);
    Readable rd = new StringReader("q");
    MarbleSolitaireControllerImpl controller = new MarbleSolitaireControllerImpl(model, view, rd);

    String actualBoard = "    _\n"
            + "   O O\n"
            + "  O O O\n"
            + " O O O O\n"
            + "O O O O O\n";
    String gameOverMessage = "Game quit!\n"
            + "State of game when quit:\n"
            + actualBoard
            + "Score: 14\n";

    controller.playGame();
    assertEquals(gameOverMessage, ap.toString());
  }

  @Test
  public void testOneMove() {
    TriangleSolitaireModel model = new TriangleSolitaireModel();
    TriangleSolitaireModelState state = new TriangleSolitaireModelState(model);
    Appendable ap = new StringBuilder();
    TriangleSolitaireTextView view = new TriangleSolitaireTextView(state, ap);
    String move1 = "2 0 0 0";
    Readable rd = new StringReader(move1 + " q");
    MarbleSolitaireControllerImpl controller = new MarbleSolitaireControllerImpl(model, view, rd);
    controller.playGame();

    String expectedOutput = "State of game when quit:\n"
            + "    O\n"
            + "   _ O\n"
            + "  _ O O\n"
            + " O O O O\n"
            + "O O O O O\n"
            + "Score: 13\n";

    String[] output = ap.toString().split("Game quit!\n");

    assertEquals(expectedOutput, output[1]);
  }

  @Test
  public void testGameFinish() {
    TriangleSolitaireModel model = new TriangleSolitaireModel();
    TriangleSolitaireModelState state = new TriangleSolitaireModelState(model);
    Appendable ap = new StringBuilder();
    TriangleSolitaireTextView view = new TriangleSolitaireTextView(state, ap);
    String move1 = "2 0 0 0  2 2 2 0  0 0 2 2  3 0 1 0  3 2 3 0  4 0 2 0  1 0 3 0  4 2 4 0  4 0 2 0"
            + " 4 4 4 2  2 2 4 4 ";
    Readable rd = new StringReader(move1 + " q");
    MarbleSolitaireControllerImpl controller = new MarbleSolitaireControllerImpl(model, view, rd);
    controller.playGame();

    String expectedOutput = "    _\n"
            + "   _ _\n"
            + "  O _ _\n"
            + " _ _ _ _\n"
            + "_ _ O _ O\n"
            + "Score: 3\n";

    String[] output = ap.toString().split("Game over!\n");

    for (int i = 0; i < output.length; i++) {
      System.out.println(output[i]);
    }

    assertEquals(expectedOutput, output[1]);
  }

  @Test
  public void testErrorTooFarMove() {
    TriangleSolitaireModel model = new TriangleSolitaireModel();
    TriangleSolitaireModelState state = new TriangleSolitaireModelState(model);
    Appendable ap = new StringBuilder();
    TriangleSolitaireTextView view = new TriangleSolitaireTextView(state, ap);
    String move1 = "2 0 0 0  2 2 2 0  0 0 2 2  4 0 4 3";
    Readable rd = new StringReader(move1 + " q");
    MarbleSolitaireControllerImpl controller = new MarbleSolitaireControllerImpl(model, view, rd);
    controller.playGame();

    String expectedOutput = "Invalid move. Play Again.\nGame quit!\nState of game when quit:\n"
            + "    _\n"
            + "   _ _\n"
            + "  O _ O\n"
            + " O O O O\n"
            + "O O O O O\n";

    String[] output = ap.toString().split("Score: 11\n");

    for (int i = 0; i < output.length; i++) {
      System.out.println(output[i]);
    }

    assertEquals(expectedOutput, output[1]);
  }

  @Test
  public void testErrorNotValidMove() {
    TriangleSolitaireModel model = new TriangleSolitaireModel();
    TriangleSolitaireModelState state = new TriangleSolitaireModelState(model);
    Appendable ap = new StringBuilder();
    TriangleSolitaireTextView view = new TriangleSolitaireTextView(state, ap);
    String move1 = "2 0 0 0  2 2 2 0  0 0 2 2  4 0 4 5";
    Readable rd = new StringReader(move1 + " q");
    MarbleSolitaireControllerImpl controller = new MarbleSolitaireControllerImpl(model, view, rd);
    controller.playGame();

    String expectedOutput = "Invalid move. Play Again.\nGame quit!\nState of game when quit:\n"
            + "    _\n"
            + "   _ _\n"
            + "  O _ O\n"
            + " O O O O\n"
            + "O O O O O\n";

    String[] output = ap.toString().split("Score: 11\n");

    for (int i = 0; i < output.length; i++) {
      System.out.println(output[i]);
    }

    assertEquals(expectedOutput, output[1]);
  }

  @Test
  public void testErrorNoPossibleMoves() {
    TriangleSolitaireModel model = new TriangleSolitaireModel();
    TriangleSolitaireModelState state = new TriangleSolitaireModelState(model);
    Appendable ap = new StringBuilder();
    TriangleSolitaireTextView view = new TriangleSolitaireTextView(state, ap);
    String move1 = "2 0 0 0  2 2 2 0  0 0 2 2  0 0 2 0";
    Readable rd = new StringReader(move1 + " q");
    MarbleSolitaireControllerImpl controller = new MarbleSolitaireControllerImpl(model, view, rd);
    controller.playGame();

    String expectedOutput = "Invalid move. Play Again.\nGame quit!\nState of game when quit:\n"
            + "    _\n"
            + "   _ _\n"
            + "  O _ O\n"
            + " O O O O\n"
            + "O O O O O\n";

    String[] output = ap.toString().split("Score: 11\n");

    for (int i = 0; i < output.length; i++) {
      System.out.println(output[i]);
    }

    assertEquals(expectedOutput, output[1]);
  }

  @Test
  public void testErrorMarbleInToSpotMoves() {
    TriangleSolitaireModel model = new TriangleSolitaireModel();
    TriangleSolitaireModelState state = new TriangleSolitaireModelState(model);
    Appendable ap = new StringBuilder();
    TriangleSolitaireTextView view = new TriangleSolitaireTextView(state, ap);
    String move1 = "2 0 0 0  2 2 2 0  0 0 2 2  4 0 2 0";
    Readable rd = new StringReader(move1 + " q");
    MarbleSolitaireControllerImpl controller = new MarbleSolitaireControllerImpl(model, view, rd);
    controller.playGame();

    String expectedOutput = "Invalid move. Play Again.\nGame quit!\nState of game when quit:\n"
            + "    _\n"
            + "   _ _\n"
            + "  O _ O\n"
            + " O O O O\n"
            + "O O O O O\n";

    String[] output = ap.toString().split("Score: 11\n");

    for (int i = 0; i < output.length; i++) {
      System.out.println(output[i]);
    }

    assertEquals(expectedOutput, output[1]);
  }

  @Test
  public void testGameOverMidGame() {
    TriangleSolitaireModel model = new TriangleSolitaireModel();
    TriangleSolitaireModelState state = new TriangleSolitaireModelState(model);
    Appendable ap = new StringBuilder();
    TriangleSolitaireTextView view = new TriangleSolitaireTextView(state, ap);
    String move1 = "2 0 0 0  2 2 2 0  0 0 2 2  3 0 1 0  3 3 1 1   3 2 3 0   4 0 2 0 "
            + " 1 0 3 0  4 2 4 0   4 0 2 0   4 4 4 2 ";
    Readable rd = new StringReader(move1 + " q");
    MarbleSolitaireControllerImpl controller = new MarbleSolitaireControllerImpl(model, view, rd);
    controller.playGame();

    String expectedOutput =
            "    _\n"
                    + "   _ O\n"
                    + "  O _ _\n"
                    + " _ _ _ _\n"
                    + "_ _ O _ _\n"
                    + "Score: 3\n";

    String[] output = ap.toString().split("Game over!\n");

    for (int i = 0; i < output.length; i++) {
      System.out.println(output[i]);
    }

    assertEquals(expectedOutput, output[1]);
  }

  @Test
  public void testIsPosNumeric() {
    TriangleSolitaireModel model = new TriangleSolitaireModel();
    TriangleSolitaireModelState state = new TriangleSolitaireModelState(model);
    Appendable ap = new StringBuilder();
    TriangleSolitaireTextView view = new TriangleSolitaireTextView(state, ap);
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
