package model;

/**
 * This interface represents the model for this program.
 * It includes operations that transform an image.
 */
public interface ImageModel {

  /**
   * Gets the integer array for an Image of the given name, or throws exception.
   *
   * @param name the name of the Image
   * @return the 3D integer array representing the Image
   * @throws IllegalStateException if there is no Image with the given name
   */
  int[][][] getImage(String name) throws IllegalStateException;

  /**
   * Loads given image to this model.
   *
   * @param image the given Image
   * @param name  the name we will use to access this image
   */
  void loadImage(Image image, String name);

  /**
   * Returns the width value of the image with given fileName, or 0 if it does not exist.
   *
   * @param fileName the given name of the image
   * @return the width of the image with the given fileName or 0
   */
  int getImageWidth(String fileName);

  /**
   * Returns the height value of the image with given fileName, or 0 if it does not exist.
   *
   * @param fileName the given name of the image
   * @return the height of the image with the given fileName or 0
   */
  int getImageHeight(String fileName);
}