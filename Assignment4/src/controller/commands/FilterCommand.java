package controller.commands;

import controller.ImageCommand;
import model.Image;
import model.ImageImpl;
import model.ImageModel;

import static model.ImageUtil.clamp;

/**
 * This class implements the ImageFilter interface and represents a filter command.
 * It's used to apply a filter to an image.
 */
public class FilterCommand implements ImageCommand {
  private final String fileName;
  private final String destFile;
  private final double[][] filter;

  /**
   * Creates a new blur object given tokens, containing the file and destination.
   * @param tokens the tokens from the argument given by the user
   * @throws IllegalStateException if the given set of tokens is not large enough: NOT if they're
   *                               incorrect.
   */
  public FilterCommand(String[] tokens, double[][] filter) {
    if (tokens.length != 3) {
      throw new IllegalStateException("Invalid command length: " + tokens.length);
    }
    if (filter.length  % 2 != 1) {
      throw new IllegalStateException("Invalid filter, length: " + filter.length);
    }
    this.fileName = tokens[1];
    this.destFile = tokens[2];
    this.filter = filter;
  }

  @Override
  public String use(ImageModel model) throws IllegalStateException {
    int[][][] img = model.getImage(this.fileName);
    int width = img.length;
    int height = img[0].length;
    int[][][] newImg = new int[width][height][3];

    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        newImg[x][y] = clamp(createNewPixel(getKernel(x, y, img, this.filter.length),
                this.filter));
      }
    }

    Image newImage = new ImageImpl(newImg);
    model.loadImage(newImage, this.destFile);

    return this.destFile;
  }

  /**
   * Creates a new pixel and applies a given filter, using its related kernel.
   * @param kernel a 3D integer array of pixels.
   * @param filter a 2D integer array representing a filter.
   * @return an integer array containing a new pixel's filtered rgb values.
   */
  protected int[]  createNewPixel(int[][][] kernel, double[][] filter) {
    int[] pixel = new int[3];
    int width = kernel.length;
    int height = kernel[0].length;
    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        pixel[0] += (int) Math.round(kernel[x][y][0] * filter[x][y]);
        pixel[1] += (int) Math.round(kernel[x][y][1] * filter[x][y]);
        pixel[2] += (int) Math.round(kernel[x][y][2] * filter[x][y]);
      }
    }
    return pixel;
  }

  /**
   * Gets a kernel, a 3D array of pixels, of a specified size, for a given pixel.
   * @param row the row of the pixel.
   * @param col the column of the pixel.
   * @param image the image to pull the pixels from.
   * @param size size of the filter??
   * @return a 3D integer array of pixels.
   */
  protected int[][][] getKernel(int row, int col, int[][][] image, int size) {
    int[][][] kernel = new int[size][size][3];
    int prop = Math.floorDiv(size, 2);
    for (int x = 0; x < size; x++) {
      for (int y = 0; y < size; y++) {
        try {
          kernel[x][y][0] = image[row + (x - prop)][col + (y - prop)][0];
          kernel[x][y][1] = image[row + (x - prop)][col + (y - prop)][1];
          kernel[x][y][2] = image[row + (x - prop)][col + (y - prop)][2];
        } catch (IndexOutOfBoundsException e) {
          kernel[x][y][0] = 0;
          kernel[x][y][1] = 0;
          kernel[x][y][2] = 0;
        }
      }
    }
    return kernel;
  }


}
