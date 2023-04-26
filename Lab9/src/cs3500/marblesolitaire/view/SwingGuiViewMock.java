package cs3500.marblesolitaire.view;

import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModelState;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

/**
 * This class represents a mock SwingGuiView class used for testing.
 */
public class SwingGuiViewMock extends SwingGuiView {
  StringBuilder log;

  public SwingGuiViewMock(StringBuilder log) {
    super(new EnglishSolitaireModelState());
    this.log = log;
  }

  @Override
  public void renderMessage(String message) {
    this.log.append(message);
  }
}
