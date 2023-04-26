package model;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;

/**
 * This class contains utility methods to read an image from file and write to a file.
 */
public class ImageUtil {
  /**
   * Reads an image filename and return the contents as an integer array.
   *
   * @param filename the file's path.
   * @return an array of integers [[1, 2, 3] [4, 5, 6]]
   */
  public static int[][][] readImage(String filename) throws IOException {
    BufferedImage input = ImageIO.read(new FileInputStream(filename));

    int[][][] image = new int[input.getWidth()][input.getHeight()][3];
    for (int x = 0; x < input.getWidth() - 1; x++) {
      for (int y = 0; y < input.getHeight() - 1; y++) {
        int color = input.getRGB(x, y);
        Color c = new Color(color);
        image[x][y][0] = c.getRed();
        image[x][y][1] = c.getGreen();
        image[x][y][2] = c.getBlue();
      }
    }
    return image;
  }

  /**
   * Gets the width of an image (in pixels).
   *
   * @param filename the file's path.
   * @return an integer of the image's width.
   * @throws IOException in case the file is not found.
   */
  public static int getWidth(String filename) throws IOException {
    BufferedImage input;

    input = ImageIO.read(new FileInputStream(filename));

    return input.getWidth();
  }

  /**
   * Gets the height of an image (in pixels).
   *
   * @param filename the file's path.
   * @return an integer of image's height.
   * @throws IOException in case the file is not found
   */
  public static int getHeight(String filename) throws IOException {
    BufferedImage input;
    input = ImageIO.read(new FileInputStream(filename));

    return input.getHeight();
  }

  /**
   * Write an image to a file in a given format.
   *
   * @param rgb      the image data as a 3D array of integers.
   * @param width    the width of the image.
   * @param height   the height of the image.
   * @param filename the path to the image.
   * @throws IOException if the provided path cannot be written to.
   */
  public static void writeImage(int[][][] rgb, int width, int height, String filename)
          throws IOException {

    BufferedImage output = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        int r = rgb[x][y][0];
        int g = rgb[x][y][1];
        int b = rgb[x][y][2];
        int color = (r << 16) + (g << 8) + b;
        output.setRGB(x, y, color);
      }
    }

    String ext = filename.substring(filename.indexOf(".") + 1);
    ImageIO.write(output, ext, new File(filename));
  }

  /**
   * Writes a PPM image to a file.
   *
   * @param img      the image as an array
   * @param destFile the name of the target file
   * @throws IOException if the provided path cannot be written to
   */
  public static void writePPM(int[][][] img, String destFile) throws IOException {
    FileWriter newFile = new FileWriter(destFile);
    newFile.write("p3\n" + img.length + " " + img[0].length + "\n255\n");
    for (int i = 0; i < img[0].length; i++) {
      for (int j = 0; j < img.length; j++) {
        newFile.write(img[j][i][0] + "\n" + img[j][i][1] + "\n" + img[j][i][2] + "\n");
      }
    }
    newFile.close();
  }

  /**
   * Write a BufferedImage object to a file.
   *
   * @param image    the BufferedImage to write.
   * @param filename the path to the image.
   * @throws IOException if the provided path cannot be written to.
   */
  public static void writeBuffImage(BufferedImage image, String filename)
          throws IOException {
    String ext = filename.substring(filename.indexOf(".") + 1);
    ImageIO.write(image, ext, new File(filename));
  }

  /**
   * Returns a buffered image given an integer array.
   *
   * @param rgb a 3D integer array of an image (x, y, color).
   */
  public static BufferedImage getBufferedImage(int[][][] rgb) throws IOException {
    BufferedImage output = new BufferedImage(rgb.length, rgb[0].length,
            BufferedImage.TYPE_INT_RGB);
    for (int x = 0; x < rgb.length; x++) {
      for (int y = 0; y < rgb[0].length; y++) {
        int r = rgb[x][y][0];
        int g = rgb[x][y][1];
        int b = rgb[x][y][2];
        int color = (r << 16) + (g << 8) + b;
        output.setRGB(x, y, color);
      }
    }
    return output;
  }

  /**
   * Given a buffered image, returns an {@code Image} instance.
   * @param input the given {@code BufferedImage}
   * @return an {@code Image} equivalent to this given input
   */
  public static Image getImageFromBuffered(BufferedImage input) {
    int width = input.getWidth();
    int height = input.getHeight();
    int[][][] pixels = new int[width][height][3];

    BufferedImage output = new BufferedImage(input.getWidth(), input.getHeight(),
            BufferedImage.TYPE_INT_RGB);

    for (int x = 0; x < input.getWidth(); x++) {
      for (int y = 0; y < input.getHeight(); y++) {
        int color = input.getRGB(x, y);
        Color c = new Color(color);
        pixels[x][y][0] = c.getRed();
        pixels[x][y][1] = c.getGreen();
        pixels[x][y][2] = c.getBlue();
      }
    }

    return new ImageImpl(pixels);
  }

  /**
   * Read an image file in the PPM format and print the colors.
   *
   * @param filename the path of the file.
   */
  public static int[][][] readPPM(String filename) throws IllegalArgumentException {
    Scanner sc;

    try {
      sc = new Scanner(new FileInputStream(filename));
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("File " + filename + " not found!");
    }
    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }

    //now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());

    String token;

    token = sc.next();
    if (!token.equalsIgnoreCase("P3")) {
      throw new IllegalArgumentException("Invalid PPM file: plain RAW file should begin with P3");
    }
    int width = sc.nextInt();
    int height = sc.nextInt();
    int maxValue = sc.nextInt(); // not used in our case...

    int[][][] ppm = new int[width][height][3];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        ppm[j][i][0] = sc.nextInt();
        ppm[j][i][1] = sc.nextInt();
        ppm[j][i][2] = sc.nextInt();
      }
    }
    return ppm;
  }

  /**
   * Clamps a pixel's RGB values to their allowed range, 0 to 255.
   *
   * @param pixel the pixel to clamp ([r, g, b]).
   * @return the same pixel as given with values clamped to 0-255.
   */
  public static int[] clamp(int[] pixel) {
    int[] newPixel = new int[3];
    newPixel[0] = Math.max(0, Math.min(255, pixel[0]));
    newPixel[1] = Math.max(0, Math.min(255, pixel[1]));
    newPixel[2] = Math.max(0, Math.min(255, pixel[2]));
    return newPixel;
  }
}