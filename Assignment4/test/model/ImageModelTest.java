package model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Tests for {@code ImageModel} methods and it's implementations, in this case ImageModelImpl.
 */
public class ImageModelTest {
  ImageModel model;

  @Before
  public void init() {
    this.model = new ImageModelImpl();
    ImageImpl dogImg = new ImageImpl("res/dog.jpeg");
    this.model.loadImage(dogImg, "dog");
  }

  @Test
  public void testSecondConstructor() {
    this.model = new ImageModelImpl("dog", "res/dog.jpeg");
    this.model = new ImageModelImpl("earthPPM", "res/earth.ppm");
    this.model = new ImageModelImpl("earth", "res/earth.jpeg");
    assertEquals(225, this.model.getImageHeight("earth"));
  }

  @Test
  public void testSecondConstructorInvalidFile() {
    try {
      this.model = new ImageModelImpl("porcupine", "res/porcupine.jpeg");
      fail("Model took a nonexistent image and went with it");
    } catch (IllegalArgumentException e) {
      assertEquals("Error: Invalid File", e.getMessage());
    }
  }

  @Test
  public void testGetImageWidth() {
    assertEquals(720, this.model.getImageWidth("dog"));
  }

  @Test
  public void testGetImageHeight() {
    assertEquals(479, this.model.getImageHeight("dog"));
  }

  @Test
  public void testGetImage() {
    assertEquals(3, this.model.getImage("dog")[0][0].length);
    assertEquals(479, this.model.getImage("dog")[0].length);
    assertEquals(720, this.model.getImage("dog").length);
  }

  @Test
  public void testGetImageFail() {
    ImageModelImpl m = new ImageModelImpl();
    try {
      m.getImage("dog");
      fail("Model successfully retrieved a nonexistent image");
    } catch (IllegalStateException e) {
      assertEquals("Image dog does not exist", e.getMessage());
    }
  }
}
