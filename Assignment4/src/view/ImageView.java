package view;

import java.util.function.Consumer;

/**
 * This interface represents the view to a controller.
 * It loads images, and shows any error messages using a pop-up dialog box.
 */
public interface ImageView extends GenericView {
  /**
   * Makes objects visible in the view.
   */
  void makeVisible();

  /**
   * Gets the command from the user.
   *
   * @return the string command to be executed.
   */
  String getCommand();


  /**
   * Shows an error message in the form of a pop-up,
   * in case any command does not run properly.
   *
   * @param error the given error text
   */
  void showErrorMessage(String error);

  /**
   * Sets the image displayed in the view to the image stored in the model.
   *
   * @param image an integer array of pixels.
   */
  void setImage(int[][][] image);

  /**
   * This signals the view to refresh itself (draw itself).
   */
  void refresh();

  /**
   * Provides the view with a callback option to process a command.
   * @param command the command to callback.
   */
  void setCommandCallBack(Consumer<String> command);
}



