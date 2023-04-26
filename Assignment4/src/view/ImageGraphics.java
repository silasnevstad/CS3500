package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;
import java.util.function.Consumer;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import model.Histogram;
import model.ImageImpl;
import model.ImageModel;
import model.ImageUtil;

/**
 * This is an implementation of the ImageView interface
 * It loads and displays images as well as shows any error messages using a pop-up dialog box.
 */
public class ImageGraphics extends JFrame implements ImageView {
  private JLabel imageLabel;
  private JLabel messageLabel;
  private final ImageModel model;
  private JMenuBar menu;
  private Consumer<String> command;
  private JScrollPane scrollPane;
  private JMenu imageSubMenu;
  private String current;
  private GraphPanel graphPanel;
  private JTextField input;

  /**
   * Creates a new {@code ImageGraphics}, which prepares the window for the image editor.
   */
  public ImageGraphics(ImageModel model) {
    super();
    this.setTitle("Image Manipulator");
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setBackground(Color.GRAY);
    this.messageLabel = new JLabel();
    this.model = model;

    this.setLayout(new BorderLayout());
    this.add(this.messageLabel, BorderLayout.NORTH);

    buildImageDisplay(600, 500); // arbitrarily chosen starting size
    buildFeatures();

    this.pack();

    this.command = null;
    this.current = "";
  }

  /**
   * Builds the display to show an image on a scrollable panel.
   *
   * @param width  the width of the panel.
   * @param height the height of the panel.
   */
  private void buildImageDisplay(int width, int height) {
    this.imageLabel = new JLabel();
    this.scrollPane = new JScrollPane(imageLabel);
    scrollPane.setPreferredSize(new Dimension(width, height));
    this.add(scrollPane, BorderLayout.CENTER);
  }

  /**
   * Builds the features of the program, the menu bar. From which the user can access any command.
   */
  private void buildFeatures() {
    buildMenuBar();
    this.setJMenuBar(menu);
    this.buildFileSubMenu();
    this.buildImageSubMenu();
    this.buildFilterSubMenu();
    this.buildTransformSubMenu();
    this.buildComponentSubMenu();
    this.buildResizeSubMenu();
    this.buildExecuteSubMenu();
  }

  /**
   * Both builds and updates the histogram graph display, using a histogram, the current image, and
   * a GraphPanel. First, it constructs a new histogram with the current image displayed, then
   * initiates the GraphPanel with the new histogram, or if it has already been initiated, it loads
   * the new histogram to the GraphPanel.
   */
  private void updateGraphDisplay(ImageImpl image) {
    Histogram histogram = new Histogram(image);
    try {
      this.graphPanel.loadHistograms(histogram.getHistograms());
    } catch (NullPointerException e) { // in case we are loading the first image.
      this.graphPanel = new GraphPanel(histogram.getHistograms());
    }
    graphPanel.setPreferredSize(new Dimension(256, 256));
    graphPanel.repaint();
    this.add(graphPanel, BorderLayout.EAST);
  }

  /**
   * Builds the menu bar for the view.
   */
  private void buildMenuBar() {
    this.menu = new JMenuBar();
  }

  /**
   * Builds the file submenu (load & save).
   */
  private void buildFileSubMenu() {
    JMenu subMenu = new JMenu("File");
    JMenuItem load = new JMenuItem("Load");

    load.addActionListener((ActionEvent e) -> {
      final JFileChooser fileChooser = new JFileChooser("res/");
      int status = fileChooser.showOpenDialog(null);
      if (command != null && status == JFileChooser.APPROVE_OPTION) {
        File file = fileChooser.getSelectedFile();
        command.accept("load " + file.getAbsolutePath() + " " + file.getName());
        updateImageSubMenu(file.getName());
      }
    });
    subMenu.add(load);
    JMenuItem save = new JMenuItem("Save");
    save.addActionListener((ActionEvent e) -> {
      final JFileChooser chooser = new JFileChooser();
      int status = chooser.showSaveDialog(ImageGraphics.this);
      if (command != null && status == JFileChooser.APPROVE_OPTION) {
        File file = chooser.getSelectedFile();
        command.accept("save " + file.getAbsolutePath() + " " + this.current);
      }
    });
    subMenu.add(save);
    this.menu.add(subMenu);
  }

  /**
   * Builds the image sub menu, an option for the user to switch between loaded images.
   */
  private void buildImageSubMenu() {
    this.imageSubMenu = new JMenu("Images");

    this.menu.add(this.imageSubMenu);
  }

  /**
   * Updates the image sub menu with a menu item, given the name of the image.
   *
   * @param name String name of the image.
   */
  private void updateImageSubMenu(String name) {
    JMenuItem image = new JMenuItem(name);
    image.addActionListener((ActionEvent e) -> {
      if (command != null) {
        update(name);
      }
    });
    this.imageSubMenu.add(image);
  }

  /**
   * Builds the execute command sub menu, for written commands.
   */
  private void buildExecuteSubMenu() {
    JMenu subMenu = new JMenu("More");
    JMenuItem execute = new JMenuItem("Execute");
    execute.addActionListener((ActionEvent e) -> {
      String command = JOptionPane.showInputDialog(this,
              "Enter your command(s) here:");
      if (this.command != null && command != null) {
        this.command.accept(command);
      }
    });
    subMenu.add(execute);
    this.menu.add(subMenu);
  }

  /**
   * Builds the filter submenu, for the filter commands.
   */
  private void buildFilterSubMenu() {
    JMenu subMenu = new JMenu("Filter");
    JMenuItem greyscale = new JMenuItem("Greyscale");
    greyscale.addActionListener((ActionEvent e) -> {
      if (command != null) {
        command.accept("greyscale " + this.current + " " + this.current);
      }
    });
    subMenu.add(greyscale);

    JMenuItem blur = new JMenuItem("Blur");
    blur.addActionListener((ActionEvent e) -> {
      if (command != null) {
        command.accept("blur " + this.current + " " + this.current);
      }
    });
    subMenu.add(blur);

    JMenuItem sharpen = new JMenuItem("Sharpen");
    sharpen.addActionListener((ActionEvent e) -> {
      if (command != null) {
        command.accept("sharpen " + this.current + " " + this.current);
      }
    });
    subMenu.add(sharpen);

    JMenuItem sepia = new JMenuItem("Sepia");
    sepia.addActionListener((ActionEvent e) -> {
      if (command != null) {
        command.accept("sepia " + this.current + " " + this.current);
      }
    });
    subMenu.add(sepia);

    this.menu.add(subMenu);
  }

  /**
   * Builds the transform submenu, for all transformation commands.
   */
  private void buildTransformSubMenu() {
    JMenu subMenu = new JMenu("Transform");
    JMenuItem horizontalFlip = new JMenuItem("Flip Horizontally");
    horizontalFlip.addActionListener((ActionEvent e) -> {
      if (command != null) {
        command.accept("horizontal-flip " + this.current + " " + this.current);
      }
    });
    subMenu.add(horizontalFlip);

    JMenuItem verticalFlip = new JMenuItem("Flip Vertically");
    verticalFlip.addActionListener((ActionEvent e) -> {
      if (command != null) {
        command.accept("vertical-flip " + this.current + " " + this.current);
      }
    });
    subMenu.add(verticalFlip);

    JMenuItem brighten = new JMenuItem("Brighten");
    brighten.addActionListener((ActionEvent e) -> {
      if (command != null) {
        String value = JOptionPane.showInputDialog("Brighten by...");
        command.accept("brighten " + value + " " + this.current + " " + this.current);
      }
    });
    subMenu.add(brighten);

    JMenuItem darken = new JMenuItem("Darken");
    darken.addActionListener((ActionEvent e) -> {
      if (command != null) {
        String result = JOptionPane.showInputDialog("Darken by...");
        int intValue = -Integer.parseInt(result);
        String value = String.valueOf(intValue);
        command.accept("brighten " + value + " " + this.current + " " + this.current);
      }
    });
    subMenu.add(darken);

    this.menu.add(subMenu);
  }

  /**
   * Builds the component submenu, for all component commands.
   */
  private void buildComponentSubMenu() {
    JMenu subMenu = new JMenu("Component");
    JMenuItem red = new JMenuItem("Red");
    red.addActionListener((ActionEvent e) -> {
      if (command != null) {
        command.accept("red-component " + this.current + " " + this.current);
      }
    });
    subMenu.add(red);

    JMenuItem green = new JMenuItem("Green");
    green.addActionListener((ActionEvent e) -> {
      if (command != null) {
        command.accept("green-component " + this.current + " " + this.current);
      }
    });
    subMenu.add(green);

    JMenuItem blue = new JMenuItem("Blue");
    blue.addActionListener((ActionEvent e) -> {
      if (command != null) {
        command.accept("blue-component " + this.current + " " + this.current);
      }
    });
    subMenu.add(blue);

    JMenuItem value = new JMenuItem("Value");
    value.addActionListener((ActionEvent e) -> {
      if (command != null) {
        command.accept("value-component " + this.current + " " + this.current);
      }
    });
    subMenu.add(value);

    JMenuItem intensity = new JMenuItem("Intensity");
    intensity.addActionListener((ActionEvent e) -> {
      if (command != null) {
        command.accept("intensity-component " + this.current + " " + this.current);
      }
    });
    subMenu.add(intensity);

    JMenuItem luma = new JMenuItem("Luma");
    luma.addActionListener((ActionEvent e) -> {
      if (command != null) {
        command.accept("luma-component " + this.current + " " + this.current);
      }
    });
    subMenu.add(luma);


    this.menu.add(subMenu);
  }

  /**
   * Builds the resize submenu, for the resizing commands.
   */
  private void buildResizeSubMenu() {
    JMenu subMenu = new JMenu("Resize");

    JMenuItem quarter = new JMenuItem("0.25x");
    quarter.addActionListener((ActionEvent e) -> {
      if (command != null) {
        command.accept("resize 0.25 " + this.current + " " + this.current);
      }
    });
    subMenu.add(quarter);

    JMenuItem half = new JMenuItem("0.5x");
    half.addActionListener((ActionEvent e) -> {
      if (command != null) {
        command.accept("resize 0.5 " + this.current + " " + this.current);
      }
    });
    subMenu.add(half);

    JMenuItem threeQuarters = new JMenuItem("0.75x");
    threeQuarters.addActionListener((ActionEvent e) -> {
      if (command != null) {
        command.accept("resize 0.75 " + this.current + " " + this.current);
      }
    });
    subMenu.add(threeQuarters);

    JMenuItem oneAndQuarter = new JMenuItem("1.25x");
    oneAndQuarter.addActionListener((ActionEvent e) -> {
      if (command != null) {
        command.accept("resize 1.25 " + this.current + " " + this.current);
      }
    });
    subMenu.add(oneAndQuarter);

    JMenuItem oneAndHalf = new JMenuItem("1.5x");
    oneAndHalf.addActionListener((ActionEvent e) -> {
      if (command != null) {
        command.accept("resize 1.25 " + this.current + " " + this.current);
      }
    });
    subMenu.add(oneAndHalf);

    JMenuItem oneAndThreeQuarters = new JMenuItem("1.75x");
    oneAndThreeQuarters.addActionListener((ActionEvent e) -> {
      if (command != null) {
        command.accept("resize 1.75 " + this.current + " " + this.current);
      }
    });
    subMenu.add(oneAndThreeQuarters);

    JMenuItem twoTimes = new JMenuItem("2x");
    twoTimes.addActionListener((ActionEvent e) -> {
      if (command != null) {
        command.accept("resize 2 " + this.current + " " + this.current);
      }
    });
    subMenu.add(twoTimes);

    this.menu.add(subMenu);
  }

  @Override
  public void makeVisible() {
    this.setVisible(true);
  }

  /**
   * Provides the view with a callback option to process a command.
   *
   * @param command the command to callback.
   */
  public void setCommandCallBack(Consumer<String> command) {
    this.command = command;
  }

  @Override
  public String getCommand() {
    String command = this.input.getText();
    this.input.setText("");
    return command;
  }

  @Override
  public void setImage(int[][][] image) {
    try {
      BufferedImage img = ImageUtil.getBufferedImage(image);
      this.imageLabel.setIcon(new ImageIcon(img));
      this.scrollPane.setPreferredSize(new Dimension(img.getWidth(), img.getHeight()));
      this.setPreferredSize(new Dimension(img.getWidth() + 256, img.getHeight() + 256));
      this.pack();
    } catch (IOException e) {
      this.showErrorMessage(e.toString());
    }
  }

  @Override
  public void refresh() {
    this.revalidate();
  }

  @Override
  public void showErrorMessage(String error) {
    JOptionPane.showMessageDialog(this, error, "Error",
            JOptionPane.ERROR_MESSAGE);
  }

  @Override
  public void update(String file) {
    int[][][] img = this.model.getImage(file);
    ImageImpl image = new ImageImpl(img);
    setImage(img);
    this.current = file;
    this.updateGraphDisplay(image);
    refresh();
  }

  @Override
  public void renderMessage(String message) {
    this.messageLabel.setText(message);
  }

  @Override
  public void render() {
    makeVisible();
  }

  /**
   * This class represents a GraphPanel, and extends the JPanel interface. It's purpose it to
   * paint 4 different colored graphs to a JPanel representing an image's histogram.
   */
  private class GraphPanel extends JPanel {
    private int[][] cords;
    private int startX = 0;
    private int startY = getContentPane().getHeight() / 2;

    /**
     * Constructs a Graph Panel given a 2D array of coordinates.
     *
     * @param cords a 2D array of integers (coordinates) [[Red], [Green], [Blue], [Intensity]].
     */
    GraphPanel(int[][] cords) {
      this.cords = cords;
    }

    /**
     * Updates the coordinates for the current histogram shown, as well as, the startY position.
     *
     * @param histograms a 2D array of coordinates (red, green, blue, intensity)
     */
    protected void loadHistograms(int[][] histograms) {
      this.cords = histograms;
      this.startY = getContentPane().getHeight() / 2;
    }

    @Override
    protected void paintComponent(Graphics g) {
      super.paintComponents(g);
      Graphics2D graphics2D = (Graphics2D) g;

      Color red = new Color(227, 64, 64, 100);
      Color green = new Color(100, 236, 100, 100);
      Color blue = new Color(100, 100, 255, 100);
      Color grey = new Color(50, 50, 50, 100);
      Color[] colorsSoft = new Color[]{red, green, blue, grey};
      Color[] colorHard = new Color[]{Color.red.brighter(), Color.green, Color.blue, Color.gray};
      int i = 0;
      int prevUpperY = 0;
      int prevLowerY = 0;
      int max = maxHelper(this.cords);
      int min = minHelper(this.cords);
      for (int[] graph : this.cords) {
        boolean skipFirst = true;
        for (int cord : graph) {
          int y = scaleBetween(cord, 256, 0, max, min);
          int upperY = startY - (y / 2);
          int lowerY = startY + (y / 2);
          graphics2D.setColor(colorsSoft[i]);
          graphics2D.drawLine(startX, startY, startX, upperY);
          graphics2D.drawLine(startX, startY, startX, lowerY);

          if (!skipFirst) {
            graphics2D.setColor(colorHard[i]);
            graphics2D.drawLine(startX - 1, prevUpperY, startX, upperY);
            graphics2D.drawLine(startX - 1, prevLowerY, startX, lowerY);
          } else {
            skipFirst = false;
          }
          prevUpperY = upperY;
          prevLowerY = lowerY;
          startX++;
        }
        i++;
        startX = 0;
      }
    }

    /**
     * Gets the maximum value in a given array of integers.
     *
     * @param input a 2D array of integers.
     * @return the max integer found.
     */
    private int maxHelper(int[][] input) {
      int max = 0;
      for (int[] nums : input) {
        for (int num : nums) {
          max = Math.max(num, max);
        }
      }
      return max;
    }

    /**
     * Gets the minimum value in a given array of integers.
     *
     * @param input a 2D array of integers.
     * @return the min integer found.
     */
    private int minHelper(int[][] input) {
      int min = 1000;
      for (int[] nums : input) {
        for (int num : nums) {
          min = Math.min(num, min);
        }
      }
      return min;
    }

    /**
     * Scales a given number between a range of allowed numbers, given a max and min for the
     * given numbers set.
     *
     * @param num        the unscaled integer.
     * @param maxAllowed the maximum value allowed.
     * @param minAllowed the minimum value allowed.
     * @param max        the max in the greater set of integers.
     * @param min        the min in the greater set of integers.
     * @return an integer scaled to the provided range.
     */
    private int scaleBetween(int num, int maxAllowed, int minAllowed, int max, int min) {
      return (maxAllowed - minAllowed) * (num - min) / (max - min) + minAllowed;
    }
  }
}
