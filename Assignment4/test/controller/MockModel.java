package controller;

import model.Image;
import model.ImageModel;

/**
 * Mock class to represent a {@code ImageModel} for controller testing.
 */
public class MockModel implements ImageModel {

  StringBuilder log;

  /**
   * Creates a new {@code MockModel} with given StringBuilder.
   * @param log the log to write given inputs to
   */
  public MockModel(StringBuilder log) {
    this.log = log;
  }

  @Override
  public int[][][] getImage(String name) throws IllegalStateException {
    this.log.append(name);
    return new int[][][]{{{1, 1, 1}}};
  }

  @Override
  public void loadImage(Image image, String name) {
    System.out.println("loadImage");
  }

  @Override
  public int getImageWidth(String fileName) {
    return 0;
  }

  @Override
  public int getImageHeight(String fileName) {
    return 0;
  }
}
