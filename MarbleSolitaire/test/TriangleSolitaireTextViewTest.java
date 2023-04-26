import org.junit.Test;
import org.junit.Before;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModel;
import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModelState;
import cs3500.marblesolitaire.view.TriangleSolitaireTextView;

/**
 * Represents a test class for the Triangle Solitaire Model State class.
 */
public class TriangleSolitaireTextViewTest {
  TriangleSolitaireModelState tempState;
  TriangleSolitaireModelState tempState6;
  TriangleSolitaireModelState tempState7;
  TriangleSolitaireModelState tempStateCustomEmptySpace;
  TriangleSolitaireTextView temp;
  TriangleSolitaireTextView temp5;
  TriangleSolitaireTextView temp7;
  TriangleSolitaireTextView tempCustomEmptySpace;
  String actualBoard;
  String actual6Board;
  String actual7Board;
  String customEmptySpaceBoard;

  @Before
  public void init() {
    this.tempState = new TriangleSolitaireModelState();
    this.tempState6 = new TriangleSolitaireModelState(6);
    this.tempState7 = new TriangleSolitaireModelState(7);
    this.tempStateCustomEmptySpace = new TriangleSolitaireModelState(
            new TriangleSolitaireModel(5, 4, 4));
    this.temp = new TriangleSolitaireTextView(this.tempState);
    this.temp5 = new TriangleSolitaireTextView(this.tempState6);
    this.temp7 = new TriangleSolitaireTextView(this.tempState7);
    this.tempCustomEmptySpace = new TriangleSolitaireTextView(this.tempStateCustomEmptySpace);
    this.actualBoard = "    _\n"
            + "   O O\n"
            + "  O O O\n"
            + " O O O O\n"
            + "O O O O O";
    this.actual6Board = "     _\n"
            + "    O O\n"
            + "   O O O\n"
            + "  O O O O\n"
            + " O O O O O\n"
            + "O O O O O O";
    this.actual7Board = "      _\n"
            + "     O O\n"
            + "    O O O\n"
            + "   O O O O\n"
            + "  O O O O O\n"
            + " O O O O O O\n"
            + "O O O O O O O";
    this.customEmptySpaceBoard = "    O\n"
            + "   O O\n"
            + "  O O O\n"
            + " O O O O\n"
            + "O O O O _";
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidStateConstructor() {
    TriangleSolitaireTextView view = new TriangleSolitaireTextView(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidStateSecondConstructor() {
    Appendable ap = new StringBuilder();
    TriangleSolitaireTextView view = new TriangleSolitaireTextView(null, ap);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidAppendableConstructor() {
    Appendable ap = new StringBuilder();
    TriangleSolitaireTextView view = new TriangleSolitaireTextView(this.tempState, null);
  }

  @Test
  public void testToString() {
    assertEquals(this.actualBoard, this.temp.toString());
    assertEquals(this.actual6Board, this.temp5.toString());
    assertEquals(this.actual7Board, this.temp7.toString());
    assertEquals(this.customEmptySpaceBoard, this.tempCustomEmptySpace.toString());
  }

  @Test
  public void testRenderBoard() throws IOException {
    Appendable ap = new StringBuilder();
    TriangleSolitaireTextView view = new TriangleSolitaireTextView(this.tempState, ap);
    view.renderMessage("Testing...");
    assertEquals("Testing...", ap.toString());
    view.renderMessage("Testing again...");
    assertEquals("Testing...Testing again...", ap.toString());
  }
}
