package controller;

import java.util.Scanner;

/**
 * An interface representing an image controller, used to interact with the model and the user.
 */
public interface ImageController {
  /**
   * Process a given string command and return status or error as a string.
   *
   * @param scan the Scanner for input
   * @return status or error message
   * @throws IllegalStateException if something goes wrong during runtime
   */
  String processCommand(Scanner scan) throws IllegalStateException;

  /**
   * Start the program, i.e. give control to the controller.
   *
   * @throws IllegalStateException if the output is not functional
   */
  void start() throws IllegalStateException;
}