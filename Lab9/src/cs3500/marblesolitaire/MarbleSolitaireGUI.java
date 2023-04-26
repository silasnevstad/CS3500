package cs3500.marblesolitaire;

import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModelState;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;
import cs3500.marblesolitaire.view.SwingGuiView;

/**
 * A class to allow user input to interact with the marble solitaire game.
 */
public class MarbleSolitaireGUI {
  /**
   * Runs a new controller's playGame method with default settings.
   * @param args array of sequence characters (input)
   */
  public static void main(String[] args) {
    EnglishSolitaireModel model = new EnglishSolitaireModel();
    MarbleSolitaireModelState state = new EnglishSolitaireModelState(model);
    SwingGuiView view = new SwingGuiView(state);
  }
}
