<h2 align="center">Image Manipulator</h3>

<!-- TABLE OF CONTENTS -->
<div id="top"></div>
<details>
  <summary>Table of Contents</summary>
  <ol>
    <li><a href="#buildwith">Built With</a></li>
    <li><a href="#design">Design</a></li>
    <li><a href="#acknowledgments">Acknowledgments</a></li>
  </ol>
</details>

This is a GUI image manipulator Silas Nevstad and Ari Mahler programmed for Northeastern's CS3500 Object
Orientated Design class. It allows a user to load in and save images as well as manipulate them through
a variety of commands.

### Built With
* [Java](https://java.com)
* [java.awt](https://docs.oracle.com/javase/7/docs/api/java/awt/package-summary.html)
  * java.awt.event
  * java.awt.image
* [java.io](https://docs.oracle.com/javase/7/docs/api/java/io/package-summary.html)
* [java.lang](https://docs.oracle.com/javase/7/docs/api/java/lang/package-summary.html)
* [java.util](https://docs.oracle.com/javase/8/docs/api/java/util/package-summary.html)
  * java.util.function
* [javax.imageio](https://docs.oracle.com/javase/7/docs/api/javax/imageio/ImageIO.html)
* [javax.swing](https://docs.oracle.com/javase/tutorial/uiswing/index.html)
  * javax.swing.border
  * javax.swing.text

<p align="right">(<a href="#top">back to top</a>)</p>

<!-- Design -->
<div id="design">

## Design
Our program includes a model, two views, and two controllers. The user can use the command-line interface to start
the program with a script, in a text view, or in a GUI. Our controller handles the user's input and GUI interactions
by waiting for an input through either a pre-programmed button from the GUI or a full command from the text view 
or a given file.

Our GUI controller is built by implementing an ImageController and ActionListener interfaces. Unlike previous
projects, it awaits a specific user input and executes the entire line simultaneously. It does this with the
help of our Command interface, an interface from which all our commands stem, and our view class. When the program
is run, rather than handling the user's input immediately, our program adds action listeners to our two buttons, quit
and execute, and simply listens for user interaction. Once an input is received, our controller first handles any errors;
Then, a hashmap, filled with all our preset image operations and phrases, creates and calls the specified command.
From there, it either creates an ImageCommand, with the image name, destination name, and an optional function as inputs,
or an ImageFilter, with the image name, destination name, and a filter as inputs. They both contain only one public function
called use which implements the operation.

Our view implements an interactive GUI interface, including a button panel for our two buttons, an image panel containing
the current image, and an input field that allows the user to type in commands. Our view implements the ImageView interface
and extends the JFrame class. It handles operations such as refreshing the window, setting a new image, getting the command
directly from the text input box, and displaying error messages to the user. This class's functions are very closely related
to those of java's JFrame class, so for that reason, the tests for this class are minimal because otherwise, they become redundant.

The text view needed to implement a parent interface that ImageView implemented, however we noticed quickly that,
while the ImageView is an adequate interface for GUIs, it contains far too many excess functions that our Text view
would not have to implement. To fix this, we created an even more generic interface, GenericView, with the 
3 key functions we needed from a view (produce an image, send a message, update the view). We then had ImageView
extend this interface, adding the new functionality in ImageGraphics with the existing helper methods. With this,
we created TextGraphics, and built our now existing text view. This meant both views could be referenced through
GenericView now.

Our model class monitors and manipulates images through our util and image classes. Our image class
represents images using a 3D integer array and offers only a couple of commands to monitor it. Our util
class uses the java IO library and buffered image library to more efficiently convert images and connect
our program to the file system. It offers a variety of commands to load/save images from/to the disk file.
Our model draws all this together to interact with the controller, so it can house and help manipulate
the images. Our images throughout the program are stored in a hashmap within the model.

UPDATES: By implementing the Java swing library, we have made our view more interactive and easier to utilize.
Instead of the old text input, an execute button, and a quit button, we have replaced it with a menu
bar that houses all our commands. From here the user can access all of our available commands, as well as,
an image switcher to change between loaded images, and a text input prompt for written commands. Another new implementation 
is we've added histograms beside every image for the red, green, blue, and intensity components. To do this we've overlayed 
4 bar graphs over one another and colored them in their respective colors (grey for intensity) by 
extending the swing JPanel class and overriding its paintComponent function. They refresh every time an 
image manipulation or change is made.
- Saving now saves the image currently displayed.
- ScrollPanel has been added for images too large to be displayed.
- Resize command added to menu bar.
- Find the 'old' text input under More in the menu bar for written commands.
- Cannot load multiple versions of the same image, doing so overwrites the old image.

To implement the histogram functionality for the GUI, we added a new decorator to the image
class. This modification will not impact other uses of the Image class, however it extends 
functionality for our use case.

Despite these changes, the program's file and text view functionality will function identically to
the previous iteration.



</div>
<p align="right">(<a href="#top">back to top</a>)</p>

<!-- ACKNOWLEDGMENTS -->
<div id="acknowledgements">

## Acknowledgments
These are the images we used in our project.
* [Earth Image](https://www.pexels.com/search/earth/)
* [Dog Image](https://en.wikipedia.org/wiki/Portal:Dogs/Selected_picture/7)
* [Cat Image](https://en.wikipedia.org/wiki/File:A-Cat.jpg)
* [Manhattan Landscape](https://northeastern.instructure.com/courses/111220/assignments/1384373)
* [London Landscape](https://unsplash.com/s/photos/london-landscape)

<p align="right">(<a href="#top">back to top</a>)</p>

</div>