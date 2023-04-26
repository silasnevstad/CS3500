package view;

import java.io.IOException;
import java.util.HashSet;

/**
 * This class represents the text graphic view for the Image Manipulator. It uses a HashSet to
 * hold a current list of all existing images, and operations to both render
 * the board and messages.
 */
public class TextGraphics implements GenericView {

  private final Appendable output;
  private final HashSet<String> images;

  /**
   * Creates a new {@code TextGraphics} with a given output direction.
   *
   * @param output the given output direction
   */
  public TextGraphics(Appendable output) {
    if (output == null) {
      throw new IllegalStateException("Given argument cannot be null.");
    }
    this.output = output;
    this.images = new HashSet<>();
  }

  /**
   * Creates a new {@code TextGraphics} with the default output direction, System.out.
   */
  public TextGraphics() {
    this.output = System.out;
    this.images = new HashSet<>();
  }

  @Override
  public void update(String file) {
    this.images.add(file);
  }

  @Override
  public void renderMessage(String message) throws IOException {
    this.output.append(message);
  }

  @Override
  public void render() throws IOException {
    this.output.append("Images available:\n");
    for (String name : this.images) {
      this.output.append(name + "\n");
    }
    this.output.append("Next operation: \n");
  }
}
