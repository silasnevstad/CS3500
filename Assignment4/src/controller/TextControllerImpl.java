package controller;

import java.io.InputStreamReader;
import java.util.Scanner;

import model.ImageModel;
import view.GenericView;

/**
 * The class representing the asynchronous controller for the text view.
 */
public class TextControllerImpl extends AbstractController {

  /**
   * Constructs an {@code TextControllerImpl} object with a given {@code ImageModel},
   * {@code GenericView}, a Readable object and an Appendable object.
   *
   * @param model an ImageModel representing the model.
   * @param view  a GenericView representing the view.
   * @param input a Readable object representing the input to the controller.
   * @param output an Appendable object representing the output of the controller.
   */
  public TextControllerImpl(ImageModel model, GenericView view, Readable input,
                            Appendable output) throws IllegalArgumentException {
    super(model, view, input, output);

  }

  /**
   * Constructs an {@code TextControllerImpl} object with a given {@code ImageModel}
   * and {@code GenericView}, and default input and output values.
   *
   * @param model an ImageModel representing the model.
   * @param view  a GenericView representing the view.
   */
  public TextControllerImpl(ImageModel model, GenericView view) {
    super(model, view, new InputStreamReader(System.in), System.out);
  }

  @Override
  public void start() throws IllegalStateException {
    // Load all the commands to the commands HashMap
    loadCommands();
    Scanner checker = new Scanner(this.input);
    boolean quit = false;

    transmitRender();
    while (!quit && checker.hasNextLine()) {
      String status;

      status = processCommand(checker);

      if (status.contains("quit")) {
        quit = true;
      } else {
        transmit(status);
        transmitRender();
      }
    }
    transmit("Program quit successfully!");
  }
}
