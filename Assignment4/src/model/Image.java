package model;

/**
 * This interface represents an image and the operations to monitor
 * it, but not change it.
 */
public interface Image {

  /**
   * This method gets the integer array value for this image.
   *
   * @return the integer array value for this image in the following format: [[[a,b,c],[e,f,g]],
   *     [[h,i,j],[k,l,m]]].
   */
  int[][][] getImage();

  /**
   * Getter for the image's width.
   *
   * @return the width of this image
   */
  int getIWidth();

  /**
   * Getter for the image's height.
   *
   * @return the height of this image
   */
  int getIHeight();
}