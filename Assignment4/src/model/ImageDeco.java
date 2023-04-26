package model;

/**
 * Abstract class representing a decoration for the Image interface. The current
 * use of this is to implement histograms.
 */
public abstract class ImageDeco implements Image {

  protected Image base;

  /**
   * Creates a new {@code ImageDeco} with the given {@code Image} as a base. This is to require
   * a call to this constructor in the children of this class.
   * @param image the base image
   */
  ImageDeco(Image image) {
    this.base = image;
  }
}
