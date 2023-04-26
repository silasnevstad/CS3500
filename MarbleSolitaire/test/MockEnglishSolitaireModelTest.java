import org.junit.Before;
import org.junit.Test;

import java.io.StringReader;

import cs3500.marblesolitaire.controller.MarbleSolitaireController;
import cs3500.marblesolitaire.controller.MarbleSolitaireControllerImpl;
import cs3500.marblesolitaire.model.hw02.MockEnglishSolitaireModel;
import cs3500.marblesolitaire.view.MockMarbleSolitaireTextView;

import static org.junit.Assert.assertEquals;

/**
 * A class representing test for the mock english solitaire model.
 * Used to test if the inputs received are correct.
 */
public class MockEnglishSolitaireModelTest {
  StringBuilder logModel;
  StringBuilder logView;
  MockEnglishSolitaireModel mockModel;
  MockMarbleSolitaireTextView mockView;
  Readable rd;
  MarbleSolitaireController controller;


  @Before
  public void init() {
    this.logModel = new StringBuilder();
    this.logView = new StringBuilder();
    this.mockModel = new MockEnglishSolitaireModel(this.logModel);
    this.mockView = new MockMarbleSolitaireTextView(this.logView);
    this.rd = new StringReader("6 4 4 4 q");
    this.controller = new MarbleSolitaireControllerImpl(this.mockModel, this.mockView, this.rd);
  }

  @Test
  public void testMoveInputs() {
    controller.playGame();
    assertEquals("from row = 6, from col = 4, to row = 4, to col = 4\n",
            this.logModel.toString());
  }
}
