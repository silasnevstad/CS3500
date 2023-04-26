package cs3500.marblesolitaire.controller;

/**
 * Represents a user providing the program with  an input.
 */
public class InputInteraction implements Interaction {
  String input;

  /**
   * Creates a new class implementing the interaction interface.
   * @param in argument to append to input.
   * @returns a new InputInteraction
   */
  public static Interaction inputs(String in) {
    return (input, output) -> {
      input.append(in);
    };
  }

  public void apply(StringBuilder in, StringBuilder out) {
    in.append(input);
  }
}