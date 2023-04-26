package controller.commands;

import controller.ImageCommand;
import model.Image;
import model.ImageImpl;
import model.ImageModel;

/**
 * This class represents a load command, and implements the ImageCommand interface.
 * It's operation are used to load a image from a given file location.
 */
public class Load implements ImageCommand {
  private final String path;
  private final String name;

  /**
   * Constructs a new load object given an array of user input strings.
   * @param tokens the tokens from the argument given by the user
   * @throws IllegalStateException if the given set of tokens is not large enough: NOT if they're
   *                               incorrect.
   */
  public Load(String[] tokens) {
    if (tokens.length != 3) {
      throw new IllegalStateException("Invalid command length: " + tokens.length);
    }
    this.path = tokens[1];
    this.name = tokens[2];
  }

  @Override
  public String use(ImageModel model) throws IllegalStateException {
    try {
      Image img = new ImageImpl(this.path);
      model.loadImage(img, this.name);
    } catch (IllegalArgumentException e) {
      throw new IllegalStateException("File not found.");
    }
    return (this.name);
  }
}
