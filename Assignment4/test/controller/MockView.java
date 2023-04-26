package controller;

import java.util.function.Consumer;

import view.ImageView;

/**
 * Mock class to represent a {@code ImageView} for controller testing.
 */
public class MockView implements ImageView {

  StringBuilder log;
  String command;
  int[][][] imageLog;

  /**
   * Creates a new {@code MockView} with given StringBuilder and preset command.
   * @param log the log to write given inputs to
   * @param command command to run if getCommand is called
   */
  public MockView(StringBuilder log, String command) {
    this.log = log;
    this.command = command;
  }

  @Override
  public void makeVisible() {
    // Empty because mock class
  }

  @Override
  public String getCommand() {
    return this.command;
  }

  @Override
  public void showErrorMessage(String error) {
    this.log.append(error);
  }

  @Override
  public void setImage(int[][][] image) {
    this.imageLog = image;
  }

  @Override
  public void refresh() {
    // Empty because mock class
  }

  //TODO: maybe test?
  @Override
  public void setCommandCallBack(Consumer<String> command) {
    // Empty because mock class
  }

  @Override
  public void update(String file) {
    this.log.append(file);
  }

  @Override
  public void renderMessage(String message) {
    this.log.append(message);
  }

  @Override
  public void render() {
    // Empty because mock class
  }
}
