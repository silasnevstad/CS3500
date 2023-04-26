import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;

import cs3500.marblesolitaire.controller.Interaction;
import cs3500.marblesolitaire.controller.MarbleSolitaireControllerImpl;
import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModelState;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;
import cs3500.marblesolitaire.view.MockMarbleSolitaireTextView;

import static cs3500.marblesolitaire.controller.InputInteraction.inputs;
import static cs3500.marblesolitaire.controller.PrintInteraction.prints;
import static org.junit.Assert.assertEquals;

/**
 * A class representing tests for the interaction classes.
 */
public class InteractionTest {
  EnglishSolitaireModel model;
  EnglishSolitaireModelState state;
  MarbleSolitaireTextView view;
  MarbleSolitaireControllerImpl controller;
  Interaction[] interactions;
  StringBuilder actualOutput;

  @Before
  public void init() {
    String actualBoard = "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n";
    String actualBoardModifiedOnce = "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "    O _ O\n"
            + "    O O O\n";
    String actualBoardModifiedTwice = "    O O O\n"
            + "    O O O\n"
            + "O O O _ O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O _ O\n"
            + "    O O O\n";
    this.interactions = new Interaction[]{
            prints(actualBoard + "Score: 32"),
            inputs("6 4 4 4 "),
            prints(actualBoardModifiedOnce + "Score: 31"),
            inputs("3 -1 "),
            prints("Invalid move. Play Again."),
            inputs("4 5 4"),
            inputs("\nq"),
            prints("Game quit!\n" + "State of game when quit:\n"
                    + actualBoardModifiedTwice + "Score: 30")
    };
    this.actualOutput = new StringBuilder();
    this.model = new EnglishSolitaireModel();
    this.state = new EnglishSolitaireModelState(this.model);
    this.view = new MockMarbleSolitaireTextView(this.state, this.actualOutput);
  }

  /**
   * Applies a given array of interactions to a specified model.
   * @param model English Solitaire Model to perform with.
   * @param interactions array of interactions (inputs & outputs).
   */
  public String[] testRun(EnglishSolitaireModel model, Interaction[] interactions) {
    StringBuilder userInput = new StringBuilder();
    StringBuilder expectedOutput = new StringBuilder();

    for (Interaction interaction : interactions) {
      interaction.apply(userInput, expectedOutput);
    }

    StringReader input = new StringReader(userInput.toString());

    this.controller = new MarbleSolitaireControllerImpl(model, this.view, input);
    this.controller.playGame();

    String[] output = this.actualOutput.toString().split("\n");

    assertEquals(expectedOutput.toString(), actualOutput.toString());
    String[] returnString = {expectedOutput.toString(), actualOutput.toString()};
    return returnString;
  }

  @Test
  public void test() throws IOException {
    String[] testValues = testRun(this.model, this.interactions);
    assertEquals(testValues[0], testValues[1]);
  }
}
