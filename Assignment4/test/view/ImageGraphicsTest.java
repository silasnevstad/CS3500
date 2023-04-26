package view;

import org.junit.Before;
import org.junit.Test;

import controller.MockView;
import model.ImageModelImpl;

import static org.junit.Assert.assertEquals;

/**
 * This class represents testing for the image graphics class.
 */
public class ImageGraphicsTest {
  private MockView view;
  StringBuilder log;

  @Before
  public void init() {
    ImageModelImpl model = new ImageModelImpl("earth", "res/earth.jpeg");
    this.log = new StringBuilder();
    this.view = new MockView(this.log, "load res/earth.jpeg earth");
  }

  @Test
  public void testGetCommand() {
    assertEquals("load res/earth.jpeg earth", this.view.getCommand());
  }
}