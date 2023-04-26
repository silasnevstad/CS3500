package cs3500.marblesolitaire.view;

import java.awt.*;
//import java.awt.JPanel;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

public class BoardPanel extends JPanel {
  private MarbleSolitaireModelState modelState;
  private Image emptySlot, marbleSlot, blankSlot;
  private final int cellDimension;
  private int originX,originY;
  
  public BoardPanel(MarbleSolitaireModelState state) throws IllegalStateException {
    super();
    this.modelState = state;
    this.setBackground(Color.WHITE);
    this.cellDimension = 50;
    try {
      emptySlot = ImageIO.read(new FileInputStream("res/empty.png"));
      emptySlot = emptySlot.getScaledInstance(cellDimension, cellDimension, Image.SCALE_DEFAULT);

      marbleSlot = ImageIO.read(new FileInputStream("res/marble.png"));
      marbleSlot = marbleSlot.getScaledInstance(cellDimension, cellDimension, Image.SCALE_DEFAULT);

      blankSlot = ImageIO.read(new FileInputStream("res/blank.png"));
      blankSlot = blankSlot.getScaledInstance(cellDimension, cellDimension, Image.SCALE_DEFAULT);

      this.setPreferredSize(
              new Dimension((this.modelState.getBoardSize() + 4) * cellDimension
                      , (this.modelState.getBoardSize() + 4) * cellDimension));
    } catch (IOException e) {
      throw new IllegalStateException("Icons not found!");
    }

  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    
    originX = (int) (this.getPreferredSize().getWidth() / 2 - this.modelState.getBoardSize() * cellDimension / 2);
    originY = (int) (this.getPreferredSize().getHeight() / 2 - this.modelState.getBoardSize() * cellDimension / 2);


    //your code to the draw the board should go here. 
    //The originX and originY is the top-left of where the cell (0,0) should start
    //cellDimension is the width (and height) occupied by every cell
    
  }


 
}
