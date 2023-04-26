package view;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;


/**
 * This class represents testing for the text graphics class.
 */
public class TextGraphicsTest {
  private TextGraphics view;
  StringBuilder log;

  @Before
  public void init() {
    //ImageModelImpl model = new ImageModelImpl("res/earth.jpeg", "earth"); // UNUSED
    this.log = new StringBuilder();
    this.view = new TextGraphics(this.log);
  }

  @Test
  public void testErrorInvalidInput() {
    try {
      TextGraphics view = new TextGraphics(null);
    } catch (IllegalStateException e) {
      assertEquals("Given argument cannot be null.", e.getMessage());
    }
  }

  @Test
  public void testRender() throws IOException {
    this.view.render();
    String expected = "Images available:\n" +
            "Next operation: \n";
    assertEquals(expected, this.log.toString());
  }

  @Test
  public void testRenderMesage() throws IOException {
    this.view.renderMessage("testing...");
    assertEquals("testing...", this.log.toString());
  }

  @Test
  public void testUpdate() throws IOException {
    this.view.update("res/earth.jpeg");
    this.view.render();
    String expected = "Images available:\n"
            + "res/earth.jpeg\n"
            + "Next operation: \n";
    assertEquals(expected, this.log.toString());
  }

  @Test
  public void testUpdateMultipleFiles() throws IOException {
    this.view.update("res/london.bmp");
    this.view.update("res/earth.jpeg");
    this.view.update("res/earth.ppm");
    this.view.render();
    String expected = "Images available:\n" +
            "res/london.bmp\n" +
            "res/earth.jpeg\n" +
            "res/earth.ppm\n" +
            "Next operation: \n";
    assertEquals(expected, this.log.toString());
  }
}