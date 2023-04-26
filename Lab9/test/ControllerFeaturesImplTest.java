import org.junit.Test;

import cs3500.marblesolitaire.controller.MarbleControllerFeaturesImpl;
import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModelState;
import cs3500.marblesolitaire.view.MarbleSolitaireGuiView;
import cs3500.marblesolitaire.view.SwingGuiView;
import cs3500.marblesolitaire.view.SwingGuiViewMock;

import static org.junit.Assert.assertEquals;

public class ControllerFeaturesImplTest {
  private EnglishSolitaireModel model;
  private EnglishSolitaireModelState state;
  private MarbleSolitaireGuiView view;
  private MarbleControllerFeaturesImpl controller;

  @Test
  public void testControllerFeaturesInputToFirst() {
    StringBuilder log = new StringBuilder();
    this.model = new EnglishSolitaireModel();
    this.state =  new EnglishSolitaireModelState(this.model);
    this.view = new SwingGuiViewMock(log);
    this.controller = new MarbleControllerFeaturesImpl(this.model, this.view);
    this.controller.play(4, 4);
    assertEquals(32, this.model.getScore());
    this.controller.play(6, 4);
    System.out.println("foo" + log);
    assertEquals(31, this.model.getScore());
  }
}
