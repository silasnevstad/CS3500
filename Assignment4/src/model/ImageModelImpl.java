package model;

import java.util.HashMap;

/**
 * This class represents the operations of the image model,
 * the model interface for this program.
 */
public class ImageModelImpl implements ImageModel {
  private HashMap<String, Image> images;


  /**
   * Constructs an ImageModelImpl object given a filename to use as this image.
   *
   * @param fileName     String of the file's name.
   * @param fileLocation String of the file's location.
   * @throws IllegalArgumentException in case file could not be found or loaded.
   */
  public ImageModelImpl(String fileName, String fileLocation)
          throws IllegalArgumentException {
    Image img = new ImageImpl(fileLocation);
    this.images = new HashMap<>();
    this.images.put(fileName, img);
  }

  /**
   * Constructs an ImageModelImpl object with an empty set of images.
   */
  public ImageModelImpl() {
    images = new HashMap<>();
  }

  @Override
  public int[][][] getImage(String name) throws IllegalStateException {
    Image found = this.images.get(name);
    if (found == null) {
      throw new IllegalStateException("Image " + name + " does not exist");
    }

    return found.getImage();
  }

  /**
   * Loads given image to this model.
   *
   * @param image an Image class.
   */
  @Override
  public void loadImage(Image image, String name) {
    this.images.put(name, image);
  }

  @Override
  public int getImageWidth(String fileName) {
    Image find = this.images.get(fileName);
    if (find == null) {
      return 0;
    }

    return find.getIWidth();
  }

  @Override
  public int getImageHeight(String fileName) {
    Image find = this.images.get(fileName);
    if (find == null) {
      return 0;
    }

    return find.getIHeight();
  }
}