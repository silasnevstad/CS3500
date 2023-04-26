package controller;

import model.ImageModel;

/**
 * Interface for commands that work on image model, contains use method.
 */
public interface ImageCommand {
  /**
   * the function that completes the operation that the command is meant to do.
   *
   * @param model the model used by the controller
   * @return the name of the destination file
   * @throws IllegalStateException if the given image or destination don't exist.
   */
  String use(ImageModel model) throws IllegalStateException;
}