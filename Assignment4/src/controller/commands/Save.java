package controller.commands;

import java.io.IOException;
import controller.ImageCommand;
import model.ImageModel;

import static model.ImageUtil.writeImage;
import static model.ImageUtil.writePPM;

/**
 * This class represents a save command, and implements the ImageCommand interface.
 * It's operation are used to save an image to a given file location.
 */
public class Save implements ImageCommand {
  private final String name;
  private final String destFile;

  /**
   * Constructs a new save object given an array of user input strings.
   * @param tokens the tokens from the argument given by the user
   * @throws IllegalStateException if the given set of tokens is not large enough: NOT if they're
   *                               incorrect.
   */
  public Save(String[] tokens) {
    if (tokens.length != 3) {
      throw new IllegalStateException("Invalid command length: " + tokens.length);
    }
    this.name = tokens[2];
    this.destFile = tokens[1];
  }

  @Override
  public String use(ImageModel model) throws IllegalStateException {
    int[][][] img = model.getImage(this.name);
    try {
      String ext = this.destFile.substring(this.destFile.indexOf(".") + 1);
      if (ext.equalsIgnoreCase("ppm")) {
        writePPM(img, this.destFile);
      } else {
        writeImage(img, img.length, img[0].length, this.destFile);
      }
    } catch (IOException e) {
      throw new IllegalStateException("File not found.");
    }
    // Save does not return a new image, simply return the old one!
    return this.name;
  }
}
