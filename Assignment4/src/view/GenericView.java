package view;

import java.io.IOException;

/**
 * This interface represents the generic methods that a view should have. This includes
 * updating the view, rendering a message, and rendering the menu
 */
public interface GenericView {

  /**
   * Updates the view to account for the just processed command.
   */
  void update(String file);

  /**
   * Prints out a new message onto the view.
   *
   * @param message the given message to be printed
   */
  void renderMessage(String message) throws IOException;

  /**
   * Renders the menu. This can take the form of a GUI panel or a text view.
   */
  void render() throws IOException;


}
