package controller;

import org.junit.Before;
import org.junit.Test;

import java.io.File;

import controller.commands.Brightness;
import controller.commands.ComponentCommand;
import controller.commands.FilterCommand;
import controller.commands.HorizontalFlipCommand;
import controller.commands.Load;
import controller.commands.Save;
import controller.commands.VerticalFlipCommand;
import model.Image;
import model.ImageImpl;
import model.ImageModel;
import model.ImageModelImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Class containing tests for all the different commands that we have.
 */
public class ImageCommandTest {
  ComponentCommand c1;
  ImageCommand cmd;
  ImageModel m1;

  @Before
  public void init() {
    this.m1 = new ImageModelImpl();
    ImageImpl dogJpg;
    ImageImpl earthPPM;
    ImageImpl earthImg;
    ImageImpl londonImg;
    dogJpg = new ImageImpl("res/dog.jpeg");
    earthPPM = new ImageImpl("res/earth.ppm");
    earthImg = new ImageImpl("res/earth.jpeg");
    londonImg = new ImageImpl("res/london.bmp");
    this.m1.loadImage(dogJpg, "dog");
    this.m1.loadImage(earthPPM, "earthPPM");
    this.m1.loadImage(earthImg, "earth");
    this.m1.loadImage(londonImg, "london");
  }


  // GREYSCALE TESTING -----------------------------------------------------------------------
  @Test
  public void testRedComponent() {
    this.c1 = new ComponentCommand(new String[]{"red-component", "dog", "dog-red"},
        (int[] x) -> new int[]{x[0], x[0], x[0]});
    this.c1.use(this.m1);

    int[][][] original = this.m1.getImage("dog");
    int[][][] grey = this.m1.getImage("dog-red");

    for (int i = 0; i < this.m1.getImageWidth("dog-red"); i++) {
      for (int j = 0; j < this.m1.getImageHeight("dog-red"); j++) {
        // Check that all values are equal to the red component from before
        assertEquals(original[i][j][0], grey[i][j][0]);
        assertEquals(original[i][j][0], grey[i][j][1]);
        assertEquals(original[i][j][0], grey[i][j][2]);
      }
    }
  }

  @Test
  public void testGreenComponent() {
    this.c1 = new ComponentCommand(new String[]{"green-component", "dog", "dog-green"},
        (int[] x) -> new int[]{x[1], x[1], x[1]});
    this.c1.use(this.m1);

    int[][][] original = this.m1.getImage("dog");
    int[][][] grey = this.m1.getImage("dog-green");

    for (int i = 0; i < this.m1.getImageWidth("dog-green"); i++) {
      for (int j = 0; j < this.m1.getImageHeight("dog-green"); j++) {
        // Check that all values are equal to the green component from before
        assertEquals(original[i][j][1], grey[i][j][0]);
        assertEquals(original[i][j][1], grey[i][j][1]);
        assertEquals(original[i][j][1], grey[i][j][2]);
      }
    }
  }

  @Test
  public void testBlueComponent() {
    this.c1 = new ComponentCommand(new String[]{"blue-component", "dog", "dog-blue"},
        (int[] x) -> new int[]{x[2], x[2], x[2]});
    this.c1.use(this.m1);

    int[][][] original = this.m1.getImage("dog");
    int[][][] grey = this.m1.getImage("dog-blue");

    for (int i = 0; i < this.m1.getImageWidth("dog-blue"); i++) {
      for (int j = 0; j < this.m1.getImageHeight("dog-blue"); j++) {
        // Check that all values are equal to the blue component from before
        assertEquals(original[i][j][2], grey[i][j][0]);
        assertEquals(original[i][j][2], grey[i][j][1]);
        assertEquals(original[i][j][2], grey[i][j][2]);
      }
    }
  }

  @Test
  public void testValueComponent() {
    this.c1 = new ComponentCommand(new String[]{"value-component", "dog", "dog-value"},
        (int[] x) -> new int[]{Math.max(x[0], Math.max(x[1], x[2])),
                    Math.max(x[0], Math.max(x[1], x[2])),
                    Math.max(x[0], Math.max(x[1], x[2]))});
    this.c1.use(this.m1);

    int[][][] original = this.m1.getImage("dog");
    int[][][] grey = this.m1.getImage("dog-value");

    for (int i = 0; i < this.m1.getImageWidth("dog-value"); i++) {
      for (int j = 0; j < this.m1.getImageHeight("dog-value"); j++) {
        // Check that all values are equal to the original pixel's value
        int value = Math.max(original[i][j][0], Math.max(original[i][j][1], original[i][j][2]));
        assertEquals(value, grey[i][j][0]);
        assertEquals(value, grey[i][j][1]);
        assertEquals(value, grey[i][j][2]);
      }
    }
  }

  @Test
  public void testIntensityComponent() {
    this.c1 = new ComponentCommand(new String[]{"intensity-component", "dog", "dog-intensity"},
        (int[] x) -> new int[]{((x[0] + x[1] + x[2]) / 3),
            ((x[0] + x[1] + x[2]) / 3),
            ((x[0] + x[1] + x[2]) / 3)});
    this.c1.use(this.m1);

    int[][][] original = this.m1.getImage("dog");
    int[][][] grey = this.m1.getImage("dog-intensity");

    for (int i = 0; i < this.m1.getImageWidth("dog-intensity"); i++) {
      for (int j = 0; j < this.m1.getImageHeight("dog-intensity"); j++) {
        // Check that all values are equal to the original pixel's intensity
        int value = (original[i][j][0] + original[i][j][1] + original[i][j][2]) / 3;
        assertEquals(value, grey[i][j][0]);
        assertEquals(value, grey[i][j][1]);
        assertEquals(value, grey[i][j][2]);
      }
    }
  }

  @Test
  public void testLumaComponent() {
    this.c1 = new ComponentCommand(new String[]{"luma-component", "dog", "dog-luma"},
        (int[] x) -> new int[]{(int) ((0.2126 * x[0]) + (0.7152 * x[1]) + (0.0722 * x[2])),
            (int) ((0.2126 * x[0]) + (0.7152 * x[1]) + (0.0722 * x[2])),
            (int) ((0.2126 * x[0]) + (0.7152 * x[1]) + (0.0722 * x[2]))});
    this.c1.use(this.m1);

    int[][][] original = this.m1.getImage("dog");
    int[][][] grey = this.m1.getImage("dog-luma");

    for (int i = 0; i < this.m1.getImageWidth("dog-luma"); i++) {
      for (int j = 0; j < this.m1.getImageHeight("dog-luma"); j++) {
        // Check that all values are equal to the original pixel's luma
        int value = (int) ((0.2126 * original[i][j][0]) + (0.7152 * original[i][j][1]) +
                (0.0722 * original[i][j][2]));
        assertEquals(value, grey[i][j][0]);
        assertEquals(value, grey[i][j][1]);
        assertEquals(value, grey[i][j][2]);
      }
    }
  }
  // GREYSCALE TESTING -----------------------------------------------------------------------

  // FLIPPING TESTING START -----------------------------------------------------------------------
  @Test
  public void testFlippedHorizontal() {
    this.cmd = new HorizontalFlipCommand(new String[]{"horizontal-flip",
        "earth", "earth-horizontal"});
    this.cmd.use(this.m1);

    int[][][] actualImg = this.m1.getImage("earth");
    int width = actualImg.length;
    int height = actualImg[0].length;
    int[][][] newImg = this.m1.getImage("earth-horizontal");
    for (int x = width - 1; x > 0; x--) {
      for (int y = 0; y < height; y++) {
        assertEquals(newImg[x][y][0], actualImg[width - x][y][0]);
        assertEquals(newImg[x][y][1], actualImg[width - x][y][1]);
        assertEquals(newImg[x][y][2], actualImg[width - x][y][2]);
      }
    }
  }

  @Test
  public void testFlippedVertical() {
    this.cmd = new VerticalFlipCommand(new String[]{"vertical-flip", "earth", "earth-vertical"});
    this.cmd.use(this.m1);

    int[][][] actualImg = this.m1.getImage("earth");
    int width = actualImg.length;
    int height = actualImg[0].length;
    int[][][] newImg = this.m1.getImage("earth-vertical");
    for (int x = 0; x < width; x++) {
      for (int y = height - 1; y > 0; y--) {
        assertEquals(newImg[x][y][0], actualImg[x][height - y][0]);
        assertEquals(newImg[x][y][1], actualImg[x][height - y][1]);
        assertEquals(newImg[x][y][2], actualImg[x][height - y][2]);
      }
    }
  }

  // LOAD & SAVE TESTING START --------------------------------------------------------------------
  @Test
  public void testSave() {
    File earthFile = new File("res/earth.jpg");

    ImageModel model = new ImageModelImpl("earth", "res/earth.jpeg");

    this.cmd = new Save(new String[]{"save", "res/earth.jpg", "earth"});
    this.cmd.use(model);
    assertTrue(earthFile.exists());
  }

  @Test
  public void testLoad() {
    Image earthImg = new ImageImpl("res/earth.jpeg");
    this.cmd = new Load(new String[]{"load", "res/earth.jpeg", "earth"});
    this.cmd.use(this.m1);

    int[][][] actual = earthImg.getImage();
    int[][][] expected = this.m1.getImage("earth");

    for (int x = 0; x < earthImg.getIWidth(); x++) {
      for (int y = 0; y < earthImg.getIHeight(); y++) {
        assertEquals(actual[0][0][0], expected[0][0][0]);
        assertEquals(actual[0][0][1], expected[0][0][1]);
        assertEquals(actual[0][0][2], expected[0][0][2]);
      }
    }
  }

  @Test
  public void testPPMLoad() {
    Image testPPM = new ImageImpl("res/earth.ppm");
    this.cmd = new Load(new String[]{"load", "res/earth.ppm", "ppm"});
    this.cmd.use(this.m1);

    int[][][] actual = testPPM.getImage();
    int[][][] expected = this.m1.getImage("ppm");

    for (int x = 0; x < testPPM.getIWidth(); x++) {
      for (int y = 0; y < testPPM.getIHeight(); y++) {
        assertEquals(actual[0][0][0], expected[0][0][0]);
        assertEquals(actual[0][0][1], expected[0][0][1]);
        assertEquals(actual[0][0][2], expected[0][0][2]);
      }
    }
  }

  @Test
  public void testJPGLoad() {
    Image testJPG = new ImageImpl("res/cat.jpg");
    this.cmd = new Load(new String[]{"load", "res/cat.jpg", "jpg"});
    this.cmd.use(this.m1);

    int[][][] actual = testJPG.getImage();
    int[][][] expected = this.m1.getImage("jpg");

    for (int x = 0; x < testJPG.getIWidth(); x++) {
      for (int y = 0; y < testJPG.getIHeight(); y++) {
        assertEquals(actual[0][0][0], expected[0][0][0]);
        assertEquals(actual[0][0][1], expected[0][0][1]);
        assertEquals(actual[0][0][2], expected[0][0][2]);
      }
    }
  }

  @Test
  public void testPNGLoad() {
    Image testPNG = new ImageImpl("res/manhattan.png");
    this.cmd = new Load(new String[]{"load", "res/manhattan.png", "png"});
    this.cmd.use(this.m1);

    int[][][] actual = testPNG.getImage();
    int[][][] expected = this.m1.getImage("png");

    for (int x = 0; x < testPNG.getIWidth(); x++) {
      for (int y = 0; y < testPNG.getIHeight(); y++) {
        assertEquals(actual[0][0][0], expected[0][0][0]);
        assertEquals(actual[0][0][1], expected[0][0][1]);
        assertEquals(actual[0][0][2], expected[0][0][2]);
      }
    }
  }

  @Test
  public void testBMPLoad() {
    Image testBMP = new ImageImpl("res/london.bmp");
    this.cmd = new Load(new String[]{"load", "res/london.bmp", "bmp"});
    this.cmd.use(this.m1);

    int[][][] actual = testBMP.getImage();
    int[][][] expected = this.m1.getImage("bmp");

    for (int x = 0; x < testBMP.getIWidth(); x++) {
      for (int y = 0; y < testBMP.getIHeight(); y++) {
        assertEquals(actual[0][0][0], expected[0][0][0]);
        assertEquals(actual[0][0][1], expected[0][0][1]);
        assertEquals(actual[0][0][2], expected[0][0][2]);
      }
    }
  }

  @Test
  public void testPPMSave() {
    File expectedPPM = new File("res/earthNew.ppm");
    Image testPPM = new ImageImpl("res/earth.ppm");
    this.m1.loadImage(testPPM, "ppm");
    this.cmd = new Save(new String[]{"save", "res/earthNew.ppm", "ppm"});
    this.cmd.use(this.m1);
    assertTrue(expectedPPM.exists());
  }

  @Test
  public void testJPGSave() {
    File expectedJPG = new File("res/catNew.jpg");
    Image testPPM = new ImageImpl("res/cat.jpg");
    this.m1.loadImage(testPPM, "jpg");
    this.cmd = new Save(new String[]{"save", "res/catNew.jpg", "jpg"});
    this.cmd.use(this.m1);
    assertTrue(expectedJPG.exists());
  }

  @Test
  public void testPNGSave() {
    File expectedPNG = new File("res/manhattanNew.png");
    Image testPNG = new ImageImpl("res/manhattan.png");
    this.m1.loadImage(testPNG, "png");
    this.cmd = new Save(new String[]{"save", "res/manhattanNew.png", "png"});
    this.cmd.use(this.m1);
    assertTrue(expectedPNG.exists());
  }

  @Test
  public void testBMPSave() {
    File expectedBMP = new File("res/londonNew.bmp");
    Image testPNG = new ImageImpl("res/london.bmp");
    this.m1.loadImage(testPNG, "bmp");
    this.cmd = new Save(new String[]{"save", "res/londonNew.bmp", "bmp"});
    this.cmd.use(this.m1);
    assertTrue(expectedBMP.exists());
  }

  // LOAD & SAVE TESTING END ----------------------------------------------------------------------

  // FILTER TESTING START -------------------------------------------------------------------------
  @Test
  public void testBlurFilter() {
    double[][] blurFilter = {{0.0625, 0.125, 0.0625},
        {0.125, 0.25, 0.125},
        {0.0625, 0.125, 0.0625}};
    ImageCommand blur = new FilterCommand(new String[]{"blur", "dog", "dog-blurred"}, blurFilter);
    blur.use(this.m1);

    int[][][] dog = this.m1.getImage("dog");
    int[][][] actual = this.m1.getImage("dog-blurred");
    int width = actual.length;
    int height = actual[0].length;
    int[][][] expected = new int[width][height][3];
    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        for (int i = -1; i < 2; i++) {
          for (int j = -1; j < 2; j++) {
            int pixelRed;
            int pixelGreen;
            int pixelBlue;
            try {
              pixelRed = dog[x + i][y + j][0];
              pixelGreen = dog[x + i][y + j][1];
              pixelBlue = dog[x + i][y + j][2];
            } catch (IndexOutOfBoundsException e) {
              pixelRed = 0;
              pixelGreen = 0;
              pixelBlue = 0;
            }
            expected[x][y][0] += Math.round(pixelRed * blurFilter[i + 1][j + 1]);
            expected[x][y][1] += Math.round(pixelGreen * blurFilter[i + 1][j + 1]);
            expected[x][y][2] += Math.round(pixelBlue * blurFilter[i + 1][j + 1]);
          }
        }
        expected[x][y][0] = Math.max(0, Math.min(255, expected[x][y][0]));
        expected[x][y][1] = Math.max(0, Math.min(255, expected[x][y][1]));
        expected[x][y][2] = Math.max(0, Math.min(255, expected[x][y][2]));
      }
    }

    for (int x = 0; x < expected.length; x++) {
      for (int y = 0; y < expected[0].length; y++) {
        assertEquals(expected[x][y][0], actual[x][y][0]);
        assertEquals(expected[x][y][1], actual[x][y][1]);
        assertEquals(expected[x][y][2], actual[x][y][2]);
      }
    }
  }

  @Test
  public void testSharpenFilter() {
    double[][] sharpenFilter = {{-0.125, -0.125, -0.125, -0.125, -0.125},
        {-0.125, 0.25, 0.25, 0.25, -0.125},
        {-0.125, 0.25, 1, 0.25, -0.125},
        {-0.125, 0.25, 0.25, 0.25, -0.125},
        {-0.125, -0.125, -0.125, -0.125, -0.125}};
    ImageCommand blur = new FilterCommand(new String[]{"sharpen", "dog", "dog-sharpen"},
            sharpenFilter);
    blur.use(this.m1);

    int[][][] dog = this.m1.getImage("dog");
    int[][][] actual = this.m1.getImage("dog-sharpen");
    int width = actual.length;
    int height = actual[0].length;
    int[][][] expected = new int[width][height][3];
    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        for (int i = -2; i < 3; i++) {
          for (int j = -2; j < 3; j++) {
            int pixelRed;
            int pixelGreen;
            int pixelBlue;
            try {
              pixelRed = dog[x + i][y + j][0];
              pixelGreen = dog[x + i][y + j][1];
              pixelBlue = dog[x + i][y + j][2];
            } catch (IndexOutOfBoundsException e) {
              pixelRed = 0;
              pixelGreen = 0;
              pixelBlue = 0;
            }
            expected[x][y][0] += Math.round(pixelRed * sharpenFilter[i + 2][j + 2]);
            expected[x][y][1] += Math.round(pixelGreen * sharpenFilter[i + 2][j + 2]);
            expected[x][y][2] += Math.round(pixelBlue * sharpenFilter[i + 2][j + 2]);
          }
        }
        expected[x][y][0] = Math.max(0, Math.min(255, expected[x][y][0]));
        expected[x][y][1] = Math.max(0, Math.min(255, expected[x][y][1]));
        expected[x][y][2] = Math.max(0, Math.min(255, expected[x][y][2]));
      }
    }

    for (int x = 0; x < expected.length; x++) {
      for (int y = 0; y < expected[0].length; y++) {
        assertEquals(expected[x][y][0], actual[x][y][0]);
        assertEquals(expected[x][y][1], actual[x][y][1]);
        assertEquals(expected[x][y][2], actual[x][y][2]);
      }
    }
  }

  @Test
  public void testSepiaFilter() {
    this.c1 = new ComponentCommand(new String[]{"sepia", "dog", "dog-sepia"},
        (int[] x) -> new int[]{
            (int) ((0.393 * x[0]) + (0.769 * x[1]) + (0.189 * x[2])),
            (int) ((0.349 * x[0]) + (0.686 * x[1]) + (0.168 * x[2])),
            (int) ((0.272 * x[0]) + (0.534 * x[1]) + (0.131 * x[2]))});
    this.c1.use(this.m1);

    int[][][] original = this.m1.getImage("dog");
    int[][][] actual = this.m1.getImage("dog-sepia");
    int width = actual.length;
    int height = actual[0].length;
    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        int red = Math.max(0, Math.min(255, (int) ((0.393 * original[x][y][0])
                + (0.769 * original[x][y][1]) + (0.189 * original[x][y][2]))));
        int green = Math.max(0, Math.min(255, (int) ((0.349 * original[x][y][0])
                + (0.686 * original[x][y][1]) + (0.168 * original[x][y][2]))));
        int blue = Math.max(0, Math.min(255, (int) ((0.272 * original[x][y][0])
                + (0.534 * original[x][y][1]) + (0.131 * original[x][y][2]))));
        assertEquals(red, actual[x][y][0]);
        assertEquals(green, actual[x][y][1]);
        assertEquals(blue, actual[x][y][2]);
      }
    }
  }

  @Test
  public void testGreyscaleFilter() {
    this.c1 = new ComponentCommand(new String[]{"greyscale", "dog", "dog-greyscale"},
        (int[] x) -> new int[]{
            (int)((0.2126 * x[0]) + (0.7152 * x[1]) + (0.0722 * x[2])),
            (int)((0.2126 * x[0]) + (0.7152 * x[1]) + (0.0722 * x[2])),
            (int)((0.2126 * x[0]) + (0.7152 * x[1]) + (0.0722 * x[2]))});
    this.c1.use(this.m1);

    int[][][] original = this.m1.getImage("dog");
    int[][][] actual = this.m1.getImage("dog-greyscale");
    int width = actual.length;
    int height = actual[0].length;
    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        int value = (int) ((0.2126 * original[x][y][0]) + (0.7152 * original[x][y][1]) +
                (0.0722 * original[x][y][2]));
        assertEquals(value, actual[x][y][0]);
        assertEquals(value, actual[x][y][1]);
        assertEquals(value, actual[x][y][2]);
      }
    }
  }

  // FILTER TESTING END ---------------------------------------------------------------------------

  // ERROR MESSAGE TESTING START ------------------------------------------------------------------
  @Test
  public void testErrorMessageInvalidCommand() {
    try {
      ImageCommand load = new Load(new String[]{"load", "res/earth.jpeg"});
    } catch (IllegalStateException e) {
      assertEquals("Invalid command length: 2", e.getMessage());
    }
    try {
      ImageCommand save = new Save(new String[]{"save", "res/earth.jpeg"});
    } catch (IllegalStateException e) {
      assertEquals("Invalid command length: 2", e.getMessage());
    }
    try {
      ImageCommand brighten = new Brightness(new String[]{"brighten", "earth", "earth-bright"});
    } catch (IllegalStateException e) {
      assertEquals("Invalid command length: 3", e.getMessage());
    }
    try {
      ImageCommand verticalFlip = new VerticalFlipCommand(
              new String[]{"vertical-flip", "earth"});
    } catch (IllegalStateException e) {
      assertEquals("Invalid command length: 2", e.getMessage());
    }
  }

  @Test
  public void testErrorMessageInvalidImage() {
    ImageModel m2 = new ImageModelImpl("earth", "res/earth.jpeg");

    this.cmd = new Save(new String[]{"save", "res/earth.jpg", "ananab"});
    try {
      this.cmd.use(m2);
    } catch (IllegalStateException e) {
      assertEquals("Image ananab does not exist", e.getMessage());
    }
  }

  // -- Filter Commands --
  @Test
  public void testBlurFilterInvalid() {
    try {
      ImageCommand filterCommand = new FilterCommand(new String[]{"blur", "dog", "dog-blurred"},
              new double[][]{{0.0625, 0.125, 0.0625}, {0.125, 0.25, 0.125}});
    } catch (IllegalStateException e) {
      assertEquals("Invalid filter, length: 2", e.getMessage());
    }
    try {
      ImageCommand filterCommand = new FilterCommand(new String[]{"blur", "dog", "dog-blurred"},
              new double[][]{{0.0625, 0.125, 0.0625}, {0.125, 0.25, 0.125},
                  {0.0625, 0.125, 0.0625}, {0.125, 0.25, 0.125}});
    } catch (IllegalStateException e) {
      assertEquals("Invalid filter, length: 4", e.getMessage());
    }
  }

  @Test
  public void testSharpenFilterInvalid() {
    try {
      ImageCommand filterCommand = new FilterCommand(new String[]{"sharpen", "dog", "dog-sharpen"},
              new double[][]{{-0.125, -0.125, -0.125, -0.125, -0.125},
                  {-0.125, 0.25, 0.25, 0.25, -0.125},
                  {-0.125, 0.25, 1, 0.25, -0.125},
                  {-0.125, 0.25, 0.25, 0.25, -0.125}});
    } catch (IllegalStateException e) {
      assertEquals("Invalid filter, length: 4", e.getMessage());
    }
    try {
      ImageCommand filterCommand = new FilterCommand(new String[]{"sharpen", "dog", "dog-sharpen"},
              new double[][]{{-0.125, -0.125, -0.125, -0.125, -0.125},
                  {-0.125, 0.25, 0.25, 0.25, -0.125}});
    } catch (IllegalStateException e) {
      assertEquals("Invalid filter, length: 2", e.getMessage());
    }
  }

  // -- Component Commands --
  @Test
  public void testRedComponentErrorMessage() {
    try {
      this.c1 = new ComponentCommand(new String[]{"red-component", "dog"},
          (int[] x) -> new int[]{x[0], x[0], x[0]});
    } catch (IllegalStateException e) {
      assertEquals("Invalid number of arguments: 2", e.getMessage());
    }
    try {
      this.c1 = new ComponentCommand(new String[]{"red-component", "foo", "dog-foo"},
          (int[] x) -> new int[]{x[0], x[0], x[0]});
      this.c1.use(this.m1);
    } catch (IllegalStateException e) {
      assertEquals("Image foo does not exist", e.getMessage());
    }
  }

  @Test
  public void testValueComponentErrorMessage() {
    try {
      this.c1 = new ComponentCommand(new String[]{"value-component", "dog"},
          (int[] x) -> new int[]{Math.max(x[0], Math.max(x[1], x[2])),
                      Math.max(x[0], Math.max(x[1], x[2])),
                      Math.max(x[0], Math.max(x[1], x[2]))});
    } catch (IllegalStateException e) {
      assertEquals("Invalid number of arguments: 2", e.getMessage());
    }
    try {
      this.c1 = new ComponentCommand(new String[]{"value-component", "foo", "dog-foo"},
          (int[] x) -> new int[]{Math.max(x[0], Math.max(x[1], x[2])),
                      Math.max(x[0], Math.max(x[1], x[2])),
                      Math.max(x[0], Math.max(x[1], x[2]))});
      this.c1.use(this.m1);
    } catch (IllegalStateException e) {
      assertEquals("Image foo does not exist", e.getMessage());
    }
  }


  // -- Flipping Commands --
  @Test
  public void testFlippedHorizontalError() {
    try {
      this.cmd = new HorizontalFlipCommand(new String[]{"horizontal-flip",
          "earth"});
    } catch (IllegalStateException e) {
      assertEquals("Invalid command length: 2", e.getMessage());
    }
    try {
      this.cmd = new HorizontalFlipCommand(new String[]{"horizontal-flip", "notADog",
          "dog-flipped"});
      this.cmd.use(this.m1);
    } catch (IllegalStateException e) {
      assertEquals("Image notADog does not exist", e.getMessage());
    }
  }

  @Test
  public void testFlippedVerticalError() {
    try {
      this.cmd = new VerticalFlipCommand(new String[]{"vertical-flip",
          "earth"});
    } catch (IllegalStateException e) {
      assertEquals("Invalid command length: 2", e.getMessage());
    }
    try {
      this.cmd = new VerticalFlipCommand(new String[]{"vertical-flip", "notADog", "dog-flipped"});
      this.cmd.use(this.m1);
    } catch (IllegalStateException e) {
      assertEquals("Image notADog does not exist", e.getMessage());
    }
  }

  // ERROR MESSAGE TESTING END --------------------------------------------------------------------

  @Test
  public void testMultipleCommandsSave() {
    File earthTestFile = new File("res/earthBright.png");
    ImageCommand load1 = new Load(new String[]{"load", "res/earth.jpeg", "earthTest"});
    ImageCommand brighten1 = new Brightness(new String[]{"brighten", "10", "earthTest",
        "earthBright"});
    ImageCommand save1 = new Save(new String[]{"save", "res/earthBright.png", "earthBright"});
    load1.use(m1);
    brighten1.use(m1);
    save1.use(m1);
    assertTrue(earthTestFile.exists());
  }

  // PPM & BMP COMMAND TESTING --------------------------------------------------------------------
  @Test
  public void testPPMBlur() {
    double[][] blurFilter = {{0.0625, 0.125, 0.0625},
        {0.125, 0.25, 0.125},
        {0.0625, 0.125, 0.0625}};
    ImageCommand blur = new FilterCommand(new String[]{"blur", "earthPPM", "earthPPM-blurred"},
            blurFilter);
    blur.use(this.m1);

    int[][][] dog = this.m1.getImage("earthPPM");
    int[][][] actual = this.m1.getImage("earthPPM-blurred");
    int width = actual.length;
    int height = actual[0].length;
    int[][][] expected = new int[width][height][3];
    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        for (int i = -1; i < 2; i++) {
          for (int j = -1; j < 2; j++) {
            int pixelRed;
            int pixelGreen;
            int pixelBlue;
            try {
              pixelRed = dog[x + i][y + j][0];
              pixelGreen = dog[x + i][y + j][1];
              pixelBlue = dog[x + i][y + j][2];
            } catch (IndexOutOfBoundsException e) {
              pixelRed = 0;
              pixelGreen = 0;
              pixelBlue = 0;
            }
            expected[x][y][0] += Math.round(pixelRed * blurFilter[i + 1][j + 1]);
            expected[x][y][1] += Math.round(pixelGreen * blurFilter[i + 1][j + 1]);
            expected[x][y][2] += Math.round(pixelBlue * blurFilter[i + 1][j + 1]);
          }
        }
        expected[x][y][0] = Math.max(0, Math.min(255, expected[x][y][0]));
        expected[x][y][1] = Math.max(0, Math.min(255, expected[x][y][1]));
        expected[x][y][2] = Math.max(0, Math.min(255, expected[x][y][2]));
      }
    }

    for (int x = 0; x < expected.length; x++) {
      for (int y = 0; y < expected[0].length; y++) {
        assertEquals(expected[x][y][0], actual[x][y][0]);
        assertEquals(expected[x][y][1], actual[x][y][1]);
        assertEquals(expected[x][y][2], actual[x][y][2]);
      }
    }
  }

  @Test
  public void testBMPSharpen() {
    double[][] sharpenFilter = {{-0.125, -0.125, -0.125, -0.125, -0.125},
        {-0.125, 0.25, 0.25, 0.25, -0.125},
        {-0.125, 0.25, 1, 0.25, -0.125},
        {-0.125, 0.25, 0.25, 0.25, -0.125},
        {-0.125, -0.125, -0.125, -0.125, -0.125}};
    ImageCommand blur = new FilterCommand(new String[]{"sharpen", "london", "london-sharp"},
            sharpenFilter);
    blur.use(this.m1);

    int[][][] london = this.m1.getImage("london");
    int[][][] actual = this.m1.getImage("london-sharp");
    int width = actual.length;
    int height = actual[0].length;
    int[][][] expected = new int[width][height][3];
    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        for (int i = -2; i < 3; i++) {
          for (int j = -2; j < 3; j++) {
            int pixelRed;
            int pixelGreen;
            int pixelBlue;
            try {
              pixelRed = london[x + i][y + j][0];
              pixelGreen = london[x + i][y + j][1];
              pixelBlue = london[x + i][y + j][2];
            } catch (IndexOutOfBoundsException e) {
              pixelRed = 0;
              pixelGreen = 0;
              pixelBlue = 0;
            }
            expected[x][y][0] += Math.round(pixelRed * sharpenFilter[i + 2][j + 2]);
            expected[x][y][1] += Math.round(pixelGreen * sharpenFilter[i + 2][j + 2]);
            expected[x][y][2] += Math.round(pixelBlue * sharpenFilter[i + 2][j + 2]);
          }
        }
        expected[x][y][0] = Math.max(0, Math.min(255, expected[x][y][0]));
        expected[x][y][1] = Math.max(0, Math.min(255, expected[x][y][1]));
        expected[x][y][2] = Math.max(0, Math.min(255, expected[x][y][2]));
      }
    }

    for (int x = 0; x < expected.length; x++) {
      for (int y = 0; y < expected[0].length; y++) {
        assertEquals(expected[x][y][0], actual[x][y][0]);
        assertEquals(expected[x][y][1], actual[x][y][1]);
        assertEquals(expected[x][y][2], actual[x][y][2]);
      }
    }
  }

  @Test
  public void testBMPGreyscale() {
    this.c1 = new ComponentCommand(new String[]{"greyscale", "london", "london-greyscale"},
        (int[] x) -> new int[]{
            (int)((0.2126 * x[0]) + (0.7152 * x[1]) + (0.0722 * x[2])),
            (int)((0.2126 * x[0]) + (0.7152 * x[1]) + (0.0722 * x[2])),
            (int)((0.2126 * x[0]) + (0.7152 * x[1]) + (0.0722 * x[2]))});
    this.c1.use(this.m1);

    int[][][] original = this.m1.getImage("london");
    int[][][] actual = this.m1.getImage("london-greyscale");
    int width = actual.length;
    int height = actual[0].length;
    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        int value = (int) ((0.2126 * original[x][y][0]) + (0.7152 * original[x][y][1]) +
                (0.0722 * original[x][y][2]));
        assertEquals(value, actual[x][y][0]);
        assertEquals(value, actual[x][y][1]);
        assertEquals(value, actual[x][y][2]);
      }
    }
  }

  @Test
  public void testPPMLoadJPGSave() {
    this.cmd = new Load(new String[]{"load", "res/earth.ppm", "ppm"});
    this.cmd.use(this.m1);
    this.cmd = new Save(new String[]{"save", "res/earthPPM.jpg", "ppm"});
    this.cmd.use(this.m1);

    File expectedJPG = new File("res/earthPPM.jpg");
    assertTrue(expectedJPG.exists());
  }

  @Test
  public void testBMPLoadPPMSave() {
    this.cmd = new Load(new String[]{"load", "res/london.bmp", "bmp"});
    this.cmd.use(this.m1);
    this.cmd = new Save(new String[]{"save", "res/londonBMP.ppm", "bmp"});
    this.cmd.use(this.m1);

    File expectedJPG = new File("res/londonBMP.ppm");
    assertTrue(expectedJPG.exists());
  }
}