package controller.commands;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import controller.ImageCommand;
import model.ImageModel;

import static model.ImageUtil.getBufferedImage;
import static model.ImageUtil.getImageFromBuffered;

/**
 * This class represents a resize command, its purpose is to resize images, either downscaling
 * or upscaling. It implements the ImageCommand interface.
 */
public class Resize implements ImageCommand {
  private final double size;
  private final String file;
  private final String destFile;

  /**
   * Constructs a resize command, given the amount to resize by.
   * @param tokens the command given to the controller, split by spaces
   */
  public Resize(String[] tokens) {
    if (tokens.length != 4) {
      throw new IllegalStateException("Invalid command length: " + tokens.length);
    }
    if (!Character.isDigit(tokens[1].charAt(tokens[1].length() - 1))) {
      throw new IllegalStateException("Resize command must be given a number to resize by.");
    }
    this.size = Double.parseDouble(tokens[1]);
    this.file = tokens[2];
    this.destFile = tokens[3];
  }

  @Override
  public String use(ImageModel model) throws IllegalStateException {
    int[][][] img = model.getImage(this.file);
    int width = img.length;
    int height = img[0].length;
    int resizedWidth = (int) (width * this.size);
    int resizedHeight = (int) (height * this.size);
    BufferedImage originalImage;
    try {
      originalImage = getBufferedImage(img);
    } catch (IOException e) {
      throw new IllegalStateException("Image " + this.file + " not found.");
    }
    BufferedImage resizedImage = new BufferedImage(
            resizedWidth,
            resizedHeight,
            BufferedImage.TYPE_INT_RGB);
    Graphics2D graphics2D = resizedImage.createGraphics();
    graphics2D.drawImage(originalImage, 0, 0, resizedWidth, resizedHeight, null);
    graphics2D.dispose();

    model.loadImage(getImageFromBuffered(resizedImage), this.destFile);

    return this.destFile;
  }
}
