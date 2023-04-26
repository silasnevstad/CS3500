package controller.commands;

import controller.ImageCommand;
import model.Image;
import model.ImageImpl;
import model.ImageModel;

/**
 * This class represents a horizontal flip command, and implements the ImageCommand interface.
 * It's operation are used to flip an image verically.
 */
public class VerticalFlipCommand implements ImageCommand {
  private final String fileName;
  private final String destFile;

  /**
   * Constructs a vertical flip command, given an array of strings pertaining to user inputs.
   * @param tokens the tokens from the argument given by the user.
   * @throws IllegalStateException if the given set of tokens is not large enough: NOT if they're
   *     incorrect.
   */
  public VerticalFlipCommand(String[] tokens) {
    if (tokens.length != 3) {
      throw new IllegalStateException("Invalid command length: " + tokens.length);
    }
    this.fileName = tokens[1];
    this.destFile = tokens[2];
  }

  @Override
  public String use(ImageModel model) throws IllegalStateException {
    int[][][] img = model.getImage(this.fileName);
    int width = img.length;
    int height = img[0].length;
    int[][][] newImg = new int[width][height][3]; // this prevents us from modifying the original

    for (int x = 0; x < width; x++) {
      for (int y = height - 1; y > 0; y--) {
        newImg[x][y][0] = img[x][height - y][0];
        newImg[x][y][1] = img[x][height - y][1];
        newImg[x][y][2] = img[x][height - y][2];
      }
    }
    Image newImage = new ImageImpl(newImg);
    model.loadImage(newImage, this.destFile);
    return this.destFile;
  }
}