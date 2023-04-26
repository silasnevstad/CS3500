package model;

import org.junit.Before;
import org.junit.Test;

import controller.ImageCommand;
import controller.commands.Brightness;
import controller.commands.ComponentCommand;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * This class represents testing for the image class.
 */
public class ImageTest {
  Image earth;
  Image dog;

  @Before
  public void init() {
    this.earth = new ImageImpl("res/earth.jpeg");
    this.dog = new ImageImpl("res/dog.jpeg");
  }

  @Test
  public void testFirstConstructorNonPPM() {
    this.earth = new ImageImpl("res/earth.jpeg");
    this.dog = new ImageImpl("res/dog.jpeg");
    assertEquals(479, this.dog.getIHeight());
  }

  @Test
  public void testFirstConstructorPPM() {
    this.earth = new ImageImpl("res/earth.ppm");
    assertEquals(225, this.earth.getIHeight());
  }

  @Test
  public void testFirstConstructorFailPPM() {
    try {
      this.earth = new ImageImpl("res/kingKong.ppm");
      fail("ImageImpl pretended it found an image");
    } catch (IllegalArgumentException e) {
      assertEquals("Error: Invalid File", e.getMessage());
    }
  }

  @Test
  public void testFirstConstructorFailNonPPM() {
    try {
      this.earth = new ImageImpl("res/kingKong.gif");
      fail("ImageImpl pretended it found an image");
    } catch (IllegalArgumentException e) {
      assertEquals("Error: Invalid File", e.getMessage());
    }
  }

  @Test
  public void testGetIHeight() {
    assertEquals(225, this.earth.getIHeight());
    assertEquals(479, this.dog.getIHeight());
  }

  @Test
  public void testGetIWidth() {
    assertEquals(225, this.earth.getIWidth());
    assertEquals(720, this.dog.getIWidth());
  }

  @Test
  public void testGetImage() {
    assertEquals(3, this.earth.getImage()[0][0].length);
    assertEquals(225, this.earth.getImage()[0].length);
    assertEquals(225, this.earth.getImage().length);
    assertEquals(3, this.dog.getImage()[0][0].length);
    assertEquals(479, this.dog.getImage()[0].length);
    assertEquals(720, this.dog.getImage().length);
  }

  @Test
  public void testGetImageExtensively() {
    int[][][] testImg = new int[100][100][3];
    for (int x = 0; x < 100; x++) {
      for (int y = 0; y < 100; y++) {
        testImg[x][y][0] = 255;
        testImg[x][y][1] = 140;
        testImg[x][y][2] = 150;
      }
    }
    ImageImpl pinkImg = new ImageImpl(testImg);

    assertEquals(100, pinkImg.getIHeight());
    assertEquals(100, pinkImg.getIWidth());

    int[][][] newImg = pinkImg.getImage();
    for (int x = 0; x < 100; x++) {
      for (int y = 0; y < 100; y++) {
        assertEquals(testImg[x][y][0], newImg[x][y][0]);
        assertEquals(testImg[x][y][1], newImg[x][y][1]);
        assertEquals(testImg[x][y][2], newImg[x][y][2]);
      }
    }
  }

  @Test
  public void testHistogramGrey() {
    ImageModel model = new ImageModelImpl("earth", "res/earth.ppm");
    Image earth = new ImageImpl("res/earth.ppm");
    ImageCommand grey = new ComponentCommand(new String[]{"red-component", "earth", "earth"},
        (int[] x) -> new int[]{x[0], x[0], x[0]});
    grey.use(model);
    int[][] ogHist = new Histogram(earth).getHistograms();
    int[][] newHist = new Histogram(new ImageImpl(model.getImage("earth"))).getHistograms();
    for (int i = 0; i < 256; i++) {
      assertEquals(ogHist[0][i], newHist[0][i]);
      assertEquals(ogHist[0][i], newHist[1][i]);
      assertEquals(ogHist[0][i], newHist[2][i]);
      assertEquals(ogHist[0][i], newHist[3][i]);
    }
  }

  @Test
  public void testHistogramBrighten() {
    ImageModel model = new ImageModelImpl("earth", "res/earth.ppm");
    Image earth = new ImageImpl("res/earth.ppm");
    ImageCommand bright = new Brightness(new String[]{"brighten", "10", "earth", "earth"});
    bright.use(model);
    int[][] ogHist = new Histogram(earth).getHistograms();
    int[][] newHist = new Histogram(new ImageImpl(model.getImage("earth"))).getHistograms();
    for (int i = 0; i < 245; i++) { // reduce value as it squishes against the edge
      assertEquals(ogHist[0][i], newHist[0][i + 10]);
      assertEquals(ogHist[1][i], newHist[1][i + 10]);
      assertEquals(ogHist[2][i], newHist[2][i + 10]);
    }
  }
}
