package controller;

import org.junit.Before;
import org.junit.Test;

import java.io.StringReader;

import model.ImageImpl;
import model.ImageModelImpl;
import view.TextGraphics;

import static org.junit.Assert.assertEquals;

/**
 * This class represents testing for the text controller class.
 */
public class TextControllerTest {
  private ImageModelImpl model;
  private TextGraphics view;
  private StringReader rd;
  private StringBuilder log;
  private TextControllerImpl controller;

  @Before
  public void init() {
    ImageImpl img = new ImageImpl("res/dog.jpeg");
    this.log = new StringBuilder();
    this.model = new ImageModelImpl();
    this.model.loadImage(img, "dog");
    this.view = new TextGraphics(this.log);
    this.rd = new StringReader("load res/earth.jpeg earth");
    this.controller = new TextControllerImpl(this.model, this.view, this.rd, this.log);
  }

  @Test
  public void testConstructor() {
    try {
      TextControllerImpl con = new TextControllerImpl(null, this.view, this.rd, this.log);
    } catch (IllegalArgumentException e) {
      assertEquals("Model cannot be null", e.getMessage());
    }
    try {
      TextControllerImpl con = new TextControllerImpl(this.model, null, this.rd, this.log);
    } catch (IllegalArgumentException e) {
      assertEquals("View cannot be null", e.getMessage());
    }
    try {
      TextControllerImpl con = new TextControllerImpl(this.model, this.view, null, this.log);
    } catch (IllegalArgumentException e) {
      assertEquals("Input cannot be null", e.getMessage());
    }
    try {
      TextControllerImpl con = new TextControllerImpl(this.model, this.view, this.rd, null);
    } catch (IllegalArgumentException e) {
      assertEquals("Output cannot be null", e.getMessage());
    }
  }

  @Test
  public void testSingleCommand() {
    this.controller.start();
    String expected = "Images available:\n" +
            "Next operation: \n" +
            "Successfully executed: load res/earth.jpeg earth\n" +
            "Images available:\n" +
            "earth\n" +
            "Next operation: \n" +
            "Program quit successfully!";
    assertEquals(expected, this.log.toString());
  }

  @Test
  public void testLoadSaveCommands() {
    this.rd = new StringReader("load res/earth.jpeg earth \nsave res/earthTest.ppm earth");
    this.controller = new TextControllerImpl(this.model, this.view, this.rd, this.log);
    this.controller.start();
    String expected = "Images available:\n" +
            "Next operation: \n" +
            "Successfully executed: load res/earth.jpeg earth \n" +
            "Images available:\n" +
            "earth\n" +
            "Next operation: \n" +
            "Successfully executed: save res/earthTest.ppm earth\n" +
            "Images available:\n" +
            "earth\n" +
            "Next operation: \n" +
            "Program quit successfully!";
    assertEquals(expected, this.log.toString());
  }

  @Test
  public void testLoadMultipleImages() {
    this.rd = new StringReader("load res/earth.jpeg earth\n"
            + "load res/london.bmp london\n"
            + "load res/earth.ppm earth");
    this.controller = new TextControllerImpl(this.model, this.view, this.rd, this.log);
    this.controller.start();
    String expected = "Images available:\n" +
            "Next operation: \n" +
            "Successfully executed: load res/earth.jpeg earth\n" +
            "Images available:\n" +
            "earth\n" +
            "Next operation: \n" +
            "Successfully executed: load res/london.bmp london\n" +
            "Images available:\n" +
            "london\n" +
            "earth\n" +
            "Next operation: \n" +
            "Successfully executed: load res/earth.ppm earth\n" +
            "Images available:\n" +
            "london\n" +
            "earth\n" +
            "Next operation: \n" +
            "Program quit successfully!";
    assertEquals(expected, this.log.toString());
  }
}
