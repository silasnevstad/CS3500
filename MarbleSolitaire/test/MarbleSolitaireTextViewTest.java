import org.junit.Test;
import org.junit.Before;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModelState;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;

/**
 * Represents a test class for the MarbleSolitaireTextView class.
 */
public class MarbleSolitaireTextViewTest {
  EnglishSolitaireModelState tempState;
  EnglishSolitaireModelState tempState5;
  EnglishSolitaireModelState tempState7;
  MarbleSolitaireTextView temp;
  MarbleSolitaireTextView temp5;
  MarbleSolitaireTextView temp7;
  String actualBoard;
  String actual5Board;
  String actual7Board;

  @Before
  public void init() {
    this.tempState = new EnglishSolitaireModelState();
    this.tempState5 = new EnglishSolitaireModelState(5);
    this.tempState7 = new EnglishSolitaireModelState(7);
    this.temp = new MarbleSolitaireTextView(this.tempState);
    this.temp5 = new MarbleSolitaireTextView(this.tempState5);
    this.temp7 = new MarbleSolitaireTextView(this.tempState7);
    this.actualBoard = "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O";

    this.actual5Board = "        O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O _ O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O";

    this.actual7Board = "            O O O O O O O\n"
            + "            O O O O O O O\n"
            + "            O O O O O O O\n"
            + "            O O O O O O O\n"
            + "            O O O O O O O\n"
            + "            O O O O O O O\n"
            + "O O O O O O O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O O O O O O O\n"
            + "O O O O O O O O O _ O O O O O O O O O\n"
            + "O O O O O O O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O O O O O O O\n"
            + "            O O O O O O O\n"
            + "            O O O O O O O\n"
            + "            O O O O O O O\n"
            + "            O O O O O O O\n"
            + "            O O O O O O O\n"
            + "            O O O O O O O";
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidStateConstructor() {
    MarbleSolitaireTextView view = new MarbleSolitaireTextView(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidStateSecondConstructor() {
    Appendable ap = new StringBuilder();
    MarbleSolitaireTextView view = new MarbleSolitaireTextView(null, ap);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidAppendableConstructor() {
    Appendable ap = new StringBuilder();
    MarbleSolitaireTextView view = new MarbleSolitaireTextView(this.tempState, null);
  }

  @Test
  public void testToString() {
    assertEquals(this.actualBoard, this.temp.toString());
    assertEquals(this.actual5Board, this.temp5.toString());
    assertEquals(this.actual7Board, this.temp7.toString());
  }

  @Test
  public void testRenderBoard() throws IOException {
    Appendable ap = new StringBuilder();
    MarbleSolitaireTextView view = new MarbleSolitaireTextView(this.tempState, ap);
    view.renderMessage("Testing...");
    assertEquals("Testing...", ap.toString());
    view.renderMessage("Testing again...");
    assertEquals("Testing...Testing again...", ap.toString());
  }
}
