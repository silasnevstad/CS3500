<h2 align="center">Image Manipulator</h3>

<!-- TABLE OF CONTENTS -->
<div id="top"></div>
<details>
  <summary>Table of Contents</summary>
  <ol>
    <li><a href="#Running">Running the Program</a></li>
    <li><a href="#ScriptCommand">Script Commands</a></li>
    <li><a href="#Using the GUI">Using the GUI</a></li>
  </ol>
</details>

## Running the Program
There are 2 different kinds of acceptable command-line arguments that this program can accept:
* -file path-name: this will run the given path-name as a script if possible.
* -text: will start the program with a text view

If you do not specify one of these arguments, the program will boot into GUI mode.
If you use any other argument, it will throw an error.

The file res/script.txt will contain a script that you can use to demo all the features in the program.
As mentioned above, this can be accessed by running the program with the argument "-file res/script.txt".

<!-- Script Commands -->
<div id="ScriptCommand">

## Script Commands
* load image-path image-name: Load an image from a specified path and name it a given image name.

* save image-path image-name: Save an image with the given name to the specified path.

* red-component image-name dest-image-name: Create a greyscale image with the red component of the
  image with a given name, and new destination name.

* green-component image-name dest-image-name: Create a greyscale image with the green component of the
  image with a given name, and new destination name.

* blue-component image-name dest-image-name: Create a greyscale image with the blue component of the
  image with a given name, and new destination name.

* luma-component image-name dest-image-name: Create a greyscale image with the luma component of the
  image with a given name, and new destination name.

* intensity-component image-name dest-image-name: Create a greyscale image with the intensity component of the
  image with a given name, and new destination name.

* horizontal-flip image-name dest-image-name: Flip a given image horizontally to create a new image,
  under the given name.

* vertical-flip image-name dest-image-name: Flip a given image vertically to create a new image,
  under the given name.

* brighten increment image-name dest-image-name: brighten a given image by a given increment, creating
  a new image under the given name.

* blur image-name dest-image-name: blur a given image, creating a new blurred image under the given name.

* sharpen image-name dest-image-name: sharpen a given image, creating a new sharpened image under the given name.

* sepia image-name dest-image-name: add a sepia filter to a given image, creating a new sepia image under the given name.

* greyscale image-name dest-image-name: add a greyscale filter to a given image, creating a new greyscale image under the given name.

* resize increment image-name dest-image-name: resize the given image, by a given increment (decimal), creating the resized image under the given name.

<p align="right">(<a href="#top">back to top</a>)</p>
</div>

<!-- Using the GUI -->
<div id="Using the GUI">

## Using the GUI

The GUI is the default run option of the program. That is, if you simply choose to double-click
on the .jar file or run the program without arguments, it will open the GUI view.

To utilize the GUI, you must simply choose a button, which will operate the implied function to the
currently shown image. SHOULD YOU ATTEMPT TO UTILIZE A FUNCTION WITHOUT AN IMAGE, the GUI will give
you an error. The function buttons are all contained within various tabs on top of the
screen in categories by use case.

Should you be used to using functions like what we have done with the text view or the ones given
above, you can instead utilize the execute command button to type in a command by hand.

In short:
* Load an image by going into <i>File->Load</i> and selecting an image from the menu
* Select any operation from the various categories in the top bar to operate on the image OR
* select <i>More->Execute</i> to enter in your own command.
* If you wish to load and work on more than one image, use the <i>Image</i> menu to navigate between
images
<p align="right">(<a href="#top">back to top</a>)</p>
</div>

## Acknowledgements

This is a GUI image manipulator Silas Nevstad and Ari Mahler programmed for Northeastern's CS3500 Object
Orientated Design class. It allows a user to load in and save images as well as manipulate them through
a variety of commands.
