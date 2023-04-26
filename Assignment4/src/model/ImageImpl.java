package model;

import static model.ImageUtil.readImage;
import static model.ImageUtil.readPPM;

/**
 * This class represents an implementation of the image interface. It represents an image,
 * and the operations to create, and monitor it.
 */
public class ImageImpl implements Image {
  private int[][][] image;
  private final int width;
  private final int height;

  /**
   * Constructs an ImageImpl object given a file.
   *
   * @param filename the name of the file.
   * @throws IllegalArgumentException is thrown if the file could not be loaded or found.
   */
  public ImageImpl(String filename) throws IllegalArgumentException {
    try {
      this.image = readImage(filename);
    } catch (Exception e) {
      // the next check is to see if we can get ppm
      try {
        this.image = readPPM(filename);

      } catch (IllegalArgumentException ex) {
        throw new IllegalArgumentException("Error: Invalid File");
      }
    }
    this.width = this.image.length;
    this.height = this.image[0].length;
  }

  /**
   * Constructs an ImageImpl object given an integer array representing the pixels
   * * of the image (x, y, color).
   *
   * @param image a 3D integer array of pixels.
   */
  public ImageImpl(int[][][] image) {
    this.image = image;
    this.width = image.length;
    this.height = image[0].length;
  }

  /**
   * Gets the integer array for this image.
   *
   * @return the 3D integer array representing this image.
   */
  @Override
  public int[][][] getImage() {
    return this.image;
  }

  @Override
  public int getIWidth() {
    return this.width;
  }

  @Override
  public int getIHeight() {
    return this.height;
  }
}