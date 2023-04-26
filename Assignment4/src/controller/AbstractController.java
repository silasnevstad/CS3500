package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;

import controller.commands.Brightness;
import controller.commands.ComponentCommand;
import controller.commands.FilterCommand;
import controller.commands.HorizontalFlipCommand;
import controller.commands.Load;
import controller.commands.Resize;
import controller.commands.Save;
import controller.commands.VerticalFlipCommand;
import model.ImageModel;
import view.GenericView;

/**
 * This class represents the abstract methods of the controller, i.e., methods that would be
 * shared by any controller. An example of this would be how to process a command,
 * and how to load them.
 */
public abstract class AbstractController implements ImageController {
  protected final Map<String, Function<String[], ImageCommand>> commands;
  protected ImageModel model;
  protected GenericView view;
  protected Readable input;
  protected Appendable output;

  /**
   * Constructs an {@code AbstractController} object with a given ImageModel, GenericView,
   * Appendable output, and Readable input.
   *
   * @param model  an ImageModel representing the model
   * @param view   a view relevant to the specific controller type
   * @param input  a Readable object representing the user's input
   * @param output an Appendable representing the output for the program
   */
  AbstractController(ImageModel model, GenericView view, Readable input, Appendable output) {
    if (model == null) {
      throw new IllegalArgumentException("Model cannot be null");
    } else if (view == null) {
      throw new IllegalArgumentException("View cannot be null");
    } else if (input == null) {
      throw new IllegalArgumentException("Input cannot be null");
    } else if (output == null) {
      throw new IllegalArgumentException("Output cannot be null");
    }
    this.model = model;
    this.view = view;
    this.input = input;
    this.output = output;
    this.commands = new HashMap<>();
  }

  @Override
  public String processCommand(Scanner scan) throws IllegalStateException {
    StringBuilder output = new StringBuilder();

    if (scan.hasNextLine()) {
      String command = scan.nextLine();
      String[] tokens = command.split(" ");
      ImageCommand cmd;
      if (this.commands.get(tokens[0]) != null) { // if it is a valid command
        cmd = this.commands.get(tokens[0]).apply(tokens);
      } else {
        output.append("Failed: " + command + "\n");
        return output.toString();
      }

      if (cmd != null) {
        String destFile = cmd.use(model);
        output.append("Successfully executed: " + command + "\n");
        this.view.update(destFile);
      }
    }
    return output.toString();
  }

  /**
   * Loads the currently supported commands into the command list.
   */
  protected void loadCommands() {
    this.commands.put("load", Load::new);
    this.commands.put("save", Save::new);
    this.commands.put("brighten", Brightness::new);
    this.commands.put("vertical-flip", VerticalFlipCommand::new);
    this.commands.put("horizontal-flip", HorizontalFlipCommand::new);
    this.commands.put("red-component", s -> new ComponentCommand(s,
        (int[] x) -> new int[]{x[0], x[0], x[0]}));
    this.commands.put("green-component", s -> new ComponentCommand(s,
        (int[] x) -> new int[]{x[1], x[1], x[1]}));
    this.commands.put("blue-component", s -> new ComponentCommand(s,
        (int[] x) -> new int[]{x[2], x[2], x[2]}));
    this.commands.put("value-component", s -> new ComponentCommand(s, (int[] x) -> new int[]{
            Math.max(x[0], Math.max(x[1], x[2])),
            Math.max(x[0], Math.max(x[1], x[2])),
            Math.max(x[0], Math.max(x[1], x[2]))}));
    this.commands.put("intensity-component", s -> new ComponentCommand(s, (int[] x) -> new int[]{
        ((x[0] + x[1] + x[2]) / 3),
        ((x[0] + x[1] + x[2]) / 3),
        ((x[0] + x[1] + x[2]) / 3)}));
    Function<int[], int[]> grey = (int[] x) -> new int[]{
        (int) ((0.2126 * x[0]) + (0.7152 * x[1]) + (0.0722 * x[2])),
        (int) ((0.2126 * x[0]) + (0.7152 * x[1]) + (0.0722 * x[2])),
        (int) ((0.2126 * x[0]) + (0.7152 * x[1]) + (0.0722 * x[2]))};
    this.commands.put("luma-component", s -> new ComponentCommand(s, grey));
    this.commands.put("greyscale", s -> new ComponentCommand(s, grey));
    this.commands.put("sepia", s -> new ComponentCommand(s, (int[] x) -> new int[]{
        (int) ((0.393 * x[0]) + (0.769 * x[1]) + (0.189 * x[2])),
        (int) ((0.349 * x[0]) + (0.686 * x[1]) + (0.168 * x[2])),
        (int) ((0.272 * x[0]) + (0.534 * x[1]) + (0.131 * x[2]))}));
    this.commands.put("blur", s -> new FilterCommand(s, new double[][]{
            {0.0625, 0.125, 0.0625},
            {0.125, 0.25, 0.125},
            {0.0625, 0.125, 0.0625}}));
    this.commands.put("sharpen", s -> new FilterCommand(s, new double[][]{
            {-0.125, -0.125, -0.125, -0.125, -0.125},
            {-0.125, 0.25, 0.25, 0.25, -0.125},
            {-0.125, 0.25, 1, 0.25, -0.125},
            {-0.125, 0.25, 0.25, 0.25, -0.125},
            {-0.125, -0.125, -0.125, -0.125, -0.125}}));
    this.commands.put("resize", Resize::new);
  }

  /**
   * sends the render message request to the view, handles IOException.
   *
   * @param message the given message
   * @throws IllegalStateException if an IOException occurs
   */
  protected void transmit(String message) throws IllegalStateException {
    try {
      this.view.renderMessage(message);
    } catch (IOException e) {
      throw new IllegalStateException(e.getMessage());
    }
  }

  /**
   * sends the render request to the view, handles IOException.
   *
   * @throws IllegalStateException if an IOException occurs
   */
  protected void transmitRender() throws IllegalStateException {
    try {
      this.view.render();
    } catch (IOException e) {
      throw new IllegalStateException(e.getMessage());
    }
  }
}
