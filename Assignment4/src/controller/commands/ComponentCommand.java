package controller.commands;

import java.util.function.Function;

import controller.ImageCommand;
import model.Image;
import model.ImageImpl;
import model.ImageModel;

import static model.ImageUtil.clamp;

/**
 * Class representing a command that creates/edits an image to greyscale.
 */
public class ComponentCommand implements ImageCommand {

  private final String file;
  private final String destFile;
  private final Function<int[], int[]> component;

  /**
   * Attempts to create a new {@code ComponentCommand} with the given tokens as file and
   * destination, and a function to isolate the component in question.
   * @param tokens    the command given to the controller, INCLUDING the name of the component
   * @param component function that returns the new rgb values given the old ones
   * @throws IllegalStateException if the given set of tokens is not large enough: NOT if they're
   *                               incorrect.
   */
  public ComponentCommand(String[] tokens, Function<int[], int[]> component)
          throws IllegalStateException {
    if (tokens.length != 3) {
      throw new IllegalStateException("Invalid number of arguments: " + tokens.length);
    }

    this.file = tokens[1];
    this.destFile = tokens[2];
    this.component = component;
  }


  @Override
  public String use(ImageModel model) throws IllegalStateException {
    int[][][] img = model.getImage(this.file);
    int width = img.length;
    int height = img[0].length;
    int[][][] newImg = new int[width][height][3];

    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        newImg[x][y] = clamp(component.apply(img[x][y]));
      }
    }
    Image newImage = new ImageImpl(newImg);
    model.loadImage(newImage, this.destFile);
    return this.destFile;
  }
}