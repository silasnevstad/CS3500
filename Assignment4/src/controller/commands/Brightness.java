package controller.commands;

import controller.ImageCommand;
import model.Image;
import model.ImageImpl;
import model.ImageModel;

/**
 * This class represents a brightness command.
 * It changes the brightness by a given amount.
 */
public class Brightness implements ImageCommand {
  private final int brightnessValue;
  private final String fileName;
  private final String destFile;

  /**
   * Creates a new Brightness object given an amount to change the brightness by.
   * @param tokens the tokens from the argument given by the user
   * @throws IllegalStateException if the given set of tokens is not large enough: NOT if they're
   *                               incorrect.
   */
  public Brightness(String[] tokens) {
    if (tokens.length != 4) {
      throw new IllegalStateException("Invalid command length: " + tokens.length);
    }
    this.fileName = tokens[2];
    this.brightnessValue = Integer.parseInt(tokens[1]);
    this.destFile = tokens[3];
  }

  @Override
  public String use(ImageModel model) throws IllegalStateException {
    int[][][] img = model.getImage(this.fileName);
    int[][][] newImg = new int[img.length][img[0].length][3];
    for (int x = 0; x < img.length; x++) {
      for (int y = 0; y < img[0].length; y++) {
        int red = img[x][y][0];
        int green = img[x][y][1];
        int blue = img[x][y][2];
        if (red + this.brightnessValue >= 0 && red + this.brightnessValue <= 255) {
          newImg[x][y][0] = red + this.brightnessValue;
        } else if (red + this.brightnessValue <= 0) {
          newImg[x][y][0] = 0;
        } else if (red + this.brightnessValue >= 255) {
          newImg[x][y][0] = 255;
        }
        if (green + this.brightnessValue >= 0 && green + this.brightnessValue <= 255) {
          newImg[x][y][1] = green + this.brightnessValue;
        } else if (green + this.brightnessValue <= 0) {
          newImg[x][y][1] = 0;
        } else if (green + this.brightnessValue >= 255) {
          newImg[x][y][1] = 255;
        }
        if (blue + this.brightnessValue >= 0 && blue + this.brightnessValue <= 255) {
          newImg[x][y][2] = blue + this.brightnessValue;
        } else if (blue + this.brightnessValue <= 0) {
          newImg[x][y][2] = 0;
        } else if (blue + this.brightnessValue >= 255) {
          newImg[x][y][2] = 255;
        }
      }
    }
    Image newImage = new ImageImpl(newImg);
    model.loadImage(newImage, this.destFile);
    return this.destFile;
  }
}
