package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.StringReader;
import java.util.Scanner;

import model.ImageModel;
import view.ImageView;

/**
 * This class represents the controller and implements the ImageController interface,
 * as well as, the ActionLister interface. It takes a model, view and user input to
 * perform operations.
 */
public class ImageControllerImpl extends AbstractController implements ActionListener {

  /**
   * Constructs an {@code ImageControllerImpl} object with a given {@code ImageModel}
   * and {@code ImageView}, input and output values.
   *
   * @param model an ImageModel representing the model.
   * @param view  an ImageView representing the view.
   * @param input the given input
   * @param output the given output
   */
  public ImageControllerImpl(ImageModel model, ImageView view, Readable input, Appendable output)
          throws IllegalArgumentException {
    super(model, view, input, output); // This guarantees that view will be an ImageView
  }

  /**
   * Constructs an {@code ImageControllerImpl} object with a given {@code ImageModel}
   * and {@code ImageView}, and default input and output values.
   *
   * @param model an ImageModel representing the model.
   * @param view  an ImageView representing the view.
   */
  public ImageControllerImpl(ImageModel model, ImageView view) {
    super(model, view, new StringReader(""), System.out);
  }

  @Override
  public void start() throws IllegalStateException {
    //put the commands in the controller
    loadCommands();

    // INVARIANT: by constructor definition, view must be an ImageView.
    //((ImageView) (this.view)).setCommandButtonListener(this);
    ((ImageView) (this.view)).setCommandCallBack(this::accept);
    try {
      this.view.render();
      String status = processCommand(new Scanner(this.input));
      transmit(status);
      this.output.append(status);
    } catch (IOException e) {
      throw new IllegalStateException("Error: Output is not functional");
    }
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    // INVARIANT: by constructor definition, view must be an ImageView.
    this.input = new StringReader(((ImageView) (this.view)).getCommand());
    runOrError(new Scanner(this.input));
  }

  private void accept(String input) {
    runOrError(new Scanner(input));
  }

  private void runOrError(Scanner scan) {
    try {
      String status = processCommand(scan);
      transmit(status);
      this.output.append(status);
    } catch (Exception ex) {
      // INVARIANT: by constructor definition, view must be an ImageView.
      ((ImageView) (this.view)).showErrorMessage(ex.getMessage());
    }
  }
}