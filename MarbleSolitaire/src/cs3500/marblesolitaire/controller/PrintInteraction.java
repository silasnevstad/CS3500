package cs3500.marblesolitaire.controller;

/**
 * Represents the printing of a sequence of lines to output.
 */
public class PrintInteraction implements Interaction {
  String[] lines;

  /**
   * Creates a new class implementing the interaction interface.
   * @param lines argument/s to append to output.
   * @returns a new PrintInteraction
   */
  public static Interaction prints(String... lines) {
    return (input, output) -> {
      for (String line : lines) {
        output.append(line).append('\n');
      }
    };
  }

  /**
   * Appends lines to given output.
   * @param in StringBuilder representing input.
   * @param out StringBuilder representing output.
   */
  public void apply(StringBuilder in, StringBuilder out) {
    for (String line : lines) {
      out.append(line).append("\n");
    }
  }
}

