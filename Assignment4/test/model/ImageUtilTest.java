package model;

import org.junit.Before;
import org.junit.Test;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import static model.ImageUtil.clamp;
import static model.ImageUtil.getBufferedImage;
import static model.ImageUtil.getHeight;
import static model.ImageUtil.getWidth;
import static model.ImageUtil.readImage;
import static model.ImageUtil.writeBuffImage;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * This class represents testing for the image util class.
 */
public class ImageUtilTest {
  Image img;

  @Before
  public void init() {
    try {
      this.img = new ImageImpl("res/earth.jpeg");
    } catch (IllegalArgumentException e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void testGetBufferedImage() {
    BufferedImage expected = new BufferedImage(225, 225, BufferedImage.TYPE_INT_RGB);
    int[][][] newImg = new int[225][225][3];
    BufferedImage actual = null;
    try {
      actual = getBufferedImage(newImg);
    } catch (IOException e) {
      fail(e.getMessage());
    }
    for (int x = 0; x < expected.getWidth(); x++) {
      for (int y = 0; y < expected.getHeight(); y++) {
        assertEquals(expected.getRGB(x, y), actual.getRGB(x, y));
      }
    }
  }

  @Test
  public void testWriteImage() {
    File testFile = new File("res/earthTest.png");
    File testFile2 = new File("res/earthTest2.png");
    try {
      writeBuffImage(getBufferedImage(this.img.getImage()), "res/earthTest.png");
    } catch (IOException e) {
      fail(e.getMessage());
    }
    assertTrue(testFile.exists());
  }

  @Test
  public void testGetDimensions() throws IOException {
    assertEquals(225, getWidth("res/earth.jpeg"));
    assertEquals(225, getHeight("res/earth.jpeg"));
  }

  @Test
  public void testReadImage() throws IOException {
    BufferedImage expected = ImageIO.read(new FileInputStream("res/earth.jpeg"));
    int[][][] actual = readImage("res/earth.jpeg");
    for (int x = 0; x < expected.getWidth() - 1; x++) {
      for (int y = 0; y < expected.getHeight() - 1; y++) {
        int colorNum = expected.getRGB(x, y);
        Color color = new Color(colorNum);
        int red = color.getRed();
        int green = color.getGreen();
        int blue = color.getBlue();
        assertEquals(red, actual[x][y][0]);
        assertEquals(green, actual[x][y][1]);
        assertEquals(blue, actual[x][y][2]);
      }
    }
  }

  @Test
  public void testClamp() {
    int[] testPixel = new int[]{-100, 256, 300};
    int[] expected = new int[]{0, 255, 255};
    int[] actual = clamp(testPixel);
    assertEquals(expected[0], actual[0]);
    assertEquals(expected[1], actual[1]);
    assertEquals(expected[2], actual[2]);
    assertEquals(expected.length, actual.length);
  }
}
