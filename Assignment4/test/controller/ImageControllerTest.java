package controller;

import org.junit.Before;
import org.junit.Test;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.StringReader;

import javax.swing.JButton;

import model.ImageModel;
import model.ImageModelImpl;
import view.ImageGraphics;
import view.ImageView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * The testing class for the {@code ImageControllerImpl} methods.
 */
public class ImageControllerTest {
  ImageModel model;
  Readable rd;
  ImageView view;
  ImageController controller;

  StringBuilder log;

  @Before
  public void init() {
    this.model = new ImageModelImpl();
    this.view = new ImageGraphics(this.model);
    this.rd = new StringReader("load res/earth.jpeg earth\n"
            + "brighten -10 earth earthDark\n"
            + "save res/earthDark.ppm earthDark");
    this.log = new StringBuilder();
    this.controller = new ImageControllerImpl(model, view, rd, log);
  }

  @Test
  public void testConstructor() {
    Readable r = new StringReader("load res/earth.jpeg earth");
    this.controller = new ImageControllerImpl(model, view, r, log);
    this.controller.start();
    assertEquals("Successfully executed: load res/earth.jpeg earth\n",
            this.log.toString());
  }

  @Test
  public void testConstructorNullModel() {
    try {
      this.controller = new ImageControllerImpl(null, view, rd, log);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Model cannot be null", e.getMessage());
    }
  }

  @Test
  public void testConstructorNullView() {
    try {
      this.controller = new ImageControllerImpl(model, null, rd, log);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("View cannot be null", e.getMessage());
    }
  }

  @Test
  public void testConstructorNullReadable() {
    try {
      this.controller = new ImageControllerImpl(model, view, null, log);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Input cannot be null", e.getMessage());
    }
  }

  @Test
  public void testConstructorNullAppendable() {
    try {
      this.controller = new ImageControllerImpl(model, view, rd, null);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Output cannot be null", e.getMessage());
    }
  }

  @Test
  public void testSecondConstructorNullModel() {
    try {
      this.controller = new ImageControllerImpl(null, view);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Model cannot be null", e.getMessage());
    }
  }

  @Test
  public void testSecondConstructorNullView() {
    try {
      this.controller = new ImageControllerImpl(model, null);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("View cannot be null", e.getMessage());
    }
  }

  @Test
  public void testStart() {
    this.controller.start();
    String[] logs = this.log.toString().split("\n");
    assertEquals("Successfully executed: load res/earth.jpeg earth", logs[0]);
    int[][][] sample = this.model.getImage("earth");
    assertEquals(225, sample.length);
    assertEquals(225, sample[0].length);
  }

  @Test
  public void testActionPerformedEmpty() {
    StringBuilder viewLog = new StringBuilder();
    this.controller = new ImageControllerImpl(this.model, new MockView(viewLog, ""),
            new StringReader(""), this.log);
    JButton test = new JButton();
    test.addActionListener((ActionListener) this.controller);
    test.doClick();
    assertEquals("", this.log.toString());
  }

  @Test
  public void testActionPerformedValid() {
    StringBuilder viewLog = new StringBuilder();
    this.controller = new ImageControllerImpl(this.model, new MockView(viewLog,
            "load res/earth.jpeg earth"),
            new StringReader(""), this.log);
    this.controller.start(); //set up available commands
    JButton test = new JButton();
    test.addActionListener((ActionListener) this.controller);
    test.doClick();
    assertEquals("Successfully executed: load res/earth.jpeg earth\n",
            this.log.toString());
  }

  @Test
  public void testActionPerformedInvalid() {
    StringBuilder viewLog = new StringBuilder();
    this.controller = new ImageControllerImpl(this.model,
            new MockView(viewLog, "This is a test"),
            new StringReader(""), this.log);
    this.controller.start(); //set up available commands
    JButton test = new JButton();
    test.addActionListener((ActionListener) this.controller);
    test.doClick();
    assertEquals("Failed: This is a test\n", this.log.toString());
  }

  @Test
  public void testValidInputSetImage() {
    StringBuilder viewLog = new StringBuilder();
    MockView mockV = new MockView(viewLog, "");
    this.controller = new ImageControllerImpl(this.model, mockV,
            new StringReader("load res/earth.jpeg earth"), this.log);
    this.controller.start();

    assertEquals("Successfully executed: load res/earth.jpeg earth\n",
            this.log.toString());
  }

  @Test
  public void testValidShowErrorMessage() {
    StringBuilder viewLog = new StringBuilder();
    this.controller = new ImageControllerImpl(this.model, new MockView(viewLog, "hi"),
            new StringReader(""), this.log);
    this.controller.start();
    JButton test = new JButton();
    test.addActionListener((ActionListener) this.controller);
    test.doClick();
    assertEquals("Failed: hi\n", this.log.toString());
  }

  @Test
  public void testProcessCommand() {
    this.controller.start();
    String[] logs = this.log.toString().split("\n");
    assertEquals("Successfully executed: load res/earth.jpeg earth", logs[0]);
  }

  @Test
  public void testProcessCommandFakeCommand() {
    this.rd = new StringReader("animate res/earth.jpeg");
    this.controller = new ImageControllerImpl(this.model, this.view, this.rd, this.log);
    this.controller.start();
    assertEquals("Failed: animate res/earth.jpeg\n", this.log.toString());
  }

  @Test
  public void testProcessCommandFakeImage() {
    this.rd = new StringReader("red-component earth earth-red");
    this.controller = new ImageControllerImpl(this.model, this.view, this.rd, this.log);
    try {
      this.controller.start();
    } catch (IllegalStateException e) {
      assertEquals("Image earth does not exist", e.getMessage());
    }
  }

  @Test
  public void testProcessCommandFakeFile() {
    this.rd = new StringReader("load res/porcupine.ppm porcupine");
    this.controller = new ImageControllerImpl(this.model, this.view, this.rd, this.log);
    try {
      this.controller.start();
    } catch (IllegalStateException e) {
      assertEquals("File not found.", e.getMessage());
    }
  }

  @Test
  public void testValidInputGetImage() {
    StringBuilder mLog = new StringBuilder();
    MockModel mockM = new MockModel(mLog);
    this.controller = new ImageControllerImpl(mockM, new ImageGraphics(mockM),
            new StringReader("load res/earth.jpeg earth"), this.log);
    this.controller.start();

    assertEquals("earth", mLog.toString());
  }

  @Test
  public void testMockControllerInputs() {
    StringBuilder log = new StringBuilder();
    MockView view = new MockView(new StringBuilder(), "load res/dog.jpeg dog");
    MockController mock = new MockController(log, view);
    mock.start();
    assertEquals("load res/dog.jpeg dog", log.toString());
    assertEquals("load res/dog.jpeg dog", mock.processCommand(null));

    mock.actionPerformed(new ActionEvent(this, 1, ""));
    String[] output = log.toString().split("load ");
    assertEquals("res/dog.jpeg dog", output[1]);
  }
}
