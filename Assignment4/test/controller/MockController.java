package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

/**
 * Mock class to represent a {@code ImageController} for controller testing.
 */
public class MockController implements ImageController, ActionListener {
  StringBuilder log;
  private MockView view;

  /**
   * Creates a new {@code MockController} object, given a StringBuilder.
   * @param log StringBuilder to monitor inputs for testing.
   */
  MockController(StringBuilder log, MockView view) {
    this.log = log;
    this.view = view;
  }

  @Override
  public String processCommand(Scanner scan) throws IllegalStateException {
    return this.view.getCommand();
  }

  @Override
  public void start() throws IllegalStateException {
    log.append(this.processCommand(null)); //don't need a scanner, just testing input
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    this.log.append(this.view.getCommand());
  }
}
