package cs3500.marblesolitaire.controller;

/**
 * An interface representing a marble solitaire controller.
 */
public interface MarbleSolitaireController {
  /**
   * Plays a new game of Marble Solitaire.
   * @throws IllegalStateException if the controller is unable to successfully read input or
   *                               transmit output.
   */
  void playGame() throws IllegalStateException;
}
