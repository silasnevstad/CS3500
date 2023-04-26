import java.io.FileReader;
import java.io.IOException;

import controller.ImageController;
import controller.ImageControllerImpl;
import controller.TextControllerImpl;
import model.ImageModel;
import model.ImageModelImpl;
import view.GenericView;
import view.ImageGraphics;
import view.ImageView;
import view.TextGraphics;

/**
 * Class that has the main function for the image manipulator.
 */
public class ImageManipulator {
  /**
   * Runs a new controller's playGame method with default settings.
   *
   * @param args if wanted, the file path to a script that runs at startup
   */
  public static void main(String[] args) throws IOException {
    ImageController controller;
    ImageModel model = new ImageModelImpl();

    //General case: GUI
    if (args.length == 0) {
      ImageView iView = new ImageGraphics(model);
      controller = new ImageControllerImpl(model, iView);
    } else {
      switch (args[0]) {
        case "-file":
          Readable r = new FileReader(args[1]);
          GenericView fView = new TextGraphics();
          controller = new TextControllerImpl(model, fView, r, System.out);
          break;
        case "-text":
          GenericView tView = new TextGraphics();
          controller = new TextControllerImpl(model, tView);
          break;
        default:
          throw new IllegalArgumentException("Invalid argument: " + args[0]);
      }
    }
    controller.start();
  }
}
