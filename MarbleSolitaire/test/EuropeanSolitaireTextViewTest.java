import org.junit.Test;
import org.junit.Before;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireModelState;
import cs3500.marblesolitaire.view.EuropeanSolitaireTextView;

/**
 * Represents a test class for the European Solitaire Model State class.
 */
public class EuropeanSolitaireTextViewTest {
  EuropeanSolitaireModelState tempState;
  EuropeanSolitaireModelState tempState5;
  EuropeanSolitaireTextView temp;
  EuropeanSolitaireTextView temp5;
  String actualBoard;
  String actual5Board;

  @Before
  public void init() {
    this.tempState = new EuropeanSolitaireModelState();
    this.tempState5 = new EuropeanSolitaireModelState(5);
    this.temp = new EuropeanSolitaireTextView(this.tempState);
    this.temp5 = new EuropeanSolitaireTextView(this.tempState5);
    this.actualBoard = "    O O O\n"
            + "  O O O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "  O O O O O\n"
            + "    O O O";
    this.actual5Board =
            "        O O O O O\n"
                    + "      O O O O O O O\n"
                    + "    O O O O O O O O O\n"
                    + "  O O O O O O O O O O O\n"
                    + "O O O O O O O O O O O O O\n"
                    + "O O O O O O O O O O O O O\n"
                    + "O O O O O O _ O O O O O O\n"
                    + "O O O O O O O O O O O O O\n"
                    + "O O O O O O O O O O O O O\n"
                    + "  O O O O O O O O O O O\n"
                    + "    O O O O O O O O O\n"
                    + "      O O O O O O O\n"
                    + "        O O O O O";
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidStateConstructor() {
    EuropeanSolitaireTextView view = new EuropeanSolitaireTextView(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidStateSecondConstructor() {
    Appendable ap = new StringBuilder();
    EuropeanSolitaireTextView view = new EuropeanSolitaireTextView(null, ap);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidAppendableConstructor() {
    Appendable ap = new StringBuilder();
    EuropeanSolitaireTextView view = new EuropeanSolitaireTextView(this.tempState, null);
  }

  @Test
  public void testToString() {
    assertEquals(this.actualBoard, this.temp.toString());
    assertEquals(this.actual5Board, this.temp5.toString());
  }

  @Test
  public void testRenderBoard() throws IOException {
    Appendable ap = new StringBuilder();
    EuropeanSolitaireTextView view = new EuropeanSolitaireTextView(this.tempState, ap);
    view.renderMessage("Testing...");
    assertEquals("Testing...", ap.toString());
    view.renderMessage("Testing again...");
    assertEquals("Testing...Testing again...", ap.toString());
  }
}
