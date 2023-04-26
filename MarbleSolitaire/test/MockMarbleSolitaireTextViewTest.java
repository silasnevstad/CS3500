import org.junit.Test;
import org.junit.Before;

import java.io.IOException;
import java.io.StringReader;

import cs3500.marblesolitaire.controller.MarbleSolitaireController;
import cs3500.marblesolitaire.controller.MarbleSolitaireControllerImpl;
import cs3500.marblesolitaire.model.hw04.AbstractSolitaireModel;
import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.view.MockMarbleSolitaireTextView;

import static org.junit.Assert.assertEquals;

/**
 * A class representing tests for the mock marble solitaire text view class.
 */
public class MockMarbleSolitaireTextViewTest {
  StringBuilder log;
  MockMarbleSolitaireTextView mock;
  AbstractSolitaireModel model;
  Readable rd;
  MarbleSolitaireController controller;

  @Before
  public void init() {
    this.log = new StringBuilder();
    this.mock = new MockMarbleSolitaireTextView(this.log);
    this.model = new EnglishSolitaireModel();
    this.rd = new StringReader("");
    this.controller = new MarbleSolitaireControllerImpl(this.model, this.mock, this.rd);

  }

  @Test
  public void testRenderMessage() throws IOException {
    this.mock.renderMessage("testing");
    assertEquals("testing", this.log.toString());
    this.mock.renderMessage(" testing again");
    assertEquals("testing testing again", this.log.toString());
  }
}
