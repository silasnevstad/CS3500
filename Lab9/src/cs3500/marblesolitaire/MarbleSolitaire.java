package cs3500.marblesolitaire;

import java.io.InputStreamReader;

import cs3500.marblesolitaire.controller.MarbleSolitaireControllerImpl;
import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModelState;
import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireModel;
import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireModelState;
import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModel;
import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModelState;
import cs3500.marblesolitaire.view.EuropeanSolitaireTextView;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;
import cs3500.marblesolitaire.view.TriangleSolitaireTextView;

/**
 * A class to allow user input to interact with the marble solitaire game.
 */
public class MarbleSolitaire {
  /**
   * Runs a new controller's playGame method with default settings.
   * @param args array of sequence characters (input)
   */
  public static void main(String[] args) {
    for (String s: args) {
      System.out.println(s);
    }
    if (args[0].equalsIgnoreCase("English")) {
      EnglishSolitaireModel model = new EnglishSolitaireModel();
      MarbleSolitaireTextView view = new MarbleSolitaireTextView(
              new EnglishSolitaireModelState(model));
      new MarbleSolitaireControllerImpl(model, view, new InputStreamReader(System.in)).playGame();
    } else if (args[0].equalsIgnoreCase("triangular")) {
      TriangleSolitaireModel model = new TriangleSolitaireModel();
      TriangleSolitaireTextView view = new TriangleSolitaireTextView(
              new TriangleSolitaireModelState(model));
      new MarbleSolitaireControllerImpl(model, view, new InputStreamReader(System.in)).playGame();
    } else if (args[0].equalsIgnoreCase("european")) {
      EuropeanSolitaireModel model = new EuropeanSolitaireModel();
      EuropeanSolitaireTextView view = new EuropeanSolitaireTextView(
              new EuropeanSolitaireModelState(model));
      new MarbleSolitaireControllerImpl(model, view, new InputStreamReader(System.in)).playGame();
    } else {
      throw new IllegalArgumentException("Please pass in game type"
              + "(english, triangle, or european");
    }
  }
}
