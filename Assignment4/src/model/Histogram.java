package model;

/**
 * Class representing the decorator that adds the histogram functionality to {@code Image}.
 */
public class Histogram extends ImageDeco {
  /**
   * Creates a new {@code Histogram} decoration with the given image as a base.
   * @param image the given image
   */
  public Histogram(Image image) {
    super(image);
  }

  @Override
  public int[][][] getImage() {
    return this.base.getImage();
  }

  @Override
  public int getIWidth() {
    return this.base.getIWidth();
  }

  @Override
  public int getIHeight() {
    return this.base.getIHeight();
  }

  /**
   * Returns a 2D array, containing all the histograms for the image in the following order:
   * Red, Green, Blue, Intensity.
   * @return a 2D-array of histograms
   */
  // This approach makes the most sense: to get the histograms one at a time could be up to a
  // O((r * c)^4) process, this makes it O(r * c)
  public int[][] getHistograms() {
    int[][][] image = getImage();
    int[][] histograms = new int[4][256]; // THIS ASSUMPTION IS MADE BASED ON EXISTING RGB VALUES
    for (int[][] col : image) {
      for (int[] pixel : col) {
        // RED
        histograms[0][pixel[0]]++;
        // GREEN
        histograms[1][pixel[1]]++;
        // BLUE
        histograms[2][pixel[2]]++;
        // INTENSITY
        int intensityVal = ((pixel[0] + pixel[1] + pixel[2]) / 3);
        histograms[3][intensityVal]++;
      }
    }
    return histograms;
  }
}
